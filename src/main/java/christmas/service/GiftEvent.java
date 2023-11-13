package christmas.service;

public class GiftEvent {

    public static final String ONE_CHAMPAGNE = "샴페인 1개";
    public static final String NONE = "없음";
    public static final int CHAMPAGNE_THRESHOLD = 120000;

    public static String determineGift(int totalOrderAmount) {
        if (totalOrderAmount >= CHAMPAGNE_THRESHOLD) {
            return ONE_CHAMPAGNE;
        }
        return NONE;
    }

}