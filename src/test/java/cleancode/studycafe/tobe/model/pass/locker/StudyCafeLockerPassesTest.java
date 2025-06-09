package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.io.provider.LockerPassFileReader;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.provider.LockerPassProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static cleancode.studycafe.tobe.model.pass.StudyCafePassType.*;
import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeLockerPassesTest {
    private final LockerPassProvider lockerPassProvider = new LockerPassFileReader();

    @Test
    @DisplayName("시간 단위 이용권은 사물함을 사용할 수 없다.")
    void hourlyPassCannotUseLocker() {
        // given
        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(HOURLY, 1, 1, 0.0);
        StudyCafeLockerPasses lockerPasses = lockerPassProvider.getLockerPasses();

        // when
        Optional<StudyCafeLockerPass> lockerPass = lockerPasses.findLockerPassBy(studyCafeSeatPass);

        // then
        assertThat(lockerPass).isEmpty();
    }

    @Test
    @DisplayName("주 단위 이용권은 사물함을 사용할 수 없다.")
    void weeklyPassCannotUseLocker() {
        // given
        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(WEEKLY, 1, 1, 0.0);
        StudyCafeLockerPasses lockerPasses = lockerPassProvider.getLockerPasses();

        // when
        Optional<StudyCafeLockerPass> lockerPass = lockerPasses.findLockerPassBy(studyCafeSeatPass);

        // then
        assertThat(lockerPass).isEmpty();
    }

    @Test
    @DisplayName("1인 고정석을 선택하면 1인 고정석 이용 기간과 동일한 사물함을 이용할 수 있다.")
    void fixedPassCanUseSameDurationLocker() {
        // given
        StudyCafeSeatPass fourWeeksOfPass = StudyCafeSeatPass.of(FIXED, 4, 10000, 0.1);
        StudyCafeSeatPass twelveWeeksOfPass = StudyCafeSeatPass.of(FIXED, 12, 10000, 0.1);
        StudyCafeLockerPasses lockerPasses = lockerPassProvider.getLockerPasses();

        // when
        Optional<StudyCafeLockerPass> fourWeeksOfLockerPass = lockerPasses.findLockerPassBy(fourWeeksOfPass);
        Optional<StudyCafeLockerPass> twelveWeeksOfLockerPass = lockerPasses.findLockerPassBy(twelveWeeksOfPass);

        // then
        assertThat(fourWeeksOfLockerPass).isPresent().get()
            .extracting("passType", "duration", "price")
            .contains(FIXED, 4, 10000);
        assertThat(twelveWeeksOfLockerPass).isPresent().get()
            .extracting("passType", "duration", "price")
            .contains(FIXED, 12, 30000);
    }
}