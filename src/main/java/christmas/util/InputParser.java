package christmas.util;

import christmas.model.Constants;
import christmas.model.Menu;
import java.util.HashMap;
import java.util.Map;

public class InputParser {

    public static int parseWhenToVisit(String whenToVisit) {
        return Integer.parseInt(whenToVisit);
    }

    public static Map<String, Integer> parseUndividedOrder(String undividedOrder) {
        HashMap<String, Integer> menuAndQuantity = new HashMap<>();

        String[] dividedOrders = divideOrder(undividedOrder);
        for (String dividedOrder : dividedOrders) {
            saveMenuAndQuantity(menuAndQuantity, dividedOrder);
        }
        return menuAndQuantity;
    }

    private static String[] divideOrder(String undividedOrder) {
        return undividedOrder.split(Constants.ORDER_SEPARATOR);
    }

    private static String[] divideMenuAndQuantity(String dividedOrder) {
        return dividedOrder.split(Constants.MENU_QUANTITY_SEPARATOR);
    }

    private static void saveMenuAndQuantity(HashMap<String, Integer> menuAndQuantity, String dividedOrder) {
        String[] dividedMenuAndQuantity = divideMenuAndQuantity(dividedOrder);

        String menu = dividedMenuAndQuantity[Constants.MENU_INDEX];
        Integer quantity = Integer.parseInt(dividedMenuAndQuantity[Constants.QUANTITY_INDEX]);

        validateMenuAndQuantity(menuAndQuantity, menu, quantity);

        menuAndQuantity.put(menu, quantity);
    }

    private static void validateMenuAndQuantity(HashMap<String, Integer> menuAndQuantity, String menu,
        Integer quantity) {
        validateExistingMenu(menu);
        validateQuantityRange(quantity);
        validateDuplicateMenu(menuAndQuantity, menu);
    }

    private static void validateExistingMenu(String menu) {
        if (!Menu.contains(menu)) {
            throw new IllegalArgumentException(Constants.ORDER_ERROR);
        }
    }

    private static void validateQuantityRange(int quantity) {
        if (quantity < Constants.MIN_QUANTITY || quantity > Constants.MAX_QUANTITY) {
            throw new IllegalArgumentException(Constants.ORDER_ERROR);
        }
    }

    private static void validateDuplicateMenu(HashMap<String, Integer> menuAndQuantity, String menu) {
        if (menuAndQuantity.containsKey(menu)) {
            throw new IllegalArgumentException(Constants.ORDER_ERROR);
        }
    }

}