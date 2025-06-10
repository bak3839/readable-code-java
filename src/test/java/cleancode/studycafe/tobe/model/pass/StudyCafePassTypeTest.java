package cleancode.studycafe.tobe.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cleancode.studycafe.tobe.model.pass.StudyCafePassType.*;
import static org.assertj.core.api.Assertions.assertThat;
class StudyCafePassTypeTest {

    @Test
    @DisplayName("사물함을 사용할 수 있는 이용권 종류인지 확인한다.")
    void isLockerType() {
        // given

        // when
        boolean result = FIXED.isLockerType();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("사물함을 사용할 수 없는 이용권 종류인지 확인한다.")
    void isNotLockerType() {
        //given

        // when
        boolean result1 = HOURLY.isNotLockerType();
        boolean result2 = WEEKLY.isNotLockerType();

        // then
        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
    }
}