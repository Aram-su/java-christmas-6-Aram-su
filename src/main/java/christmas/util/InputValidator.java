package christmas.util;

import christmas.model.Menu;
import java.util.Map;
import java.util.Objects;

public class InputValidator {

    private static final String DATE_ERROR = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String ORDER_ERROR = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String ORDER_REGEX = "^[a-zA-Z가-힣]+-\\d+(,[a-zA-Z가-힣]+-\\d+)*$";
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final int MAX_TOTAL_QUANTITY = 20;
    private static final int ZERO = 0;
    private static final String TYPE_DRINK = "drink";

    public static void validateWhenToVisit(String whenToVisit) {
        validateIsNumber(whenToVisit);
        validateDateRange(whenToVisit);
    }

    public static void validateUndividedOrder(String undividedOrder) {
        validateOrderFormat(undividedOrder);
    }

    public static void validateOrder(Map<String, Integer> order) {
        validateHasNonDrinkMenu(order);
        validateTotalQuantity(order);
    }

    private static void validateIsNumber(String whenToVisit) {
        try {
            Integer.parseInt(whenToVisit);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(DATE_ERROR);
        }
    }

    private static void validateDateRange(String whenToVisit) {
        int date = Integer.parseInt(whenToVisit);
        if (date < MIN_DATE || date > MAX_DATE) {
            throw new IllegalArgumentException(DATE_ERROR);
        }
    }

    private static void validateOrderFormat(String undividedOrder) {
        if (!undividedOrder.matches(ORDER_REGEX)) {
            throw new IllegalArgumentException(ORDER_ERROR);
        }
    }

    private static void validateHasNonDrinkMenu(Map<String, Integer> order) {
        for (String menu : order.keySet()) {
            if (isNonDrink(menu)) {
                return;
            }
        }
        throw new IllegalArgumentException(ORDER_ERROR);
    }

    private static boolean isNonDrink(String menu) {
        return !Objects.equals(Menu.getTypeByName(menu), TYPE_DRINK);
    }

    private static void validateTotalQuantity(Map<String, Integer> order) {
        int totalQuantity = calculateTotalQuantity(order);
        if (totalQuantity > MAX_TOTAL_QUANTITY) {
            throw new IllegalArgumentException(ORDER_ERROR);
        }
    }

    private static int calculateTotalQuantity(Map<String, Integer> order) {
        int totalQuantity = ZERO;
        for (String menu : order.keySet()) {
            totalQuantity += order.get(menu);
        }
        return totalQuantity;
    }

}