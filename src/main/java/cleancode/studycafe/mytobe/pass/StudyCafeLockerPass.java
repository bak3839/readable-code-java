package cleancode.studycafe.mytobe.pass;

public class StudyCafeLockerPass {
    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private boolean purchaseStatus;

    private StudyCafeLockerPass(StudyCafePassType passType, int duration, int price, boolean purchaseStatus) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.purchaseStatus = purchaseStatus;
    }

    public static StudyCafeLockerPass of(StudyCafePassType passType, int duration, int price) {
        return new StudyCafeLockerPass(passType, duration, price, false);
    }

    public int getPrice() {
        return price;
    }

    public String getLockerInfo() {
        return passType.display(duration, price);
    }

    public boolean isAvailableLocker(StudyCafeSeatPass userSelectedPass) {
        return userSelectedPass.isSamePassType(this.passType)
                && userSelectedPass.isSameDuration(this.duration);
    }

    public void purchaseLocker() {
        this.purchaseStatus = true;
    }

    public boolean isPurchaseLocker() {
        return this.purchaseStatus == true;
    }
}
