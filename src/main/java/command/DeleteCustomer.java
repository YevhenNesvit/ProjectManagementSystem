package command;

import services.CustomerService;
import utils.CheckCustomers;
import view.View;

import java.sql.SQLException;

public class DeleteCustomer implements Command {
    public static final String DELETE_CUSTOMER = "delete customer";
    private final View view;
    CustomerService customerService = new CustomerService();
    CheckCustomers checkCustomers = new CheckCustomers();

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
        try {
            while (true) {
                try {
                    view.write("Please, enter customer id to delete: ");
                    id = Integer.parseInt(view.read());
                    if (checkCustomers.IsCustomerIdExists(id)) {
                        customerService.deleteCustomer(id);
                        view.write("Customer with id " + id + " successfully deleted");
                        break;
                    } else {
                        System.out.println("Customer id doesn't exists");
                    }
                } catch (NumberFormatException e) {
                    view.write("Invalid value. Use digits");
                }
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
