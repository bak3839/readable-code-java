package cleancode.studycafe.mytobe.io;

import cleancode.studycafe.mytobe.pass.StudyCafeLockerPass;
import cleancode.studycafe.mytobe.pass.StudyCafeSeatPass;
import cleancode.studycafe.mytobe.pass.StudyCafePassType;
import cleancode.studycafe.mytobe.pass.StudyCafeLockerPasses;
import cleancode.studycafe.mytobe.pass.StudyCafeSeatPasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeFileHandler {
    private static final Path PASS_LIST_PATH = Paths.get("src/main/resources/cleancode/studycafe/pass-list.csv");
    private static final Path LOCKER_PATH = Paths.get("src/main/resources/cleancode/studycafe/locker.csv");

    public StudyCafeSeatPasses readStudyCafePasses() {
        try {
            List<String> lines = Files.readAllLines(PASS_LIST_PATH);
            List<StudyCafeSeatPass> studyCafeSeatPasses = new ArrayList<>();

            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                double discountRate = Double.parseDouble(values[3]);

                StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(studyCafePassType, duration, price, discountRate);
                studyCafeSeatPasses.add(studyCafeSeatPass);
            }

            return StudyCafeSeatPasses.of(studyCafeSeatPasses);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    public StudyCafeLockerPasses readLockerPasses() {
        try {
            List<String> lines = Files.readAllLines(LOCKER_PATH);
            List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);

                StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, duration, price);
                lockerPasses.add(lockerPass);
            }

            return StudyCafeLockerPasses.of(lockerPasses);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }
}
