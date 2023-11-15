package christmas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reservation {

    private static final String DECEMBER = "12월 ";
    private static final String PREVIEW_BENEFITS = "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String WORD_SPACING = " ";
    private static final String COUNT_UNIT = "개";
    private static final String NUMBER_FORMAT = "%,d";
    private static final String WON = "원";
    private static final String COLON = ": ";
    private static final String NONE = "없음";
    private static final String GIFT_EVENT = "증정 이벤트";
    private static final int ZERO = 0;

    private final int reservationDate;
    private final Map<String, Integer> menuAndQuantity;
    private Map<String, Integer> giftAndQuantity;
    private Map<String, Integer> eventAndAmount;
    private String badge;

    public Reservation(int reservationDate, Map<String, Integer> menuAndQuantity) {
        this.reservationDate = reservationDate;
        this.menuAndQuantity = menuAndQuantity;
    }

    public int getReservationDate() {
        return reservationDate;
    }

    public Map<String, Integer> getMenuAndQuantity() {
        return menuAndQuantity;
    }

    public String getBadge() {
        if (badge == null) {
            return NONE;
        }
        return badge;
    }

    public void setGiftAndQuantity(Map<String, Integer> giftAndQuantity) {
        this.giftAndQuantity = giftAndQuantity;
    }

    public void setEventAndAmount(Map<String, Integer> eventAndAmount) {
        this.eventAndAmount = eventAndAmount;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getReservationDateEventPreview() {
        return DECEMBER + reservationDate + PREVIEW_BENEFITS;
    }

    public int getTotalOrderAmount() {
        int totalOrderAmount = ZERO;
        for (String menu : menuAndQuantity.keySet()) {
            totalOrderAmount += menuAndQuantity.get(menu) * Menu.getPriceByName(menu);
        }
        return totalOrderAmount;
    }

    public int getTotalEventAmount() {
        int totalEventAmount = ZERO;
        for (String event : eventAndAmount.keySet()) {
            totalEventAmount += eventAndAmount.get(event);
        }
        return totalEventAmount;
    }

    public int getDiscountedAmount() {
        int discountAmount = getTotalEventAmount();
        if (eventAndAmount.containsKey(GIFT_EVENT)) {
            discountAmount -= eventAndAmount.get(GIFT_EVENT);
        }
        return getTotalOrderAmount() - discountAmount;
    }

    public List<String> getMenuAndQuantityDetails() {
        List<String> menuAndQuantityDetails = new ArrayList<>();
        generateMenuAndQuantityDetails(menuAndQuantityDetails);
        return menuAndQuantityDetails;
    }

    public List<String> getGiftAndQuantityDetails() {
        List<String> giftAndQuantityDetails = new ArrayList<>();

        if (giftAndQuantity == null || giftAndQuantity.isEmpty()) {
            giftAndQuantityDetails.add(NONE);
            return giftAndQuantityDetails;
        }
        generateGiftAndQuantityDetails(giftAndQuantityDetails);
        return giftAndQuantityDetails;
    }

    public List<String> getEventAndAmountDetails() {
        List<String> eventAndAmountDetails = new ArrayList<>();

        if (eventAndAmount == null || eventAndAmount.isEmpty()) {
            eventAndAmountDetails.add(NONE);
            return eventAndAmountDetails;
        }
        generateEventAndAmountDetails(eventAndAmountDetails);
        return eventAndAmountDetails;
    }

    private void generateMenuAndQuantityDetails(List<String> menuAndQuantityDetails) {
        for (String menu : menuAndQuantity.keySet()) {
            String detail = menu + WORD_SPACING + menuAndQuantity.get(menu) + COUNT_UNIT;
            menuAndQuantityDetails.add(detail);
        }
    }

    private void generateGiftAndQuantityDetails(List<String> giftAndQuantityDetails) {
        for (String gift : giftAndQuantity.keySet()) {
            String detail = gift + WORD_SPACING + giftAndQuantity.get(gift) + COUNT_UNIT;
            giftAndQuantityDetails.add(detail);
        }
    }

    private void generateEventAndAmountDetails(List<String> eventAndAmountDetails) {
        for (String event : eventAndAmount.keySet()) {
            String detail = event + COLON + String.format(NUMBER_FORMAT, -eventAndAmount.get(event)) + WON;
            eventAndAmountDetails.add(detail);
        }
    }

}