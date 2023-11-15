package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Constants;

public class InputView {

    public static String inputWhenToVisit() {
        System.out.println(Constants.WHEN_TO_VISIT_MESSAGE);
        return Console.readLine();
    }

    public static String inputUndividedOrder() {
        System.out.println(Constants.ORDER_MESSAGE);
        return Console.readLine();
    }

}