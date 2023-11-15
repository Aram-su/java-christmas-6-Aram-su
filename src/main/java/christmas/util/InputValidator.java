package christmas.util;

import christmas.model.Constants;
import christmas.model.Menu;
import java.util.Map;
import java.util.Objects;

public class InputValidator {

    public static void validateWhenToVisit(String whenToVisit) {
        validateIsNumber(whenToVisit);
        validateDateRange(whenToVisit);
    }

    public static void validateUndividedOrder(String undividedOrder) {
        validateOrderFormat(undividedOrder);
    }

    public static void validateMenuAndQuantity(Map<String, Integer> menuAndQuantity) {
        validateHasNonDrinkMenu(menuAndQuantity);
        validateTotalQuantity(menuAndQuantity);
    }

    private static void validateIsNumber(String whenToVisit) {
        try {
            Integer.parseInt(whenToVisit);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(Constants.DATE_ERROR);
        }
    }

    private static void validateDateRange(String whenToVisit) {
        int date = Integer.parseInt(whenToVisit);
        if (date < Constants.MIN_DATE || date > Constants.MAX_DATE) {
            throw new IllegalArgumentException(Constants.DATE_ERROR);
        }
    }

    private static void validateOrderFormat(String undividedOrder) {
        if (!undividedOrder.matches(Constants.ORDER_REGEX)) {
            throw new IllegalArgumentException(Constants.ORDER_ERROR);
        }
    }

    private static void validateHasNonDrinkMenu(Map<String, Integer> menuAndQuantity) {
        for (String menu : menuAndQuantity.keySet()) {
            if (isNonDrink(menu)) {
                return;
            }
        }
        throw new IllegalArgumentException(Constants.ORDER_ERROR);
    }

    private static boolean isNonDrink(String menu) {
        return !Objects.equals(Menu.getTypeByName(menu), Constants.TYPE_DRINK);
    }

    private static void validateTotalQuantity(Map<String, Integer> menuAndQuantity) {
        int totalQuantity = calculateTotalQuantity(menuAndQuantity);
        if (totalQuantity > Constants.MAX_TOTAL_QUANTITY) {
            throw new IllegalArgumentException(Constants.ORDER_ERROR);
        }
    }

    private static int calculateTotalQuantity(Map<String, Integer> menuAndQuantity) {
        int totalQuantity = Constants.ZERO;
        for (String menu : menuAndQuantity.keySet()) {
            totalQuantity += menuAndQuantity.get(menu);
        }
        return totalQuantity;
    }

}