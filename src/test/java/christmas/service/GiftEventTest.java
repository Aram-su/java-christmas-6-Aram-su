package christmas.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GiftEventTest {

    @DisplayName("할인 전 총 주문 금액이 12만원 이상일 때 샴페인 1개 증정한다.")
    @Test
    void determineGift_TotalOrderAmount_More_Than_120000() {
        assertThat(GiftEvent.determineGift(120000))
            .isEqualTo("샴페인 1개");
    }

    @DisplayName("할인 전 총 주문 금액이 12만원 미만일 때는 아무것도 증정하지 않는다.")
    @Test
    void determineGift_TotalOrderAmount_Less_Than_120000() {
        assertThat(GiftEvent.determineGift(119999))
            .isEqualTo("없음");
    }

}