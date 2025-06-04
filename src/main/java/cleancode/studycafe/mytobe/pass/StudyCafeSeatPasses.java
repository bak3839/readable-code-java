package cleancode.studycafe.mytobe.pass;

import cleancode.studycafe.mytobe.exception.AppException;

import java.util.ArrayList;
import java.util.List;

public class StudyCafeSeatPasses {
    private final List<StudyCafeSeatPass> seatPasses;

    private StudyCafeSeatPasses(List<StudyCafeSeatPass> seatPasses) {
        this.seatPasses = seatPasses;
    }

    public static StudyCafeSeatPasses of(List<StudyCafeSeatPass> passes) {
        return new StudyCafeSeatPasses(passes);
    }

    public List<StudyCafeSeatPass> getSeatPasses() {
        return new ArrayList<>(seatPasses);
    }

    public StudyCafeSeatPasses findByPassType(StudyCafePassType userSelectedPassType) {
        List<StudyCafeSeatPass> studyCafeSeatPasses = seatPasses.stream()
                .filter(pass -> pass.isSamePassType(userSelectedPassType))
                .toList();
        return StudyCafeSeatPasses.of(studyCafeSeatPasses);
    }

    public StudyCafeSeatPass getSelectedPass(int selectedIndex) {
        if(selectedIndex < 0 || selectedIndex >= seatPasses.size()) {
            throw new AppException("잘못된 입력입니다.");
        }

        return seatPasses.get(selectedIndex);
    }
}
