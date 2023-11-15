package christmas.service;

import christmas.model.Reservation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EventTest {

    private static final String NONE = "없음";

    @DisplayName("이벤트가 적용되면 Null 값을 반환하지 않는다.")
    @Test
    void applyEvent() {
        int reservationDate = 25;
        Map<String, Integer> menuAndQuantity = new HashMap<>();
        menuAndQuantity.put("티본스테이크", 1);
        Reservation reservation = new Reservation(reservationDate, menuAndQuantity);

        Event.applyEvent(reservation);

        assertNotNull(reservation.getGiftAndQuantityDetails());
        assertNotNull(reservation.getEventAndAmountDetails());
        assertNotNull(reservation.getBadge());
    }

    @DisplayName("이벤트가 적용되지 않으면 모두 없음을 반환한다.")
    @Test
    void applyEvent_No_Apply() {
        int reservationDate = 25;
        Map<String, Integer> menuAndQuantity = new HashMap<>();
        menuAndQuantity.put("티본스테이크", 1);
        Reservation reservation = new Reservation(reservationDate, menuAndQuantity);

        assertEquals(List.of(NONE), reservation.getGiftAndQuantityDetails());
        assertEquals(List.of(NONE), reservation.getEventAndAmountDetails());
        assertEquals(NONE, reservation.getBadge());
    }
}