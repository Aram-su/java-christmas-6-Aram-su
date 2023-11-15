package christmas.service;

import christmas.model.Constants;
import christmas.model.Menu;
import christmas.model.Reservation;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DiscountEvent {

    public static Map<String, Integer> applyDiscount(Reservation reservation) {
        Map<String, Integer> discountAndAmount = new HashMap<>();
        int totalOrderAmount = reservation.getTotalOrderAmount();

        if (totalOrderAmount >= Constants.EVENT_THRESHOLD) {
            applyChristmasDdayDiscount(reservation, discountAndAmount);
            applyWeekdayDiscount(reservation, discountAndAmount);
            applyWeekendDiscount(reservation, discountAndAmount);
            applySpecialDiscount(reservation, discountAndAmount);
        }
        return discountAndAmount;
    }

    private static void applyChristmasDdayDiscount(Reservation reservation, Map<String, Integer> discountAndAmount) {
        int reservationDate = reservation.getReservationDate();

        if (reservationDate <= Constants.CHRISTMAS_D_DAY) {
            int discountAmount = Constants.DEFAULT_DISCOUNT_AMOUNT + reservationDate * Constants.INCREMENT_AMOUNT;

            updateDiscountAndAmount(discountAndAmount, Constants.D_DAY_DISCOUNT, discountAmount);
        }
    }

    private static void applyWeekdayDiscount(Reservation reservation, Map<String, Integer> discountAndAmount) {
        int reservationDate = reservation.getReservationDate();

        if (isWeekDay(reservationDate)) {
            int dessertCount = countDessert(reservation.getMenuAndQuantity());
            int discountAmount = Constants.WEEKDAY_DISCOUNT_AMOUNT * dessertCount;

            updateDiscountAndAmount(discountAndAmount, Constants.WEEKDAY_DISCOUNT, discountAmount);
        }
    }

    private static void applyWeekendDiscount(Reservation reservation, Map<String, Integer> discountAndAmount) {
        int reservationDate = reservation.getReservationDate();

        if (!isWeekDay(reservationDate)) {
            int mainCount = countMain(reservation.getMenuAndQuantity());
            int discountAmount = Constants.WEEKEND_DISCOUNT_AMOUNT * mainCount;

            updateDiscountAndAmount(discountAndAmount, Constants.WEEKEND_DISCOUNT, discountAmount);
        }
    }

    private static void applySpecialDiscount(Reservation reservation, Map<String, Integer> discountAndAmount) {
        int reservationDate = reservation.getReservationDate();

        if (isSpecialDay(reservationDate)) {
            updateDiscountAndAmount(discountAndAmount, Constants.SPECIAL_DISCOUNT, Constants.SPECIAL_DISCOUNT_AMOUNT);
        }
    }

    private static boolean isSpecialDay(int reservationDate) {
        return Constants.SPECIAL_DAYS.contains(reservationDate);
    }

    private static void updateDiscountAndAmount(Map<String, Integer> discountAndAmount, String discountType,
        int discountAmount) {
        if (discountAmount > Constants.DISCOUNT_THRESHOLD) {
            discountAndAmount.put(discountType, discountAmount);
        }
    }

    private static boolean isWeekDay(int reservationDate) {
        return Constants.WEEK_DAYS.contains(calculateDayOfWeek(reservationDate));
    }

    private static int calculateDayOfWeek(int reservationDate) {
        return reservationDate % Constants.DAYS_IN_A_WEEK;
    }

    private static int countDessert(Map<String, Integer> menuAndQuantity) {
        int cnt = Constants.ZERO;
        for (String menu : menuAndQuantity.keySet()) {
            if (isDessert(menu)) {
                cnt += getMenuQuantity(menuAndQuantity, menu);
            }
        }
        return cnt;
    }

    private static boolean isDessert(String menu) {
        return Objects.equals(Menu.getTypeByName(menu), Constants.TYPE_DESSERT);
    }

    private static int countMain(Map<String, Integer> menuAndQuantity) {
        int cnt = Constants.ZERO;
        for (String menu : menuAndQuantity.keySet()) {
            if (isMain(menu)) {
                cnt += getMenuQuantity(menuAndQuantity, menu);
            }
        }
        return cnt;
    }

    private static boolean isMain(String menu) {
        return Objects.equals(Menu.getTypeByName(menu), Constants.TYPE_MAIN);
    }

    private static int getMenuQuantity(Map<String, Integer> menuAndQuantity, String menu) {
        return menuAndQuantity.get(menu);
    }

}