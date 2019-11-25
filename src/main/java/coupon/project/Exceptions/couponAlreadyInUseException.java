package coupon.project.Exceptions;

public class couponAlreadyInUseException extends Exception {
    public couponAlreadyInUseException() {
        super("Coupon is already registered");
    }
}
