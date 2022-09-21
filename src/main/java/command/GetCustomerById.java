package command;

import services.CustomerService;
import view.View;

import java.sql.SQLException;

public class GetCustomerById implements Command {
    public static final String GET_CUSTOMER_BY_ID = "customer by id";
    private final View view;
    CustomerService customerService = new CustomerService();

    public GetCustomerById(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_CUSTOMER_BY_ID);
    }

    @Override
    public void execute() {
        int id;
        while (true) {
            try {
                view.write("Please, enter customer id: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            view.write("For id " + id + " customer is:\n" + customerService.customerById(id));
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
