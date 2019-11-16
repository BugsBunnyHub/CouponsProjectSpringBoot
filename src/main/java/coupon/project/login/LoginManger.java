package coupon.project.login;

import coupon.project.facades.AdminFacade;
import coupon.project.facades.ClientFacade;
import coupon.project.facades.CompanyFacade;
import coupon.project.facades.CustomerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class LoginManger {

    @Autowired
    private ConfigurableApplicationContext ctx;

    public ClientFacade login(String email, String password, ClientType type) throws InvalidClientTypeException, LoginFailedException {
        switch (type) {
            case Admin:
                AdminFacade adminService = ctx.getBean(AdminFacade.class);
                if (adminService.login(email, password)) {
                    System.out.println("Connected to Admin service");
                    return adminService;
                } else
                    throw new LoginFailedException();

            case Company:
                CompanyFacade companyService = ctx.getBean(CompanyFacade.class);
                if (companyService.login(email, password)) {
                    System.out.println("Connected to Company service");
                    return companyService;
                } else
                    throw new LoginFailedException();


            case Customer:
                CustomerFacade customerService = ctx.getBean(CustomerFacade.class);
                if (customerService.login(email, password)) {
                    System.out.println("Connected to Customer service");
                    return customerService;
                } else
                    throw new LoginFailedException();

            default:
                throw new InvalidClientTypeException();
        }
    }


}

