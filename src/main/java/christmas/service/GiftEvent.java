package christmas.service;

import java.util.HashMap;
import java.util.Map;

public class GiftEvent {

    public static final String CHAMPAGNE = "샴페인";
    public static final int CHAMPAGNE_THRESHOLD = 120000;
    public static final int ONE = 1;

    public static Map<String, Integer> determineGift(int totalOrderAmount) {
        Map<String, Integer> giftAndQuantity = new HashMap<>();

        if (totalOrderAmount >= CHAMPAGNE_THRESHOLD) {
            giftAndQuantity.put(CHAMPAGNE, ONE);
        }
        return giftAndQuantity;
    }

}