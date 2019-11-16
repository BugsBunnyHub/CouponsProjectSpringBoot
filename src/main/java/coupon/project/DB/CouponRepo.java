package coupon.project.DB;

import coupon.project.beans.Company;
import coupon.project.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    Coupon findCouponByTitle(String title);

    Coupon findCouponByEndDate(Date date);

    Coupon findCouponByStartDate(Date date);

    List<Coupon> findCouponByCompanyID(Company company);

    List<Coupon> findAllByAmountIsGreaterThanAndEndDateIsAfter(int amount, Date now);

}
