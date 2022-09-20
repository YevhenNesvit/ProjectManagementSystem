package services;

import config.ServiceConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerService {
    ServiceConnection serviceConnection = new ServiceConnection();
    private static final String DELETE_CUSTOMER = "DELETE FROM customers where customer_id = ?";
//    private static final String INSERT_CUSTOMER = "INSERT INTO customers (customer_id, name, country) VALUES (?, ?, ?)";

    public void updateCustomer(String columnName, String newValue, Integer id) throws SQLException {
        String updateCustomer = String.format("UPDATE customers SET %s = '%s' WHERE customer_id = ?", columnName, newValue);
        try (Connection connection = serviceConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateCustomer);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(Integer id) throws SQLException {

        try (Connection connection = serviceConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
