package coupon.project.DB;

import coupon.project.beans.Coupon;
import coupon.project.beans.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDBDAO {

    @Autowired
    private CustomerRepo customerRepo;

    public void addCustomer(Customer customer) {
        customerRepo.save(customer);

    }

    public void updateCustomer(Customer customer) {
        if (customerRepo.existsById(customer.getId()))
            customerRepo.save(customer);
    }

    public void deleteCustomer(int id) {
        customerRepo.deleteById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer findOneCustomer(int id) {
        Optional<Customer> opt = customerRepo.findById(id);
        return opt.orElse(null);
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

