package christmas.model;

import christmas.service.Event;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationTest {

    private static final String NONE = "없음";
    private static final int reservationDate = 25;
    private static final Map<String, Integer> menuAndQuantity = new HashMap<>();
    private static Reservation reservation;

    @BeforeAll
    static void initReservationAndApplyEvent() {
        menuAndQuantity.put("티본스테이크", 1); //55000
        menuAndQuantity.put("초코케이크", 1); //15000
        menuAndQuantity.put("제로콜라", 1); //3000
        reservation = new Reservation(reservationDate, menuAndQuantity);
        Event.applyEvent(reservation);
    }

    @DisplayName("예약으로부터 이벤트 날 메시지 가져오기")
    @Test
    void getReservationDateEventPreview_Test() {
        String preview = reservation.getReservationDateEventPreview();
        assertEquals("12월 25일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", preview);
    }

    @DisplayName("예약으로부터 할인 전 총주문 금액 가져오기")
    @Test
    void getTotalOrderAmount_Test() {
        int totalOrderAmount = reservation.getTotalOrderAmount();
        assertEquals(73000, totalOrderAmount);
    }

    @DisplayName("예약으로부터 총혜택 금액 가져오기")
    @Test
    void getTotalEventAmount_Test() {
        int totalEventAmount = reservation.getTotalEventAmount();
        assertEquals(6423, totalEventAmount);
    }

    @DisplayName("예약으로부터 할인 후 예상 결제 금액 가져오기")
    @Test
    void getDiscountedAmount_Test() {
        int discountedAmount = reservation.getDiscountedAmount();
        assertEquals(66577, discountedAmount);
    }

    @DisplayName("예약으로부터 메뉴 정보 가져오기")
    @Test
    void getMenuAndQuantityDetails_Test() {
        List<String> menuAndQuantityDetails = reservation.getMenuAndQuantityDetails();
        assertThat(menuAndQuantityDetails)
            .contains("티본스테이크 1개", "초코케이크 1개", "제로콜라 1개");
    }

    @DisplayName("예약으로부터 증정품 정보 가져오기 - 증정품 없는 경우")
    @Test
    void getGiftAndQuantityDetails_No_Gift() {
        List<String> giftAndQuantityDetails = reservation.getGiftAndQuantityDetails();
        assertEquals(List.of(NONE), giftAndQuantityDetails);
    }

    @DisplayName("예약으로부터 혜택 내역 가져오기")
    @Test
    void getEventAndAmountDetails_Test() {
        List<String> eventAndAmountDetails = reservation.getEventAndAmountDetails();
        assertThat(eventAndAmountDetails)
            .contains("크리스마스 디데이 할인: -3,400원", "평일 할인: -2,023원", "특별 할인: -1,000원");
    }
}