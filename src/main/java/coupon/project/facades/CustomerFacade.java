package coupon.project.facades;

import coupon.project.beans.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CustomerFacade extends ClientFacade {
    private int customerId;

    @Autowired
    DataSource dataSource;

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

    public void purchaseCoupon(int couponId) throws SQLException {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT amount FROM couponspring.coupons WHERE id=?";
        PreparedStatement prep = conn.prepareStatement(sql);
        prep.setInt(1, couponId);
        ResultSet rs = prep.executeQuery();
        rs.next();
        int amount = rs.getInt("amount");

        if (amount <= 0) return;

        Connection conn1 = dataSource.getConnection();
        String sql1 = "INSERT INTO couponspring.customers_coupons VALUES(?, ?)";
        PreparedStatement prep1 = conn1.prepareStatement(sql1);
        prep1.setInt(1, customerId);
        prep1.setInt(2, couponId);
        boolean result = prep1.execute();

        if (!result) return;

        Connection conn2 = dataSource.getConnection();
        String sql2 = "UPDATE couponspring.coupons SET amount=? WHERE id=?";
        PreparedStatement prep2 = conn2.prepareStatement(sql2);
        prep2.setInt(1, --amount);
        prep2.setInt(2, couponId);
        boolean result2 = prep2.execute(); // boolean for future use
    }



}
