package coupon.project.Exceptions;

public class CompanyExistsException extends Exception {
    public CompanyExistsException() {
        super("Company name already in use");
    }
}
