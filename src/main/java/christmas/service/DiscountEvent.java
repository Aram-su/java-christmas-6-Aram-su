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
    private static final String SPECIAL_DISCOUNT = "특별 할인";
    private static final String TYPE_DESSERT = "dessert";
    private static final String TYPE_MAIN = "main";
    private static final int EVENT_THRESHOLD = 10000;
    private static final int CHRISTMAS_D_DAY = 25;
    private static final int DEFAULT_DISCOUNT_AMOUNT = 900;
    private static final int INCREMENT_AMOUNT = 100;
    private static final int WEEKDAY_DISCOUNT_AMOUNT = 2023;
    private static final int WEEKEND_DISCOUNT_AMOUNT = 2023;
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;
    private static final int DAYS_IN_A_WEEK = 7;
    private static final int SUNDAY = 3;
    private static final int MONDAY = 4;
    private static final int TUESDAY = 5;
    private static final int WEDNESDAY = 6;
    private static final int THURSDAY = 0;
    private static final int ZERO = 0;
    private static final int DISCOUNT_THRESHOLD = 0;
    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private static final List<Integer> WEEK_DAYS = List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY);

    public static Map<String, Integer> applyDiscount(Reservation reservation) {
        Map<String, Integer> discountAndAmount = new HashMap<>();
        int totalOrderAmount = reservation.getTotalOrderAmount();

        if (totalOrderAmount >= EVENT_THRESHOLD) {
            applyChristmasDdayDiscount(reservation, discountAndAmount);
            applyWeekdayDiscount(reservation, discountAndAmount);
            applyWeekendDiscount(reservation, discountAndAmount);
            applySpecialDiscount(reservation, discountAndAmount);
        }
        return discountAndAmount;
    }

    private static void applyChristmasDdayDiscount(Reservation reservation, Map<String, Integer> discountAndAmount) {
        int reservationDate = reservation.getReservationDate();

        if (reservationDate <= CHRISTMAS_D_DAY) {
            int discountAmount = DEFAULT_DISCOUNT_AMOUNT + reservationDate * INCREMENT_AMOUNT;

            updateDiscountAndAmount(discountAndAmount, D_DAY_DISCOUNT, discountAmount);
        }
    }

    private static void applyWeekdayDiscount(Reservation reservation, Map<String, Integer> discountAndAmount) {
        int reservationDate = reservation.getReservationDate();

        if (isWeekDay(reservationDate)) {
            int dessertCount = countDessert(reservation.getMenuAndQuantity());
            int discountAmount = WEEKDAY_DISCOUNT_AMOUNT * dessertCount;

            updateDiscountAndAmount(discountAndAmount, WEEKDAY_DISCOUNT, discountAmount);
        }
    }

    private static void applyWeekendDiscount(Reservation reservation, Map<String, Integer> discountAndAmount) {
        int reservationDate = reservation.getReservationDate();

        if (!isWeekDay(reservationDate)) {
            int mainCount = countMain(reservation.getMenuAndQuantity());
            int discountAmount = WEEKEND_DISCOUNT_AMOUNT * mainCount;

            updateDiscountAndAmount(discountAndAmount, WEEKEND_DISCOUNT, discountAmount);
        }
    }

    private static void applySpecialDiscount(Reservation reservation, Map<String, Integer> discountAndAmount) {
        int reservationDate = reservation.getReservationDate();

        if (isSpecialDay(reservationDate)) {
            updateDiscountAndAmount(discountAndAmount, SPECIAL_DISCOUNT, SPECIAL_DISCOUNT_AMOUNT);
        }
    }

    private static boolean isSpecialDay(int reservationDate) {
        return SPECIAL_DAYS.contains(reservationDate);
    }

    private static void updateDiscountAndAmount(Map<String, Integer> discountAndAmount, String discountType,
        int discountAmount) {
        if (discountAmount > DISCOUNT_THRESHOLD) {
            discountAndAmount.put(discountType, discountAmount);
        }
    }

    private static boolean isWeekDay(int reservationDate) {
        return WEEK_DAYS.contains(calculateDayOfWeek(reservationDate));
    }

    private static int calculateDayOfWeek(int reservationDate) {
        return reservationDate % DAYS_IN_A_WEEK;
    }

    private static int countDessert(Map<String, Integer> menuAndQuantity) {
        int cnt = ZERO;
        for (String menu : menuAndQuantity.keySet()) {
            if (isDessert(menu)) {
                cnt += getMenuQuantity(menuAndQuantity, menu);
            }
        }
        return cnt;
    }

    private static boolean isDessert(String menu) {
        return Objects.equals(Menu.getTypeByName(menu), TYPE_DESSERT);
    }

    private static int countMain(Map<String, Integer> menuAndQuantity) {
        int cnt = ZERO;
        for (String menu : menuAndQuantity.keySet()) {
            if (isMain(menu)) {
                cnt += getMenuQuantity(menuAndQuantity, menu);
            }
        }
        return cnt;
    }

    private static boolean isMain(String menu) {
        return Objects.equals(Menu.getTypeByName(menu), TYPE_MAIN);
    }

    private static int getMenuQuantity(Map<String, Integer> menuAndQuantity, String menu) {
        return menuAndQuantity.get(menu);
    }

}