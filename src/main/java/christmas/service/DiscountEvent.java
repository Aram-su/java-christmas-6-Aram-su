package christmas.service;

import christmas.model.Menu;
import christmas.model.Reservation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DiscountEvent {

    private static final String D_DAY_DISCOUNT = "크리스마스 디데이 할인";
    private static final String WEEKDAY_DISCOUNT = "평일 할인";
    private static final String WEEKEND_DISCOUNT = "주말 할인";
    private static final String TYPE_DESSERT = "dessert";
    private static final String TYPE_MAIN = "main";
    private static final int EVENT_THRESHOLD = 10000;
    private static final int CHRISTMAS_D_DAY = 25;
    private static final int DEFAULT_DISCOUNT_AMOUNT = 900;
    private static final int INCREMENT_AMOUNT = 100;
    private static final int WEEKDAY_DISCOUNT_AMOUNT = 2023;
    private static final int WEEKEND_DISCOUNT_AMOUNT = 2023;
    private static final int DAYS_IN_A_WEEK = 7;
    private static final int SUNDAY = 3;
    private static final int MONDAY = 4;
    private static final int TUESDAY = 5;
    private static final int WEDNESDAY = 6;
    private static final int THURSDAY = 0;
    private static final int ZERO = 0;

    public static Map<String, Integer> applyDiscount(Reservation reservation) {
        Map<String, Integer> discountHistory = new HashMap<>();
        int totalOrderAmount = reservation.getTotalOrderAmount();

        if (totalOrderAmount >= EVENT_THRESHOLD) {
            applyChristmasDdayDiscount(reservation, discountHistory);
            applyWeekdayDiscount(reservation, discountHistory);
            applyWeekendDiscount(reservation, discountHistory);
        }
        return discountHistory;
    }

    private static void applyChristmasDdayDiscount(Reservation reservation, Map<String, Integer> discountHistory) {
        int reservationDate = reservation.getReservationDate();

        if (reservationDate <= CHRISTMAS_D_DAY) {
            int discountAmount = DEFAULT_DISCOUNT_AMOUNT + reservationDate * INCREMENT_AMOUNT;

            discountHistory.put(D_DAY_DISCOUNT, discountAmount);
        }
    }

    private static void applyWeekdayDiscount(Reservation reservation, Map<String, Integer> discountHistory) {
        int reservationDate = reservation.getReservationDate();

        if (isWeekDay(reservationDate)) {
            int dessertCount = countDessert(reservation.getOrder());
            int discountAmount = WEEKDAY_DISCOUNT_AMOUNT * dessertCount;

            discountHistory.put(WEEKDAY_DISCOUNT, discountAmount);
        }
    }

    private static void applyWeekendDiscount(Reservation reservation, Map<String, Integer> discountHistory) {
        int reservationDate = reservation.getReservationDate();

        if (!isWeekDay(reservationDate)) {
            int mainCount = countMain(reservation.getOrder());
            int discountAmount = WEEKEND_DISCOUNT_AMOUNT * mainCount;

            discountHistory.put(WEEKEND_DISCOUNT, discountAmount);
        }
    }

    private static boolean isWeekDay(int reservationDate) {
        List<Integer> weekDay = List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY);
        return weekDay.contains(calculateDayOfWeek(reservationDate));
    }

    private static int calculateDayOfWeek(int reservationDate) {
        return reservationDate % DAYS_IN_A_WEEK;
    }

    private static int countDessert(Map<String, Integer> order) {
        int cnt = ZERO;
        for (String menu : order.keySet()) {
            if (isDessert(menu)) {
                cnt += getMenuQuantity(order, menu);
            }
        }
        return cnt;
    }

    private static boolean isDessert(String menu) {
        return Objects.equals(Menu.getTypeByName(menu), TYPE_DESSERT);
    }

    private static int countMain(Map<String, Integer> order) {
        int cnt = ZERO;
        for (String menu : order.keySet()) {
            if (isMain(menu)) {
                cnt += getMenuQuantity(order, menu);
            }
        }
        return cnt;
    }

    private static boolean isMain(String menu) {
        return Objects.equals(Menu.getTypeByName(menu), TYPE_MAIN);
    }

    private static int getMenuQuantity(Map<String, Integer> order, String menu) {
        return order.get(menu);
    }

}