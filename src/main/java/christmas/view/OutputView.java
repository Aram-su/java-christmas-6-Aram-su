package christmas.view;

import java.util.List;

public class OutputView {

    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String MENU_MESSAGE = "\n<주문 메뉴>";
    private static final String BEFORE_DISCOUNT_MESSAGE = "\n<할인 전 총주문 금액>";
    private static final String GIFT_MESSAGE = "\n<증정 메뉴>";
    private static final String NUMBER_FORMAT = "%,d";
    private static final String WON = "원";
    private static final String EVENT_MESSAGE = "\n<혜택 내역>";
    private static final String TOTAL_EVENT_AMOUNT_MESSAGE = "\n<총혜택 금액>";
    private static final String AFTER_DISCOUNT_MESSAGE = "\n<할인 후 예상 결제 금액>";
    private static final String BADGE_MESSAGE = "\n<12월 이벤트 배지>";

    public static void printPlannerStart() {
        System.out.println(START_MESSAGE);
    }

    public static void printEventDate(String eventPreview) {
        System.out.println(eventPreview);
    }

    public static void printMenuAndQuantityDetails(List<String> menusAndQuantity) {
        System.out.println(MENU_MESSAGE);
        for (String menuAndQuantity : menusAndQuantity) {
            System.out.println(menuAndQuantity);
        }
    }

    public static void printTotalOrderAmount(int totalOrderAmount) {
        System.out.println(BEFORE_DISCOUNT_MESSAGE);
        System.out.println(String.format(NUMBER_FORMAT, totalOrderAmount) + WON);
    }

    public static void printGiftAndQuantityDetails(List<String> giftAndQuantityDetails) {
        System.out.println(GIFT_MESSAGE);
        for (String giftAndQuantityDetail : giftAndQuantityDetails) {
            System.out.println(giftAndQuantityDetail);
        }
    }

    public static void printEventAndAmountDetails(List<String> eventAndAmountDetails) {
        System.out.println(EVENT_MESSAGE);
        for (String eventAndAmountDetail : eventAndAmountDetails) {
            System.out.println(eventAndAmountDetail);
        }
    }

    public static void printTotalEventAmount(int totalEventAmount) {
        System.out.println(TOTAL_EVENT_AMOUNT_MESSAGE);
        System.out.println(String.format(NUMBER_FORMAT, -totalEventAmount) + WON);
    }

    public static void printDiscountedAmount(int discountedAmount) {
        System.out.println(AFTER_DISCOUNT_MESSAGE);
        System.out.println(String.format(NUMBER_FORMAT, discountedAmount) + WON);
    }

    public static void printBadge(String badge) {
        System.out.println(BADGE_MESSAGE);
        System.out.println(badge);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

}
