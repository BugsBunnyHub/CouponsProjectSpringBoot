package coupon.project.DB;

import coupon.project.beans.Company;
import coupon.project.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    Coupon findCouponByTitle(String title);

    Coupon findCouponByEndDate(Date date);

    Coupon findCouponByStartDate(Date date);

    List<Coupon> findCouponByCompanyID(Company company);

    List<Coupon> findAllByAmountIsGreaterThanAndEndDateIsAfter(int amount, Date now);

}
