package cleancode.studycafe.mytobe.pass;

import java.util.List;

public class StudyCafeLockerPasses {
    private final List<StudyCafeLockerPass> lockerPasses;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> lockerPasses) {
        this.lockerPasses = lockerPasses;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> lockerPasses) {
        return new StudyCafeLockerPasses(lockerPasses);
    }

    public StudyCafeLockerPass findAvailableLocker(StudyCafeSeatPass userSelectedPass) {
        return lockerPasses.stream()
                .filter(option -> option.isAvailableLocker(userSelectedPass))
                .findFirst()
                .orElse(null);
    }
}
