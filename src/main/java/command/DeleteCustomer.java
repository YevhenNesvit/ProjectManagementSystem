package command;

import services.CustomerService;
import view.View;

import java.sql.SQLException;

public class DeleteCustomer implements Command {
    public static final String DELETE_CUSTOMER = "delete customer";
    private final View view;
    CustomerService customerService = new CustomerService();

    public DeleteCustomer(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_CUSTOMER);
    }

    @Override
    public void execute() {
        int id;
        while (true) {
            try {
                view.write("Please, enter customer id to delete: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            customerService.deleteCustomer(id);
            view.write("Customer with id " + id + " successfully deleted");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
