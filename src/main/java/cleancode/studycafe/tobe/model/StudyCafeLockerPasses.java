package cleancode.studycafe.tobe.model;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {
    private final List<StudyCafeLockerPass> lockerPasses;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> passes) {
        this.lockerPasses = passes;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> lockerPasses) {
        return new StudyCafeLockerPasses(lockerPasses);
    }

    public List<StudyCafeLockerPass> findPassBy(StudyCafePassType studyCafePassType) {
        return lockerPasses.stream()
                .filter(studyCafePass -> studyCafePass.isSamePassType(studyCafePassType))
                .toList();
    }

    public Optional<StudyCafeLockerPass> findLockerPassBy(StudyCafePass pass) {
        return lockerPasses.stream()
                .filter(pass::isSameDurationType)
                .findFirst();
    }
}
