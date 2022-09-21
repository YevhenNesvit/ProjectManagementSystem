package command;

import services.CustomerService;
import utils.CheckCustomers;
import view.View;

import java.sql.SQLException;

public class UpdateCustomer implements Command {
    public static final String UPDATE_CUSTOMER = "update customer";
    private final View view;
    CustomerService customerService = new CustomerService();
    CheckCustomers checkCustomers = new CheckCustomers();

    public UpdateCustomer(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_CUSTOMER);
    }

    @Override
    public void execute() {
        String name;
        String country;
        int id;
        try {
            while (true) {
                try {
                    view.write("Please, enter name to update: ");
                    name = view.read();
                    view.write("Please, enter country to update: ");
                    country = view.read();
                    view.write("Please, enter customer_id: ");
                    id = Integer.parseInt(view.read());
                    if (checkCustomers.IsCustomerIdExists(id)) {
                        customerService.updateCustomer(name, country, id);
                        view.write("Customer with id " + id + " successfully updated");
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
