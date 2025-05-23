package cleancode.minesweeper.tobe;

public class Cell {
    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□";
    private static final String EMPTY_SIGN = "■";

    private int nearbyLandMineCount;
    private boolean isLandMine;
    private boolean isFlagged;
    private boolean isOpened;

    // Cell 가진 속성 : 근처 지뢰 수, 지뢰 여부
    // Cell 상태 : 깃발 유무, 열렸다/닫혔다. 사용자가 확인함

    private Cell(int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        this.nearbyLandMineCount = nearbyLandMineCount;
        this.isLandMine = isLandMine;
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    public static Cell create() {
        return of(0, false, false, false);
    }

    // 이름을 부여할 수 있음 -> 이펙티브 자바 아이템 1
    public static Cell of(int nearbyLandMineCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        return new Cell(nearbyLandMineCount, isLandMine, isFlagged, isOpened);
    }

    public void turnOnLandMine() {
        this.isLandMine = true;
    }

    public void updateNearbyLandMineCount(int count) {
        this.nearbyLandMineCount = count;
    }

    public void open() {
        this.isOpened = true;
    }

    public void flag() {
        this.isFlagged = true;
    }

    public boolean isOpened() {
        return this.isOpened;
    }

    public boolean isChecked() {
        return this.isFlagged || this.isOpened;
    }

    public boolean isLandMine() {
        return this.isLandMine;
    }

    public boolean hasLandMineCount() {
        return this.nearbyLandMineCount != 0;
    }

    // 현재 cell 상태에 따라 값을 반환
    public String getSign() {
        if(isOpened) {
            if(isLandMine) {
                return LAND_MINE_SIGN;
            }
            if(hasLandMineCount()) {
                return String.valueOf(nearbyLandMineCount);
            }
            return EMPTY_SIGN;
        }

        if(isFlagged) {
            return FLAG_SIGN;
        }

        return UNCHECKED_SIGN;
    }
}
