package christmas.service;

import christmas.model.Reservation;
import java.util.HashMap;
import java.util.Map;

public class DiscountEvent {

    private static final String D_DAY_DISCOUNT = "크리스마스 디데이 할인";
    private static final int EVENT_THRESHOLD = 10000;
    private static final int CHRISTMAS_D_DAY = 25;
    private static final int DEFAULT_DISCOUNT_AMOUNT = 900;
    private static final int INCREMENT_AMOUNT = 100;

    public static Map<String, Integer> applyDiscount(Reservation reservation) {
        Map<String, Integer> discountHistory = new HashMap<>();
        int totalOrderAmount = reservation.getTotalOrderAmount();

        if (totalOrderAmount >= EVENT_THRESHOLD) {
            applyChristmasDdayDiscount(reservation, discountHistory);
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

}