package christmas.view;

import christmas.model.Constants;
import java.util.List;

public class OutputView {

    public static void printPlannerStart() {
        System.out.println(Constants.START_MESSAGE);
    }

    public static void printEventDate(String eventPreview) {
        System.out.println(eventPreview);
    }

    public static void printMenuAndQuantityDetails(List<String> menusAndQuantity) {
        System.out.println(Constants.MENU_MESSAGE);
        for (String menuAndQuantity : menusAndQuantity) {
            System.out.println(menuAndQuantity);
        }
    }

    public static void printTotalOrderAmount(int totalOrderAmount) {
        System.out.println(Constants.BEFORE_DISCOUNT_MESSAGE);
        System.out.println(String.format(Constants.NUMBER_FORMAT, totalOrderAmount) + Constants.WON);
    }

    public static void printGiftAndQuantityDetails(List<String> giftAndQuantityDetails) {
        System.out.println(Constants.GIFT_MESSAGE);
        for (String giftAndQuantityDetail : giftAndQuantityDetails) {
            System.out.println(giftAndQuantityDetail);
        }
    }

    public static void printEventAndAmountDetails(List<String> eventAndAmountDetails) {
        System.out.println(Constants.EVENT_MESSAGE);
        for (String eventAndAmountDetail : eventAndAmountDetails) {
            System.out.println(eventAndAmountDetail);
        }
    }

    public static void printTotalEventAmount(int totalEventAmount) {
        System.out.println(Constants.TOTAL_EVENT_AMOUNT_MESSAGE);
        System.out.println(String.format(Constants.NUMBER_FORMAT, -totalEventAmount) + Constants.WON);
    }

    public static void printDiscountedAmount(int discountedAmount) {
        System.out.println(Constants.AFTER_DISCOUNT_MESSAGE);
        System.out.println(String.format(Constants.NUMBER_FORMAT, discountedAmount) + Constants.WON);
    }

    public static void printBadge(String badge) {
        System.out.println(Constants.BADGE_MESSAGE);
        System.out.println(badge);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

}
