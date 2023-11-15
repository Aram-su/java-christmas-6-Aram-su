package christmas.service;

import christmas.model.Constants;

public class BadgeEvent {

    public static String determineBadge(int totalEventAmount) {
        if (totalEventAmount >= Constants.SANTA_THRESHOLD) {
            return Constants.SANTA_BADGE;
        }
        if (totalEventAmount >= Constants.TREE_THRESHOLD) {
            return Constants.TREE_BADGE;
        }
        if (totalEventAmount >= Constants.STAR_THRESHOLD) {
            return Constants.STAR_BADGE;
        }
        return Constants.NONE;
    }

}