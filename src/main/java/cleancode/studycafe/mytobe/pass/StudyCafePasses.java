package cleancode.studycafe.mytobe.pass;

import cleancode.studycafe.mytobe.exception.AppException;
import cleancode.studycafe.mytobe.model.StudyCafePass;
import cleancode.studycafe.mytobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePasses {
    private final List<StudyCafePass> passes;

    private StudyCafePasses(List<StudyCafePass> passes) {
        this.passes = passes;
    }

    public static StudyCafePasses of(List<StudyCafePass> passes) {
        return new StudyCafePasses(passes);
    }

    public List<StudyCafePass> getPasses() {
        return passes;
    }

    public StudyCafePasses findByPassType(StudyCafePassType userSelectedPassType) {
        List<StudyCafePass> studyCafePasses = passes.stream()
                .filter(pass -> pass.isSamePassType(userSelectedPassType))
                .toList();
        return StudyCafePasses.of(studyCafePasses);
    }

    public StudyCafePass getSelectedPass(int selectedIndex) {
        if(selectedIndex < 0 || selectedIndex >= passes.size()) {
            throw new AppException("잘못된 입력입니다.");
        }

        return passes.get(selectedIndex);
    }
}
