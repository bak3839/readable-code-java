package cleancode.studycafe.mytobe;

import cleancode.studycafe.mytobe.exception.AppException;
import cleancode.studycafe.mytobe.io.InputHandler;
import cleancode.studycafe.mytobe.io.OutputHandler;
import cleancode.studycafe.mytobe.io.StudyCafeFileHandler;
import cleancode.studycafe.mytobe.order.StudyCafePassOrder;
import cleancode.studycafe.mytobe.pass.*;

public class StudyCafePassMachine {
    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            showStartComment();

            StudyCafePassType userSelectedPassType = selectPassType();

            StudyCafeSeatPasses passes = getPassesOfPassType(userSelectedPassType);
            StudyCafeSeatPass userSelectedPass = getUserSelectedPass(passes);

            StudyCafeLockerPass lockerPass = getLockerPass(userSelectedPass);
            purchaseLocker(lockerPass);

            showPassOrderSummary(userSelectedPass, lockerPass);
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

    private StudyCafeSeatPasses getPassesOfPassType(StudyCafePassType userSelectedPassType) {
        StudyCafeSeatPasses studyCafeSeatPasses = studyCafeFileHandler.readStudyCafePasses();
        return studyCafeSeatPasses.findByPassType(userSelectedPassType);
    }

    private StudyCafeSeatPass getUserSelectedPass(StudyCafeSeatPasses passes) {
        outputHandler.showPassListForSelection(passes);
        return inputHandler.getSelectPass(passes);
    }

    private StudyCafeLockerPass getLockerPass(StudyCafeSeatPass userSelectedPass) {
        StudyCafeLockerPasses lockerPasses = studyCafeFileHandler.readLockerPasses();
        return lockerPasses.findAvailableLocker(userSelectedPass);
    }

    private void purchaseLocker(StudyCafeLockerPass lockerPass) {
        if(lockerPass == null) {
            return;
        }

        outputHandler.askPurchaseLockerPass(lockerPass);
        if(inputHandler.getLockerSelection()) {
            lockerPass.purchaseLocker();
        }
    }

    private void showPassOrderSummary(StudyCafeSeatPass userSelectedPass, StudyCafeLockerPass lockerPass) {
        StudyCafePassOrder studyCafePassOrder = StudyCafePassOrder.of(userSelectedPass, lockerPass);
        outputHandler.showPassOrderSummary(studyCafePassOrder);
    }
}
