package cleancode.studycafe.mytobe;

import cleancode.studycafe.mytobe.exception.AppException;
import cleancode.studycafe.mytobe.io.InputHandler;
import cleancode.studycafe.mytobe.io.OutputHandler;
import cleancode.studycafe.mytobe.io.StudyCafeFileHandler;
import cleancode.studycafe.mytobe.model.*;
import cleancode.studycafe.mytobe.pass.StudyCafePasses;

import java.util.List;

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

            if (userSelectedPassType.isSamePassType(StudyCafePassType.HOURLY)) {
                outputHandler.showPassOrderSummary(userSelectedPass, null);
            }

            else if (userSelectedPassType.isSamePassType(StudyCafePassType.WEEKLY)) {
                outputHandler.showPassOrderSummary(userSelectedPass, null);
            }

            else if (userSelectedPassType.isSamePassType(StudyCafePassType.FIXED)) {
                List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
                StudyCafeLockerPass lockerPass = lockerPasses.stream()
                    .filter(option -> option.isAvailablePassType(userSelectedPass))
                    .findFirst()
                    .orElse(null);

                boolean lockerSelection = false;
                if (lockerPass != null) {
                    outputHandler.askLockerPass(lockerPass);
                    lockerSelection = inputHandler.getLockerSelection();
                }

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(userSelectedPass, lockerPass);
                } else {
                    outputHandler.showPassOrderSummary(userSelectedPass, null);
                }
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
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

    private void showStartComment() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
    }
}
