package coupon.project.facades;

import coupon.project.DB.CouponDBDAO;
import coupon.project.beans.Coupon;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

//TODO make it work
@Component
public class ExpiredCouponDelete implements Runnable {

    private CouponDBDAO couponDB = new CouponDBDAO();
    private boolean exit = false;

    public ExpiredCouponDelete() {
        super();
    }

    @Override
    public void run() {
        while (!exit) {
            try {

                Calendar c = Calendar.getInstance();
                List<Coupon> allCoupons = couponDB.getAllCoupons();
                for (Coupon allCoupon : allCoupons)
                    if (allCoupon.getEndDate().before(c.getTime())) {
                        System.out.println(" deleted coupons are: " + allCoupon.getTitle() + "");
                        couponDB.deleteCoupon(allCoupon.getId());
                    }
                System.out.println("Expired Coupon Checker launched successfully");
                TimeUnit.HOURS.sleep(24); //sleep for 24h
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void stop() {
        exit = true;
    }

}
