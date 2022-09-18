package command;

import services.CustomerRepository;
import view.View;

import java.sql.SQLException;

public class UpdateCustomer implements Command {
    public static final String UPDATE_CUSTOMER = "update customer";
    private final View view;
    CustomerRepository customerRepository = new CustomerRepository();

    public UpdateCustomer(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_CUSTOMER);
    }

    @Override
    public void execute() {
        String columnName;
        String newValue;
        int id;
        while (true) {
            try {
                view.write("Please, enter columnName to update: name or country ");
                columnName = view.read();
                view.write("Please, enter new value: ");
                newValue = view.read();
                view.write("Please, enter customer_id: ");
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value. Use digits");
            }
        }
        try {
            customerRepository.updateCustomer(columnName, newValue, id);
            view.write("Customer with id " + id + " successfully updated");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
