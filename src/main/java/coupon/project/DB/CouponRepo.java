package coupon.project.DB;

import coupon.project.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    Coupon findCouponByTitle(String title);

    Coupon findCouponByEndDate(Date date);

    Coupon findCouponByStartDate(Date date);

}
