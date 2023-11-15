package christmas.controller;

import christmas.util.InputParser;
import christmas.util.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class InputController {

    public static int getReservationDate() {
        Integer reservationDate = getReservationDateAttempt();

        while (reservationDate == null) {
            reservationDate = getReservationDateAttempt();
        }
        return reservationDate;
    }

    public static Map<String, Integer> getMenuAndQuantity() {
        Map<String, Integer> menuAndQuantity = getMenuAndQuantityAttempt();

        while (menuAndQuantity == null) {
            menuAndQuantity = getMenuAndQuantityAttempt();
        }
        return menuAndQuantity;
    }

    private static Integer getReservationDateAttempt() {
        try {
            String whenToVisit = InputView.inputWhenToVisit();
            InputValidator.validateWhenToVisit(whenToVisit);
            return InputParser.parseWhenToVisit(whenToVisit);
        } catch (IllegalArgumentException illegalArgumentException) {
            OutputView.printErrorMessage(illegalArgumentException.getMessage());
        }
        return null;
    }

    private static Map<String, Integer> getMenuAndQuantityAttempt() {
        try {
            String undividedOrder = InputView.inputUndividedOrder();
            InputValidator.validateUndividedOrder(undividedOrder);

            Map<String, Integer> menuAndQuantity = InputParser.parseUndividedOrder(undividedOrder);
            InputValidator.validateMenuAndQuantity(menuAndQuantity);

            return menuAndQuantity;
        } catch (IllegalArgumentException illegalArgumentException) {
            OutputView.printErrorMessage(illegalArgumentException.getMessage());
        }
        return null;
    }

}