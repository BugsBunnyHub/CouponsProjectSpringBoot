package coupon.project.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "Customers", uniqueConstraints =
//        {@UniqueConstraint(columnNames = "email", name = "uniqueEmailConstraint")})
@Component
@Scope("prototype") //can create many customers
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // for security better to use UUID.randomUUID() to generate random ID key as String not int
    @NotBlank
    @Column(name = "firstName")
    private String firstName;
    @NotBlank
    @Column(name = "lastName")
    private String lastName;
    @NotBlank
    @Column(name = "email", unique = true)
    private String email;
    @NotBlank
    @Column(name = "password")
    @Min(value = 6)
    @Max(value = 16)
    private String password;
    @Column
    @OneToMany(fetch = FetchType.EAGER)
    private List<Coupon> coupons = new ArrayList<>();

    //empty con for hibernate
    public Customer() {
    }

    public Customer(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String email, @NotBlank String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Customer(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String email, @NotBlank String password, List<Coupon> coupons) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

    public int getId() {
        return id;
    }

    //should be never used since it's @Id, how ever DB would ignore it
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", coupons=" + coupons +
                '}';
    }
}
