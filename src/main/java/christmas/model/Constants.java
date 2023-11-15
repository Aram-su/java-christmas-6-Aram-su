package christmas.model;

import java.util.List;

public class Constants {

    public static final String DATE_ERROR = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String ORDER_ERROR = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String DECEMBER = "12월 ";
    public static final String PREVIEW_BENEFITS = "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    public static final String MENU_MESSAGE = "\n<주문 메뉴>";
    public static final String BEFORE_DISCOUNT_MESSAGE = "\n<할인 전 총주문 금액>";
    public static final String GIFT_MESSAGE = "\n<증정 메뉴>";
    public static final String EVENT_MESSAGE = "\n<혜택 내역>";
    public static final String TOTAL_EVENT_AMOUNT_MESSAGE = "\n<총혜택 금액>";
    public static final String AFTER_DISCOUNT_MESSAGE = "\n<할인 후 예상 결제 금액>";
    public static final String BADGE_MESSAGE = "\n<12월 이벤트 배지>";
    public static final String WHEN_TO_VISIT_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String ORDER_MESSAGE =
        "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public static final String D_DAY_DISCOUNT = "크리스마스 디데이 할인";
    public static final String WEEKDAY_DISCOUNT = "평일 할인";
    public static final String WEEKEND_DISCOUNT = "주말 할인";
    public static final String SPECIAL_DISCOUNT = "특별 할인";
    public static final String GIFT_EVENT = "증정 이벤트";

    public static final String STAR_BADGE = "별";
    public static final String TREE_BADGE = "트리";
    public static final String SANTA_BADGE = "산타";

    public static final String TYPE_MAIN = "main";
    public static final String TYPE_DESSERT = "dessert";
    public static final String TYPE_DRINK = "drink";
    public static final String DEFAULT_TYPE = "none";
    public static final String CHAMPAGNE = "샴페인";

    public static final String ORDER_REGEX = "^[a-zA-Z가-힣]+-\\d+(,[a-zA-Z가-힣]+-\\d+)*$";
    public static final String NUMBER_FORMAT = "%,d";
    public static final String MENU_QUANTITY_SEPARATOR = "-";
    public static final String ORDER_SEPARATOR = ",";
    public static final String WORD_SPACING = " ";
    public static final String COLON = ": ";

    public static final String COUNT_UNIT = "개";
    public static final String WON = "원";
    public static final String NONE = "없음";

    public static final int EVENT_THRESHOLD = 10000;
    public static final int DISCOUNT_THRESHOLD = 0;
    public static final int CHAMPAGNE_THRESHOLD = 120000;
    public static final int SANTA_THRESHOLD = 20000;
    public static final int TREE_THRESHOLD = 10000;
    public static final int STAR_THRESHOLD = 5000;

    public static final int INCREMENT_AMOUNT = 100;
    public static final int DEFAULT_DISCOUNT_AMOUNT = 900;
    public static final int WEEKDAY_DISCOUNT_AMOUNT = 2023;
    public static final int WEEKEND_DISCOUNT_AMOUNT = 2023;
    public static final int SPECIAL_DISCOUNT_AMOUNT = 1000;

    public static final int MIN_DATE = 1;
    public static final int MAX_DATE = 31;
    public static final int MIN_QUANTITY = 1;
    public static final int MAX_QUANTITY = 20;
    public static final int MAX_TOTAL_QUANTITY = 20;

    public static final int MENU_INDEX = 0;
    public static final int QUANTITY_INDEX = 1;

    public static final int DEFAULT_PRICE = -1;
    public static final int ZERO = 0;
    public static final int ONE = 1;

    public static final int CHRISTMAS_D_DAY = 25;
    public static final int DAYS_IN_A_WEEK = 7;
    public static final int SUNDAY = 3;
    public static final int MONDAY = 4;
    public static final int TUESDAY = 5;
    public static final int WEDNESDAY = 6;
    public static final int THURSDAY = 0;
    public static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    public static final List<Integer> WEEK_DAYS = List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY);

}
