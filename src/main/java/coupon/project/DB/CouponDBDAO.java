package coupon.project.DB;

import coupon.project.Exceptions.couponNotFoundException;
import coupon.project.beans.Company;
import coupon.project.beans.Coupon;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CouponDBDAO {

    @Autowired
    private CouponRepo couponRepo;

    public Coupon addCoupon(Coupon coupon) {
        couponRepo.save(coupon);
        return coupon;
    }

    public void updateCoupon(@NotNull Coupon coupon) throws couponNotFoundException {
        if (couponRepo.existsById(coupon.getId()))
            couponRepo.save(coupon);
        else {
            throw new couponNotFoundException();
        }
    }

    public void deleteCoupon(int id) {
        couponRepo.deleteById(id);

    }

    public List<Coupon> getAllCoupons() {
        return couponRepo.findAll();
    }

    public Coupon getOneCoupon(int id) {
        Optional<Coupon> couponOptional = couponRepo.findById(id);
        return couponOptional.orElse(null);
    }

    public Coupon getCouponByName(String title) {
        return couponRepo.findCouponByTitle(title);
    }

    public List<Coupon> getCouponByCompany(Company company) {
        return couponRepo.findCouponByCompanyID(company);
    }


}
