package coupon.project.Exceptions;

public class CouponAlreadyPurchasedException extends Exception {
    public CouponAlreadyPurchasedException() {
        super("Coupon already purchased by the user");
    }
}
