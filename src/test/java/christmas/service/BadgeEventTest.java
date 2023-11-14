package christmas.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadgeEventTest {

    private static final String NONE = "없음";
    private static final String STAR_BADGE = "별";
    private static final String TREE_BADGE = "트리";
    private static final String SANTA_BADGE = "산타";

    @DisplayName("총혜택 금액이 5,000원 미만이면 배지는 없음 이다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 3000, 4999})
    void determineBadge_Less_Than_5000(int totalEventAmount) {
        String badge = BadgeEvent.determineBadge(totalEventAmount);

        assertEquals(NONE, badge);
    }

    @DisplayName("총혜택 금액이 5,000원 이상, 10,000원 미만이면 별 배지 이다.")
    @ParameterizedTest
    @ValueSource(ints = {5000, 7000, 9999})
    void determineBadge_More_5000_Less_10000(int totalEventAmount) {
        String badge = BadgeEvent.determineBadge(totalEventAmount);

        assertEquals(STAR_BADGE, badge);
    }

    @DisplayName("총혜택 금액이 10,000원 이상, 20,000원 미만이면 트리 배지 이다.")
    @ParameterizedTest
    @ValueSource(ints = {10000, 15000, 19999})
    void determineBadge_More_10000_Less_20000(int totalEventAmount) {
        String badge = BadgeEvent.determineBadge(totalEventAmount);

        assertEquals(TREE_BADGE, badge);
    }

    @DisplayName("총혜택 금액이 20,000원 이상이면 산타 배지 이다.")
    @ParameterizedTest
    @ValueSource(ints = {20000, 50000, 100000})
    void determineBadge_More_20000(int totalEventAmount) {
        String badge = BadgeEvent.determineBadge(totalEventAmount);

        assertEquals(SANTA_BADGE, badge);
    }

}