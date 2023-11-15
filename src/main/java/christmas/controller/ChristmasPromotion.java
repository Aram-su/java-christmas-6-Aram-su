package christmas.controller;

import christmas.model.Reservation;
import christmas.service.Event;
import christmas.view.OutputView;
import java.util.Map;

public class ChristmasPromotion {

    private Reservation reservation;

    public void startPlanner() {
        OutputView.printPlannerStart();
        makeReservation();
        beforeApplyEvent();
        applyEvent();
        afterApplyEvent();
    }

    private void makeReservation() {
        int reservationDate = InputController.getReservationDate();
        Map<String, Integer> menuAndQuantity = InputController.getMenuAndQuantity();
        reservation = new Reservation(reservationDate, menuAndQuantity);
    }

    private void beforeApplyEvent() {
        OutputView.printEventDate(reservation.getReservationDateEventPreview());
        OutputView.printMenuAndQuantityDetails(reservation.getMenuAndQuantityDetails());
        OutputView.printTotalOrderAmount(reservation.getTotalOrderAmount());
    }

    private void applyEvent() {
        Event.applyEvent(reservation);
    }

    private void afterApplyEvent() {
        OutputView.printGiftAndQuantityDetails(reservation.getGiftAndQuantityDetails());
        OutputView.printEventAndAmountDetails(reservation.getEventAndAmountDetails());
        OutputView.printTotalEventAmount(reservation.getTotalEventAmount());
        OutputView.printDiscountedAmount(reservation.getDiscountedAmount());
        OutputView.printBadge(reservation.getBadge());
    }

}