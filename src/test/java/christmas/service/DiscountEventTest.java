package christmas.service;

import christmas.model.Reservation;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountEventTest {

    private static final String D_DAY_DISCOUNT = "크리스마스 디데이 할인";

    @DisplayName("크리스마스 디데이 할인은 1일에 1000원 할인한다.")
    @Test
    void applyChristmasDdayDiscount_Day_01() {
        int reservationDate = 1;
        Map<String, Integer> order = new HashMap<>();
        order.put("티본스테이크", 1);
        Reservation reservation = new Reservation(reservationDate, order);

        Map<String, Integer> result = DiscountEvent.applyDiscount(reservation);

        assertEquals(1000, result.get(D_DAY_DISCOUNT));
    }

    @DisplayName("크리스마스 디데이 할인은 25일에 3400원 할인한다.")
    @Test
    void applyChristmasDdayDiscount_Day_25() {
        int reservationDate = 25;
        Map<String, Integer> order = new HashMap<>();
        order.put("티본스테이크", 1);
        Reservation reservation = new Reservation(reservationDate, order);

        Map<String, Integer> result = DiscountEvent.applyDiscount(reservation);

        assertEquals(3400, result.get(D_DAY_DISCOUNT));
    }

    @DisplayName("크리스마스 디데이 할인은 25일이 넘어가면 할인을 하지 않는다.")
    @Test
    void applyChristmasDdayDiscount_Day_Over_25() {
        int reservationDate = 26;
        Map<String, Integer> order = new HashMap<>();
        order.put("티본스테이크", 1);
        Reservation reservation = new Reservation(reservationDate, order);

        Map<String, Integer> result = DiscountEvent.applyDiscount(reservation);

        assertEquals(null, result.get(D_DAY_DISCOUNT));
    }

    @DisplayName("크리스마스 디데이 할인은 1~25일 사이여도 총주문 금액이 10,000원 미만 인 경우 할인하지 않는다.")
    @Test
    void applyChristmasDdayDiscount_Day_25_But_TotalOrderAmount_Less_Than_10000() {
        int reservationDate = 25;
        Map<String, Integer> order = new HashMap<>();
        order.put("타파스", 1);
        Reservation reservation = new Reservation(reservationDate, order);

        Map<String, Integer> result = DiscountEvent.applyDiscount(reservation);

        assertEquals(null, result.get(D_DAY_DISCOUNT));
    }

}