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
     * "2주권 이상은 10% 할인이 적용된다."
     * 1. 일급 컬렉션에서 duration == 2 인 이용권 불러와서 테스트
     *    - seatPassProvider 의존
     * 2. of 메서드를 통해 직접 객체를 생성하여 테스트
     *    - 생성 시 할인률을 0.1로 직접 설정하기 때문에 2주권 이상만 할인된다는 것이 명확하지 않음
     * - 이름을 바꾸자 -
     */
    // TODO: Q3. 이용권 할인 적용 테스트
    @Test
    @DisplayName("할인이 적용되는 이용권의 총 결제 금액은 이용권 가격에서 할인 가격이 차감된 값이어야 한다.")
    void totalPriceIsPassPriceSubtractDiscountPrice() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(WEEKLY, 2, 100000, 0.1);
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, null);

        // when
        int totalPrice = order.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(90000);
    }

    @Test
    @DisplayName("사물함 이용 가격은 할인이 적용되지 않으며 총 결제 금액에 합산되어야 한다.")
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