package coupon.project.Exceptions;

public class couponAlreadyPurchasedException extends Exception {
    public couponAlreadyPurchasedException() {
        super("Coupon already purchased by the user");
    }
}
