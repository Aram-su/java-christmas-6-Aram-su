package christmas.util;

import java.util.HashMap;
import java.util.Map;

public class InputParser {

    private static final String ORDER_SEPARATOR = ",";
    private static final String MENU_QUANTITY_SEPARATOR = "-";
    private static final int MENU_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    public static int parseWhenToVisit(String whenToVisit) {
        return Integer.parseInt(whenToVisit);
    }

    public static Map<String, Integer> parseUndividedOrder(String undividedOrder) {
        HashMap<String, Integer> order = new HashMap<>();

        String[] dividedOrders = divideOrder(undividedOrder);
        for (String dividedOrder : dividedOrders) {
            saveOrder(order, dividedOrder);
        }
        return order;
    }

    private static String[] divideOrder(String undividedOrder) {
        return undividedOrder.split(ORDER_SEPARATOR);
    }

    private static String[] divideMenuAndQuantity(String dividedOrder) {
        return dividedOrder.split(MENU_QUANTITY_SEPARATOR);
    }

    private static void saveOrder(HashMap<String, Integer> order, String dividedOrder) {
        String[] menuAndQuantity = divideMenuAndQuantity(dividedOrder);

        String menu = menuAndQuantity[MENU_INDEX];
        Integer quantity = Integer.parseInt(menuAndQuantity[QUANTITY_INDEX]);

        order.put(menu, quantity);
    }

}