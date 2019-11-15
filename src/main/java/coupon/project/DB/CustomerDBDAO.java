package coupon.project.DB;

import coupon.project.beans.Coupon;
import coupon.project.beans.Customer;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDBDAO {

    @Autowired
    private CustomerRepo customerRepo;

    public void addCustomer(Customer customer) {
        customerRepo.save(customer);

    }

    public void updateCustomer(@NotNull Customer customer) {
        if (customerRepo.existsById(customer.getId()))
            customerRepo.save(customer);
    }

    public void deleteCustomer(int id) {
        customerRepo.deleteById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public void findOneCustomer(int id) {
        customerRepo.findById(id);
    }

    public Customer isCustomerExists(String email, String password) {
        return customerRepo.findCustomerByEmailAndPassword(email, password);
    }

    public Customer isCustomerEmailExists(String email) {
        return customerRepo.findCustomerByEmail(email);
    }

    public List<Customer> findCustomerByCoupons(Coupon coupon) {
        return customerRepo.findCustomerByCoupons(coupon);
    }

}

