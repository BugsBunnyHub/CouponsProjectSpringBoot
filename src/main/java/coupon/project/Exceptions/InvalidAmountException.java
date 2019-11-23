package coupon.project.Exceptions;

public class InvalidAmountException extends Exception {
    public InvalidAmountException() {
        super("Coupon amount is too low");
    }
}
