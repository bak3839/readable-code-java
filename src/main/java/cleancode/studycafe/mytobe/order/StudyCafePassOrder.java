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
}
