package christmas.util;

import christmas.model.Constants;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InputParserTest {

    @DisplayName("문자로 입력된 날짜는 정수로 변환된다.")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "15,15", "31,31"})
    void parseWhenToVisit(int expectedResult, String whenToVisit) {
        assertEquals(expectedResult, InputParser.parseWhenToVisit(whenToVisit));
    }

    @DisplayName("고객 주문에 메뉴판에 존재하지 않는 메뉴가 있으면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"비빔밥-1", "티본스테이크-1,사이다-2", "티본스테이크-1,아이스크림-2,샐러드-3"})
    void parseUndividedOrder_No_Existing_Menu(String undividedOrder) {
        assertThatThrownBy(() -> InputParser.parseUndividedOrder(undividedOrder))
            .hasMessageContaining(Constants.ORDER_ERROR)
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("고객 주문에 메뉴 수량이 1 미만, 혹은 20 초과이면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-0", "타파스-1,제로콜라-22", "타파스-1,제로콜라-2,아이스크림-30"})
    void parseUndividedOrder_Menu_Quantity(String undividedOrder) {
        assertThatThrownBy(() -> InputParser.parseUndividedOrder(undividedOrder))
            .hasMessageContaining(Constants.ORDER_ERROR)
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("고객 주문에 중복되는 메뉴가 있으면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,티본스테이크-1", "아이스크림-1,아이스크림-2", "타파스-1,제로콜라-1,타파스-1"})
    void parseUndividedOrder_Duplicate_Menu(String undividedOrder) {
        assertThatThrownBy(() -> InputParser.parseUndividedOrder(undividedOrder))
            .hasMessageContaining(Constants.ORDER_ERROR)
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("적절한 고객 주문은 Map<String, Integer> 형태로 반환된다.")
    @Test
    void parseUndividedOrder_No_Error() {
        String undividedOrder = "타파스-1,티본스테이크-1,제로콜라-2";
        Map<String, Integer> menuAndQuantity = new HashMap<>();
        menuAndQuantity.put("타파스", 1);
        menuAndQuantity.put("티본스테이크", 1);
        menuAndQuantity.put("제로콜라", 2);

        assertEquals(menuAndQuantity, InputParser.parseUndividedOrder(undividedOrder));
    }


}