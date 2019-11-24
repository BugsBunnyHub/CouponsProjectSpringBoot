package coupon.project.Exceptions;

public class couponNotFoundException extends Exception {
    public couponNotFoundException() {
        super("Coupon was not found!");
    }
}
