package coupon.project.Exceptions;

public class invalidAmountException extends Exception {
    public invalidAmountException() {
        super("Coupon amount is too low");
    }
}
