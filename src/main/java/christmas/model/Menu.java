package christmas.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", "appetizer", 6000),
    TAPAS("타파스", "appetizer", 5500),
    CAESAR_SALAD("시저샐러드", "appetizer", 8000),

    T_BONE_STEAK("티본스테이크", "main", 55000),
    BARBECUE_LIP("바비큐립", "main", 54000),
    SEAFOOD_PASTA("해산물파스타", "main", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", "main", 25000),

    CHOCOLATE_CAKE("초코케이크", "dessert", 15000),
    ICE_CREAM("아이스크림", "dessert", 5000),

    ZERO_COKE("제로콜라", "drink", 3000),
    RED_WINE("레드와인", "drink", 60000),
    CHAMPAGNE("샴페인", "drink", 25000);

    private final String name;
    private final String type;
    private final int price;

    Menu(String name, String type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    private static final Map<String, Integer> nameToPrice = new HashMap<>();
    private static final Map<String, String> nameToType = new HashMap<>();
    private static final List<String> names = new ArrayList<>();

    static {
        for (Menu menu : Menu.values()) {
            nameToPrice.put(menu.getName(), menu.getPrice());
        }
    }

    static {
        for (Menu menu : Menu.values()) {
            nameToType.put(menu.getName(), menu.getType());
        }
    }

    static {
        for (Menu menu : Menu.values()) {
            names.add(menu.getName());
        }
    }

    public static int getPriceByName(String name) {
        return nameToPrice.getOrDefault(name, Constants.DEFAULT_PRICE);
    }

    public static String getTypeByName(String name) {
        return nameToType.getOrDefault(name, Constants.DEFAULT_TYPE);
    }

    public static boolean contains(String name) {
        return names.contains(name);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

}
