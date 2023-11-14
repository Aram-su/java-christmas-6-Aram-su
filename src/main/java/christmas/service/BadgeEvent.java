package christmas.service;

public class BadgeEvent {

    private static final String NONE = "없음";
    private static final String STAR_BADGE = "별";
    private static final String TREE_BADGE = "트리";
    private static final String SANTA_BADGE = "산타";
    private static final int SANTA_THRESHOLD = 20000;
    private static final int TREE_THRESHOLD = 10000;
    private static final int STAR_THRESHOLD = 5000;

    public static String determineBadge(int totalEventAmount) {
        if (totalEventAmount >= SANTA_THRESHOLD) {
            return SANTA_BADGE;
        }
        if (totalEventAmount >= TREE_THRESHOLD) {
            return TREE_BADGE;
        }
        if (totalEventAmount >= STAR_THRESHOLD) {
            return STAR_BADGE;
        }
        return NONE;
    }

}