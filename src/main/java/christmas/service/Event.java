package christmas.service;

import christmas.model.Constants;
import christmas.model.Menu;
import christmas.model.Reservation;
import java.util.Map;

public class Event {

    public static void applyEvent(Reservation reservation) {
        Map<String, Integer> giftAndQuantity = GiftEvent.determineGift(reservation.getTotalOrderAmount());
        reservation.setGiftAndQuantity(giftAndQuantity);

        Map<String, Integer> eventAndAmount = DiscountEvent.applyDiscount(reservation);
        if (!giftAndQuantity.isEmpty()) {
            updateEventAndAmount(eventAndAmount, giftAndQuantity);
        }
        reservation.setEventAndAmount(eventAndAmount);

        String badge = BadgeEvent.determineBadge(reservation.getTotalEventAmount());
        reservation.setBadge(badge);
    }

    private static void updateEventAndAmount(Map<String, Integer> eventAndAmount,
        Map<String, Integer> giftAndQuantity) {
        for (String gift : giftAndQuantity.keySet()) {
            int giftAmount = Menu.getPriceByName(gift) * giftAndQuantity.get(gift);
            eventAndAmount.put(Constants.GIFT_EVENT, giftAmount);
        }
    }

}