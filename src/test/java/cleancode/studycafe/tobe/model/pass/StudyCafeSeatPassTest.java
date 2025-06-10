package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cleancode.studycafe.tobe.model.pass.StudyCafePassType.*;
import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeSeatPassTest {

    @Test
    @DisplayName("좌석 이용권과 사물함 이용권의 종류와 기간이 같으면 true를 반환한다.")
    void isSameDurationType() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(FIXED, 2, 4, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(FIXED, 2, 8);

        // when
        boolean result = seatPass.isSameDurationType(lockerPass);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("사물함을 사용할 수 있는 이용권은 true가 반환되고, 사용할 수 없는 이용권은 false가 반환된다.")
    void cannotUseLocker() {
        // given
        StudyCafeSeatPass hourlySeatPass = StudyCafeSeatPass.of(HOURLY, 2, 4, 0.1);
        StudyCafeSeatPass weeklySeatPass = StudyCafeSeatPass.of(WEEKLY, 2, 4, 0.1);
        StudyCafeSeatPass fixedSeatPass = StudyCafeSeatPass.of(FIXED, 2, 4, 0.1);

        // when
        boolean result1 = hourlySeatPass.cannotUseLocker();
        boolean result2 = weeklySeatPass.cannotUseLocker();
        boolean result3 = fixedSeatPass.cannotUseLocker();

        // then
        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
        assertThat(result3).isFalse();
    }
}