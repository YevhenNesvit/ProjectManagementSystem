package command;

import services.CustomerService;
import view.View;

import java.sql.SQLException;

public class GetCustomers implements Command {
    public static final String GET_CUSTOMERS = "get customers";
    private final View view;
    CustomerService customerService = new CustomerService();

    public GetCustomers(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_CUSTOMERS);
    }

    @Override
    public void execute() {
        try {
            view.write("List of all customers is:\n" + customerService.customerList());
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
