package christmas.service;

import christmas.model.Reservation;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountEventTest {

    private static final String D_DAY_DISCOUNT = "크리스마스 디데이 할인";
    private static final String WEEKDAY_DISCOUNT = "평일 할인";
    private static final String WEEKEND_DISCOUNT = "주말 할인";

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

    @DisplayName("평일 할인은 일요일 ~ 목요일이고, 디저트 메뉴가 1개면 2,023원 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14})
    void applyWeekdayDiscount_WeekDay_Dessert_1(int reservationDate) {
        Map<String, Integer> order = new HashMap<>();
        order.put("초코케이크", 1);
        Reservation reservation = new Reservation(reservationDate, order);

        Map<String, Integer> result = DiscountEvent.applyDiscount(reservation);

        assertEquals(2023, result.get(WEEKDAY_DISCOUNT));
    }

    @DisplayName("평일 할인은 일요일 ~ 목요일이고, 디저트 메뉴가 2개면 4,046원 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {17, 18, 19, 20, 21, 24, 25, 26, 27, 28})
    void applyWeekdayDiscount_WeekDay_Dessert_2(int reservationDate) {
        Map<String, Integer> order = new HashMap<>();
        order.put("초코케이크", 2);
        Reservation reservation = new Reservation(reservationDate, order);

        Map<String, Integer> result = DiscountEvent.applyDiscount(reservation);

        assertEquals(2023 * 2, result.get(WEEKDAY_DISCOUNT));
    }

    @DisplayName("평일 할인은 금요일, 토요일에는 적용되지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9})
    void applyWeekdayDiscount_Weekend_No_Discount(int reservationDate) {
        Map<String, Integer> order = new HashMap<>();
        order.put("초코케이크", 1);
        Reservation reservation = new Reservation(reservationDate, order);

        Map<String, Integer> result = DiscountEvent.applyDiscount(reservation);

        assertEquals(null, result.get(WEEKDAY_DISCOUNT));
    }

    @DisplayName("주말 할인은 금요일, 토요일이고 메인 메뉴가 1개면 2,023원 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9})
    void applyWeekendDiscount_Weekend_Main_1(int reservationDate) {
        Map<String, Integer> order = new HashMap<>();
        order.put("티본스테이크", 1);
        Reservation reservation = new Reservation(reservationDate, order);

        Map<String, Integer> result = DiscountEvent.applyDiscount(reservation);

        assertEquals(2023, result.get(WEEKEND_DISCOUNT));
    }

    @DisplayName("주말 할인은 금요일, 토요일이고 메인 메뉴가 2개면 4,026원 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {15, 16, 22, 23})
    void applyWeekendDiscount_Weekend_Main_2(int reservationDate) {
        Map<String, Integer> order = new HashMap<>();
        order.put("티본스테이크", 2);
        Reservation reservation = new Reservation(reservationDate, order);

        Map<String, Integer> result = DiscountEvent.applyDiscount(reservation);

        assertEquals(2023 * 2, result.get(WEEKEND_DISCOUNT));
    }

    @DisplayName("주말 할인은 일요일 ~ 목요일이면 적용되지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14})
    void applyWeekendDiscount_Weekday_No_discount(int reservationDate) {
        Map<String, Integer> order = new HashMap<>();
        order.put("티본스테이크", 1);
        Reservation reservation = new Reservation(reservationDate, order);

        Map<String, Integer> result = DiscountEvent.applyDiscount(reservation);

        assertEquals(null, result.get(WEEKEND_DISCOUNT));
    }

    @DisplayName("평일 할인은 일요일 ~ 목요일이지만, 디저트 메뉴가 없으면 할인하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14})
    void applyWeekdayDiscount_WeekDay_No_Dessert(int reservationDate) {
        Map<String, Integer> order = new HashMap<>();
        order.put("티본스테이크", 1);
        Reservation reservation = new Reservation(reservationDate, order);

        Map<String, Integer> result = DiscountEvent.applyDiscount(reservation);

        assertEquals(null, result.get(WEEKDAY_DISCOUNT));
    }

    @DisplayName("주말 할인은 금요일, 토요일이지만 메인 메뉴가 없으면 할인하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9})
    void applyWeekendDiscount_Weekend_No_Main(int reservationDate) {
        Map<String, Integer> order = new HashMap<>();
        order.put("초코케이크", 1);
        Reservation reservation = new Reservation(reservationDate, order);

        Map<String, Integer> result = DiscountEvent.applyDiscount(reservation);

        assertEquals(null, result.get(WEEKEND_DISCOUNT));
    }

}