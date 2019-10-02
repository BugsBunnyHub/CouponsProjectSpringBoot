package coupon.project.facades;

import coupon.project.DB.CompanyDBDAO;
import coupon.project.DB.CouponDBDAO;
import coupon.project.DB.CustomerDBDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientFacade {
    @Autowired
    protected CompanyDBDAO companyDB;
    @Autowired
    protected CouponDBDAO couponDB;
    @Autowired
    protected CustomerDBDAO customerDB;

    public abstract boolean login(String email, String password);
}
