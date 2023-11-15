package christmas.util;

import christmas.model.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
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

}