package cleancode.studycafe.mytobe.io;

import cleancode.studycafe.mytobe.exception.AppException;
import cleancode.studycafe.mytobe.pass.StudyCafeSeatPass;
import cleancode.studycafe.mytobe.pass.StudyCafePassType;
import cleancode.studycafe.mytobe.pass.StudyCafeSeatPasses;

import java.util.Scanner;

public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = SCANNER.nextLine();

        if ("1".equals(userInput)) {
            return StudyCafePassType.HOURLY;
        }
        if ("2".equals(userInput)) {
            return StudyCafePassType.WEEKLY;
        }
        if ("3".equals(userInput)) {
            return StudyCafePassType.FIXED;
        }
        throw new AppException("잘못된 입력입니다.");
    }

    public StudyCafeSeatPass getSelectPass(StudyCafeSeatPasses passes) {
        String userInput = SCANNER.nextLine();
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return passes.getSelectedPass(selectedIndex);
    }

    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        return "1".equals(userInput);
    }
}
