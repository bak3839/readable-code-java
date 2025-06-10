package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.io.provider.SeatPassFileReader;
import cleancode.studycafe.tobe.provider.SeatPassProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static cleancode.studycafe.tobe.model.pass.StudyCafePassType.*;
import static cleancode.studycafe.tobe.model.pass.StudyCafePassType.FIXED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class StudyCafeSeatPassesTest {

    private final StudyCafeSeatPasses seatPasses = StudyCafeSeatPasses.of(
        List.of(
            StudyCafeSeatPass.of(HOURLY, 2, 4000, 0.0),
            StudyCafeSeatPass.of(HOURLY, 4, 6500, 0.0),
            StudyCafeSeatPass.of(HOURLY, 6, 9000, 0.0),
            StudyCafeSeatPass.of(HOURLY, 8, 11000, 0.0),
            StudyCafeSeatPass.of(HOURLY, 10, 12000, 0.0),
            StudyCafeSeatPass.of(HOURLY, 12, 13000, 0.0),
            StudyCafeSeatPass.of(WEEKLY, 1, 60000, 0.0),
            StudyCafeSeatPass.of(WEEKLY, 2, 100000, 0.0),
            StudyCafeSeatPass.of(WEEKLY, 3, 130000, 0.0),
            StudyCafeSeatPass.of(WEEKLY, 4, 150000, 0.0),
            StudyCafeSeatPass.of(WEEKLY, 12, 400000, 0.0),
            StudyCafeSeatPass.of(FIXED, 4, 250000, 0.0),
            StudyCafeSeatPass.of(FIXED, 12, 700000, 0.0)
        )
    );

    /**
     * Q. 이용권 종류별로 테스트를 작성하지 않고, 하나의 종류에 대해서만 테스트 해도 되는지
     *    - 궁극적인 목표는 '원하는 종류만 필터링'이 목표이므로 하나의 종류만 해도 되는 것일까
     *    - DisplayName : 전체 이용권 목록 중에서 지정한 이용권 종류에 맞는 이용권 목록만 필터링되어 조회된다.
     */
    @Test
    @DisplayName("시간 단위 이용권을 선택하면 시간권 목록만 필터링하여 조회되어야 한다.")
    void findPassByHourly() {
        // given
        StudyCafePassType type = HOURLY;

        // when
        List<StudyCafeSeatPass> findPasses = seatPasses.findPassBy(type);

        // then
        assertThat(findPasses).hasSize(6)
            .extracting("passType", "duration", "price")
            .containsExactlyInAnyOrder(
                tuple(HOURLY, 2, 4000),
                tuple(HOURLY, 4, 6500),
                tuple(HOURLY, 6, 9000),
                tuple(HOURLY, 8, 11000),
                tuple(HOURLY, 10, 12000),
                tuple(HOURLY, 12, 13000)
            );
    }

    @Test
    @DisplayName("주 단위 이용권을 선택하면 주권 목록만 필터링하여 조회되어야 한다.")
    void findPassByWeekly() {
        // given
        StudyCafePassType type = WEEKLY;

        // when
        List<StudyCafeSeatPass> findPasses = seatPasses.findPassBy(type);

        // then
        assertThat(findPasses).hasSize(5)
            .extracting("passType", "duration", "price")
            .containsExactlyInAnyOrder(
                tuple(WEEKLY, 1, 60000),
                tuple(WEEKLY, 2, 100000),
                tuple(WEEKLY, 3, 130000),
                tuple(WEEKLY, 4, 150000),
                tuple(WEEKLY, 12, 400000)
            );
    }

    @Test
    @DisplayName("1인 고정석을 선택하면 고정석 목록만 필터링하여 조회되어야 한다.")
    void findPassByFixed() {
        // given
        StudyCafePassType type = FIXED;

        // when
        List<StudyCafeSeatPass> findPasses = seatPasses.findPassBy(type);

        // then
        assertThat(findPasses).hasSize(2)
            .extracting("passType", "duration", "price")
            .containsExactlyInAnyOrder(
                tuple(FIXED, 4, 250000),
                tuple(FIXED, 12, 700000)
            );
    }
}