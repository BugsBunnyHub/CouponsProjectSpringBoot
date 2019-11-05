package coupon.project.DB;

import coupon.project.beans.Company;
import coupon.project.beans.Coupon;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CouponDBDAO {

    @Autowired
    private CouponRepo couponRepo;

    public void addCoupon(Coupon coupon) {
        couponRepo.save(coupon);
    }

    public void updateCoupon(@NotNull Coupon coupon) {
        if (couponRepo.existsById(coupon.getId()))
            couponRepo.save(coupon);
    }

    public void deleteCoupon(int id) {
        couponRepo.deleteById(id);

    }

    public void getOneCoupon(int id) {
        couponRepo.findById(id);
    }

    public Coupon getCouponByName(String title) {
        return couponRepo.findCouponByTitle(title);
    }

    public List<Coupon> getCouponByCompany(Company company) {
        return couponRepo.findCouponByCompanyID(company);
    }


}
