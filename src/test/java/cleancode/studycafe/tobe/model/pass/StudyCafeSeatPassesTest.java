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

    private final SeatPassProvider seatPassProvider = new SeatPassFileReader();

    /**
     * 만약 시간권 종류가 추가된다면 테스트가 깨지게 될텐데 이 테스트 방식이 옳은 방식일까
     */
    @Test
    @DisplayName("시간 단위 이용권을 선택하면 시간권 목록만 필터링하여 조회되어야 한다.")
    void findPassByHourly() {
        // given
        StudyCafePassType type = HOURLY;
        StudyCafeSeatPasses seatPasses = seatPassProvider.getSeatPasses();

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
        StudyCafeSeatPasses seatPasses = seatPassProvider.getSeatPasses();

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
        StudyCafeSeatPasses seatPasses = seatPassProvider.getSeatPasses();

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