package cleancode.minesweeper.tobe.minesweeper.board.cell;

public class CellState {
    private boolean isFlagged;
    private boolean isOpened;

    public CellState(boolean isFlagged, boolean isOpened) {
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    public static CellState initialize() {
        return new CellState(false, false);
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

    public boolean isFlagged() {
        return isFlagged;
    }
}
