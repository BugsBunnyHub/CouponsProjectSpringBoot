package coupon.project;


import coupon.project.DB.CompanyDBDAO;
import coupon.project.DB.CouponDBDAO;
import coupon.project.DB.CustomerDBDAO;
import coupon.project.beans.Category;
import coupon.project.beans.Company;
import coupon.project.beans.Coupon;
import coupon.project.beans.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Date;

@SpringBootApplication
public class CouponcrojectspringApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(CouponcrojectspringApplication.class, args);

        CustomerDBDAO cu1 = ctx.getBean(CustomerDBDAO.class);
        cu1.addCustomer(new Customer("Daniel", "Shatz", "DanielShatz@gmail.com", "123"));

        CompanyDBDAO co1 = ctx.getBean(CompanyDBDAO.class);
        co1.addCompany(new Company("Shatz ltd", "shatz@shatz.com", "123"));

        CouponDBDAO cou1 = ctx.getBean(CouponDBDAO.class);
        cou1.addCoupon(new Coupon(co1.findCompanyByName("Daniel ltd"), 1, "TEST", "TEST", "", Category.Electricity, Date.valueOf("2019-12-31"), Date.valueOf("2019-12-31"), 25, 1));


    }

}
