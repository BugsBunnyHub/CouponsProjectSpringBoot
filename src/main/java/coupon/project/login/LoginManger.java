package coupon.project.login;

import coupon.project.facades.AdminFacade;
import coupon.project.facades.ClientFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

public class LoginManger {
    @Component
    public class LoginManager {

        @Autowired
        private ConfigurableApplicationContext ctx;

        public ClientFacade login(String email, String password, ClientType type) throws InvalidClientTypeException, LoginFailedException {
            switch (type) {
                case Admin:
                    AdminFacade adminFacade = ctx.getBean(AdminFacade.class);
                    if (adminFacade.login(email, password))
                        return adminFacade;
                    else
                        throw new LoginFailedException();

                case Company:
                    // TODO

                case Customer:
                    // TODO

                default:
                    throw new InvalidClientTypeException();
            }
        }

    }
}
