package cleancode.studycafe.mytobe.model;

import java.util.List;

public class Passes {
    private final List<StudyCafePass> studyCafePasses;

    public Passes(List<StudyCafePass> studyCafePasses) {
        this.studyCafePasses = studyCafePasses;
    }

    public static Passes of(List<StudyCafePass> studyCafePasses) {
        return new Passes(studyCafePasses);
    }


}
