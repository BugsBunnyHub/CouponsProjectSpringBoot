package coupon.project.facades;

import coupon.project.beans.Customer;

public class CustomerFacade extends ClientFacade {
    private int customerId;

    @Override
    //TODO check this method
    //customer login with email and password
    public boolean login(String email, String password) {
        Customer customer = customerDB.isCustomerExists(email, password);
        if (customer != null) {
            this.customerId = customer.getId();
            return true;
        } else
            return false;
    }
}
