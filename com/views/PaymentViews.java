package com.views;

import com.controller.PaymentControl;
/**
 * The class that provides the API for the presentation layer interacting with the user to get input for Payment operations. 
 * @author DSF1 Group 1
 *
 */
public class PaymentViews implements Views {
    @Override
    public void process() {
        PaymentControl control = new PaymentControl();
        int choice;

        while (true) {

            choice = UserInputViews.getUserChoice(new String[] {
                    "Make payment and check out from room",
                    "See payment slip for reservation",
                    "Return to main menu"
            });

            switch (choice) {
                case 1:
                    control.printBill(true);
                    break;

                case 2:
                    control.printBill(false);
                    break;
                default:
                    return;
            }
        }
    }
}
