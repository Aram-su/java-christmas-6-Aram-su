package christmas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reservation {

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
            return Constants.NONE;
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
        return Constants.DECEMBER + reservationDate + Constants.PREVIEW_BENEFITS;
    }

    public int getTotalOrderAmount() {
        int totalOrderAmount = Constants.ZERO;
        for (String menu : menuAndQuantity.keySet()) {
            totalOrderAmount += menuAndQuantity.get(menu) * Menu.getPriceByName(menu);
        }
        return totalOrderAmount;
    }

    public int getTotalEventAmount() {
        int totalEventAmount = Constants.ZERO;
        for (String event : eventAndAmount.keySet()) {
            totalEventAmount += eventAndAmount.get(event);
        }
        return totalEventAmount;
    }

    public int getDiscountedAmount() {
        int discountAmount = getTotalEventAmount();
        if (eventAndAmount.containsKey(Constants.GIFT_EVENT)) {
            discountAmount -= eventAndAmount.get(Constants.GIFT_EVENT);
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
            giftAndQuantityDetails.add(Constants.NONE);
            return giftAndQuantityDetails;
        }
        generateGiftAndQuantityDetails(giftAndQuantityDetails);
        return giftAndQuantityDetails;
    }

    public List<String> getEventAndAmountDetails() {
        List<String> eventAndAmountDetails = new ArrayList<>();

        if (eventAndAmount == null || eventAndAmount.isEmpty()) {
            eventAndAmountDetails.add(Constants.NONE);
            return eventAndAmountDetails;
        }
        generateEventAndAmountDetails(eventAndAmountDetails);
        return eventAndAmountDetails;
    }

    private void generateMenuAndQuantityDetails(List<String> menuAndQuantityDetails) {
        for (String menu : menuAndQuantity.keySet()) {
            String detail = menu + Constants.WORD_SPACING + menuAndQuantity.get(menu) + Constants.COUNT_UNIT;
            menuAndQuantityDetails.add(detail);
        }
    }

    private void generateGiftAndQuantityDetails(List<String> giftAndQuantityDetails) {
        for (String gift : giftAndQuantity.keySet()) {
            String detail = gift + Constants.WORD_SPACING + giftAndQuantity.get(gift) + Constants.COUNT_UNIT;
            giftAndQuantityDetails.add(detail);
        }
    }

    private void generateEventAndAmountDetails(List<String> eventAndAmountDetails) {
        for (String event : eventAndAmount.keySet()) {
            String detail = event + Constants.COLON + getFormattedPrice(event) + Constants.WON;
            eventAndAmountDetails.add(detail);
        }
    }

    private String getFormattedPrice(String event) {
        return String.format(Constants.NUMBER_FORMAT, -eventAndAmount.get(event));
    }

}