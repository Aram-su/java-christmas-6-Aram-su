package christmas.util;

import christmas.model.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {


    @DisplayName("방문할 날짜는 숫자로 입력하지 않으면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"one", "10a", "b20", "2a1"})
    void validateWhenToVisit_Not_Number(String whenToVisit) {
        assertThatThrownBy(() -> InputValidator.validateWhenToVisit(whenToVisit))
            .hasMessageContaining(Constants.DATE_ERROR)
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("방문할 날짜는 1이상 31이하의 숫자가 아니면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"-10", "0", "32", "100"})
    void validateWhenToVisit_Out_Of_Range(String whenToVisit) {
        assertThatThrownBy(() -> InputValidator.validateWhenToVisit(whenToVisit))
            .hasMessageContaining(Constants.DATE_ERROR)
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("방문할 날짜가 숫자이고 1~31의 범위이면 에러가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "20", "31"})
    void validateWhenToVisit_No_Error(String whenToVisit) {
        assertThatCode(() -> InputValidator.validateWhenToVisit(whenToVisit))
            .doesNotThrowAnyException();
    }

    @DisplayName("주문이 형식에 맞지 않을 경우 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스1", "티본스테이크3개", "2-초코케이크", "1개 아이스크림"})
    void validateUndividedOrder_Incorrect_Format(String undividedOrder) {
        assertThatThrownBy(() -> InputValidator.validateUndividedOrder(undividedOrder))
            .hasMessageContaining(Constants.ORDER_ERROR)
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문이 형식에 맞을 경우 에러가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-1", "타파스-1,티본스테이크-2", "초코케이크-1", "초코케이크-1,타파스-2,티본스테이크-3"})
    void validateUndividedOrder_Correct_Format(String undividedOrder) {
        assertThatCode(() -> InputValidator.validateUndividedOrder(undividedOrder))
            .doesNotThrowAnyException();
    }

    @DisplayName("메뉴에 음료만 있을 경우 에러가 발생한다.")
    @ParameterizedTest
    @MethodSource("onlyDrinkMenuAndQuantity")
    void validateMenuAndQuantity_Only_Drink(Map<String, Integer> menuAndQuantity) {
        assertThatThrownBy(() -> InputValidator.validateMenuAndQuantity(menuAndQuantity))
            .hasMessageContaining(Constants.ORDER_ERROR)
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Map<String, Integer>> onlyDrinkMenuAndQuantity() {
        Map<String, Integer> onlyCoke = new HashMap<>();
        onlyCoke.put("제로콜라", 1);

        Map<String, Integer> onlyWine = new HashMap<>();
        onlyWine.put("레드와인", 2);

        Map<String, Integer> cokeAndWine = new HashMap<>();
        cokeAndWine.put("제로콜라", 1);
        cokeAndWine.put("레드와인", 2);

        return Stream.of(onlyCoke, onlyWine, cokeAndWine);
    }

    @DisplayName("주문한 메뉴의 수량이 총합 20개를 초과하면 에러가 발생한다.")
    @ParameterizedTest
    @MethodSource("overMenuTotalQuantityTwenty")
    void validateMenuAndQuantity_Over_20(Map<String, Integer> menuAndQuantity) {
        assertThatThrownBy(() -> InputValidator.validateMenuAndQuantity(menuAndQuantity))
            .hasMessageContaining(Constants.ORDER_ERROR)
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Map<String, Integer>> overMenuTotalQuantityTwenty() {
        Map<String, Integer> tapasCoke = new HashMap<>();
        tapasCoke.put("타파스", 10);
        tapasCoke.put("제로콜라", 11);

        Map<String, Integer> steakWine = new HashMap<>();
        steakWine.put("스테이크", 2);
        steakWine.put("레드와인", 19);

        Map<String, Integer> soupBarbecueIcecream = new HashMap<>();
        soupBarbecueIcecream.put("양송이수프", 6);
        soupBarbecueIcecream.put("바비큐립", 7);
        soupBarbecueIcecream.put("아이스크림", 8);

        return Stream.of(tapasCoke, steakWine, soupBarbecueIcecream);
    }

    @DisplayName("주문한 메뉴에 음료외의 다른 종류가 있고, 합이 20개 이하이면 에러가 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource("correctMenuAndQuantity")
    void validateMenuAndQuantity_Not_Error(Map<String, Integer> menuAndQuantity) {
        assertThatCode(() -> InputValidator.validateMenuAndQuantity(menuAndQuantity))
            .doesNotThrowAnyException();
    }

    private static Stream<Map<String, Integer>> correctMenuAndQuantity() {
        Map<String, Integer> tapasCoke = new HashMap<>();
        tapasCoke.put("타파스", 1);
        tapasCoke.put("제로콜라", 18);

        Map<String, Integer> steakWine = new HashMap<>();
        steakWine.put("스테이크", 2);
        steakWine.put("레드와인", 18);

        Map<String, Integer> soupBarbecueIcecream = new HashMap<>();
        soupBarbecueIcecream.put("양송이수프", 1);
        soupBarbecueIcecream.put("바비큐립", 2);
        soupBarbecueIcecream.put("아이스크림", 3);

        return Stream.of(tapasCoke, steakWine, soupBarbecueIcecream);
    }

}