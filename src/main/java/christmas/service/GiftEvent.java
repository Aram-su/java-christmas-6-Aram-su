package christmas.service;

import christmas.model.Constants;
import java.util.HashMap;
import java.util.Map;

public class GiftEvent {

    public static Map<String, Integer> determineGift(int totalOrderAmount) {
        Map<String, Integer> giftAndQuantity = new HashMap<>();

        if (totalOrderAmount >= Constants.CHAMPAGNE_THRESHOLD) {
            giftAndQuantity.put(Constants.CHAMPAGNE, Constants.ONE);
        }
        return giftAndQuantity;
    }

}