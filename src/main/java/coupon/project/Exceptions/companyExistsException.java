package coupon.project.Exceptions;

public class companyExistsException extends Exception {
    public companyExistsException() {
        super("Company name already in use");
    }
}
