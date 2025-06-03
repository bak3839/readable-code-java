package cleancode.studycafe.mytobe;

import cleancode.studycafe.mytobe.exception.AppException;
import cleancode.studycafe.mytobe.io.InputHandler;
import cleancode.studycafe.mytobe.io.OutputHandler;
import cleancode.studycafe.mytobe.io.StudyCafeFileHandler;
import cleancode.studycafe.mytobe.model.*;
import cleancode.studycafe.mytobe.pass.StudyCafeLockerPasses;
import cleancode.studycafe.mytobe.pass.StudyCafePasses;

public class StudyCafePassMachine {
    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            showStartComment();

            StudyCafePassType userSelectedPassType = selectPassType();

            StudyCafePasses passes = getPassesOfPassType(userSelectedPassType);
            StudyCafePass userSelectedPass = getUserSelectedPass(passes);

            StudyCafeLockerPass lockerPass = getLockerPass(userSelectedPass);

            lockerSelection(lockerPass, userSelectedPass);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void showStartComment() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
    }

    private StudyCafePassType selectPassType() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private StudyCafePasses getPassesOfPassType(StudyCafePassType userSelectedPassType) {
        StudyCafePasses studyCafePasses = studyCafeFileHandler.readStudyCafePasses2();
        return studyCafePasses.findByPassType(userSelectedPassType);
    }

    private StudyCafePass getUserSelectedPass(StudyCafePasses passes) {
        outputHandler.showPassListForSelection(passes);
        return inputHandler.getSelectPass2(passes);
    }

    private StudyCafeLockerPass getLockerPass(StudyCafePass userSelectedPass) {
        StudyCafeLockerPasses lockerPasses = studyCafeFileHandler.readLockerPasses2();
        return lockerPasses.findAvailableLocker(userSelectedPass);
    }

    private void lockerSelection(StudyCafeLockerPass lockerPass, StudyCafePass userSelectedPass) {
        boolean lockerSelection = false;
        if (lockerPass != null) {
            outputHandler.askLockerPass(lockerPass);
            lockerSelection = inputHandler.getLockerSelection();
        }

        if (lockerSelection) {
            outputHandler.showPassOrderSummary(userSelectedPass, lockerPass);
        } else {
            outputHandler.showPassOrderSummary(userSelectedPass);
        }
    }
}
