package christmas.util;

import christmas.model.Menu;
import java.util.HashMap;
import java.util.Map;

public class InputParser {

    private static final String ORDER_SEPARATOR = ",";
    private static final String MENU_QUANTITY_SEPARATOR = "-";
    private static final int MENU_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 20;
    private static final String ORDER_ERROR = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

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
        return undividedOrder.split(ORDER_SEPARATOR);
    }

    private static String[] divideMenuAndQuantity(String dividedOrder) {
        return dividedOrder.split(MENU_QUANTITY_SEPARATOR);
    }

    private static void saveMenuAndQuantity(HashMap<String, Integer> menuAndQuantity, String dividedOrder) {
        String[] dividedMenuAndQuantity = divideMenuAndQuantity(dividedOrder);

        String menu = dividedMenuAndQuantity[MENU_INDEX];
        Integer quantity = Integer.parseInt(dividedMenuAndQuantity[QUANTITY_INDEX]);

        validateMenuAndQuantity(menuAndQuantity, menu, quantity);

        menuAndQuantity.put(menu, quantity);
    }

    private static void validateMenuAndQuantity(HashMap<String, Integer> menuAndQuantity, String menu, Integer quantity) {
        validateExistingMenu(menu);
        validateQuantityRange(quantity);
        validateDuplicateMenu(menuAndQuantity, menu);
    }

    private static void validateExistingMenu(String menu) {
        if (!Menu.contains(menu)) {
            throw new IllegalArgumentException(ORDER_ERROR);
        }
    }

    private static void validateQuantityRange(int quantity) {
        if (quantity < MIN_QUANTITY || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(ORDER_ERROR);
        }
    }

    private static void validateDuplicateMenu(HashMap<String, Integer> menuAndQuantity, String menu) {
        if (menuAndQuantity.containsKey(menu)) {
            throw new IllegalArgumentException(ORDER_ERROR);
        }
    }

}