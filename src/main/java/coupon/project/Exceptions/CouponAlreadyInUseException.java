package coupon.project.Exceptions;

public class CouponAlreadyInUseException extends Exception {
    public CouponAlreadyInUseException() {
        super("Coupon is already registered");
    }
}
