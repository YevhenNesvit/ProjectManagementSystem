package command;

import services.CustomerService;
import view.View;

import java.sql.SQLException;

public class CreateCustomer implements Command {
    public static final String CREATE_CUSTOMER = "create customer";
    private final View view;
    CustomerService customerService = new CustomerService();

    public CreateCustomer(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(CREATE_CUSTOMER);
    }

    @Override
    public void execute() {
        int id;
        String name;
        String country;
        while (true) {
            try {
                view.write("Please, enter customer_id of new customer: ");
                id = Integer.parseInt(view.read());
                view.write("Please, enter name of new customer: ");
                name = view.read();
                view.write("Please, enter country of new customer: ");
                country = view.read();
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            customerService.createCustomer(id, name, country);
            view.write("Customer with id " + id + " successfully created");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
