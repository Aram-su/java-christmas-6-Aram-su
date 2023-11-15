package christmas.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputParserTest {

    @DisplayName("문자로 입력된 날짜는 정수로 변환된다.")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "15,15", "31,31"})
    void parseWhenToVisit(int expectedResult, String whenToVisit) {
        assertEquals(expectedResult, InputParser.parseWhenToVisit(whenToVisit));
    }

}