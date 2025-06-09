package cleancode.studycafe.tobe.model.order;

import cleancode.studycafe.tobe.io.provider.SeatPassFileReader;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.provider.SeatPassProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static cleancode.studycafe.tobe.model.pass.StudyCafePassType.FIXED;
import static cleancode.studycafe.tobe.model.pass.StudyCafePassType.WEEKLY;
import static org.assertj.core.api.Assertions.*;

class StudyCafePassOrderTest {

    /**
     * "2주권 이상은 10% 할인이 적용된다." -> 이름을 바꾸자
     * 1. seatPassProvider 의존 -> 일급 컬렉션에서 duration == 2 인 이용권 불러와서 테스트
     * 2. of 메서드를 통해 직접 객체를 생성하여 테스트
     *    - 생성 시 할인률을 0.1로 직접 설정하기 때문에 2주권 이상만 할인된다는 것이 명확하지 않음
     *
     * "할인이 적용되는 이용권의 총 결제 금액은 최종 금액에서 할인 금액이 차감된 값이어야 한다."
     * - '2주권 이상 할인'은 도메인 로직 상의 기능이 아닌 CSV 에서 읽어오는 값이 기준이므로 중요하지 않다고 판단
     * - 할인률을 가지는 이용권들을 할인이 되는지 직관적으로 확인 가능
     */
    @Test
    @DisplayName("할인이 적용되는 이용권의 총 결제 금액은 최종 금액에서 할인 금액이 차감된 값이어야 한다.")
    void discountPass() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(WEEKLY, 2, 100000, 0.1);
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, null);

        // when
        int totalPrice = order.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(90000);
    }

    @Test
    @DisplayName("사물함 이용 금액은 할인이 적용되지 않으며 총 결제 금액에 합산되어야 한다.")
    void includeLockerPriceInTotalPriceWhenLockerPurchased() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(WEEKLY, 4, 150000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(FIXED, 4, 10000);
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);

        // when
        int totalPrice = order.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(145000);
    }
}