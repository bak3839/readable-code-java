package cleancode.studycafe.mytobe.order;

import cleancode.studycafe.mytobe.pass.StudyCafeLockerPass;
import cleancode.studycafe.mytobe.pass.StudyCafeSeatPass;

public class StudyCafePassOrder {
    private final StudyCafeSeatPass seatPass;
    private final StudyCafeLockerPass lockerPass;

    private StudyCafePassOrder(StudyCafeSeatPass seatPass, StudyCafeLockerPass lockerPass) {
        this.seatPass = seatPass;
        this.lockerPass = lockerPass;
    }

    public static StudyCafePassOrder of(StudyCafeSeatPass seatPass, StudyCafeLockerPass lockerPass) {
        return new StudyCafePassOrder(seatPass, lockerPass);
    }

    public String aboutOrderOfPasses() {
        StringBuilder orderInfo = new StringBuilder();
        orderInfo.append(seatPass.getPassInfo());

        if(lockerPass != null && lockerPass.isPurchaseLocker()) {
            orderInfo.append("\n").append(lockerPass.getLockerInfo());
        }

        return orderInfo.toString();
    }

    public int orderDiscountPrice() {
        return seatPass.calculateDiscountPrice();
    }

    public int orderTotalPrice() {
        return seatPass.calculateTotalPrice() + (lockerPass != null ? lockerPass.getPrice() : 0);
    }
}
