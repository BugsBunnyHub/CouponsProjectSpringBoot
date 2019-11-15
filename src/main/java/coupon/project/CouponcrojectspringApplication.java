package coupon.project;

import coupon.project.DB.CompanyDBDAO;
import coupon.project.beans.Company;
import coupon.project.facades.AdminFacade;
import coupon.project.facades.CompanyFacade;
import coupon.project.login.ClientType;
import coupon.project.login.LoginManger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CouponcrojectspringApplication {

    public static void main(String[] args) {


        ConfigurableApplicationContext ctx = SpringApplication.run(CouponcrojectspringApplication.class, args);
        try {

            LoginManger loginManger = ctx.getBean(LoginManger.class);

            //Admin
            AdminFacade admin = (AdminFacade) loginManger.login("admin@admin.com", "admin", ClientType.Admin);
            //Add customer - works
            //Customer c1 = new Customer("Daniel", "Shatz", "DanielShatz@gmail.com", "123");
            //admin.addCustomer(c1);
            //Update customer - TODO doesn't work
            //admin.updateCustomer(c1.setFirstName("DanielUpdated")));
            //Print all customers - works
            //System.out.println(admin.getAllCustomers());
            //Delete customer - works
            //admin.deleteCustomer(c1.getId());
            //Check if customer exists by email and password - works
            //System.out.println(admin.isCustomerExists("DanielShatz@gmail.com", "123"));
            //Add company - works
            Company company1 = new Company("Shatz ltd", "shatz@shatz.com", "123"); // Works
            admin.addCompany(company1);
            //Update company TODO doesn't work
            //Print all companies
            //Delete company

            //Company
            CompanyDBDAO co1 = ctx.getBean(CompanyDBDAO.class);
            CompanyFacade companyFacade = (CompanyFacade) loginManger.login("shatz@shatz.com", "123", ClientType.Company);

            //Add coupon
            //Update coupon
            //Print all coupons for company
            //Delete coupon

            //Customer
            //Purchase coupon







//            CustomerDBDAO cu1 = ctx.getBean(CustomerDBDAO.class);
//            cu1.addCustomer(new Customer("Daniel", "Shatz", "DanielShatz@gmail.com", "123")); // Works
//
//            CompanyDBDAO co1 = ctx.getBean(CompanyDBDAO.class);
//            Company c1 = new Company("Shatz ltd", "shatz@shatz.com", "123"); // Works
//            co1.addCompany(c1);
//
//            CompanyFacade cF = (CompanyFacade) loginManger.login("shatz@shatz.com", "123", ClientType.Company);
//            Coupon co2 = new Coupon(c1, 100, "title", "description", "cat.jpg", Category.Electricity, new Date(2656565656565L), Date.valueOf("2019-01-01"), 5555.5);
//            cF.addCoupon(co2);
//
//            CustomerFacade customerFacade = (CustomerFacade) loginManger.login("DanielShatz@gmail.com", "123", ClientType.Customer);
//            List<Coupon> coupons = repo.findAllByAmountIsGreaterThanAndEndDateIsAfter(0, new Date(Instant.now().getEpochSecond() * 1000)); // getEpochSecond get's time  in seconds, *1000 to get it in miliseconds
//            Coupon coupon = coupons.get(0);
//            customerFacade.purchaseCoupon(coupon.getId());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}
