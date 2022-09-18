package repository;

import config.RepositoryConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerRepository {
    RepositoryConnection repositoryConnection = new RepositoryConnection();
//    private static final String INSERT_CUSTOMER = "INSERT INTO customers (customer_id, name, country) VALUES (?, ?, ?)";

    public void updateCustomer(String columnName, String newValue, Integer id) throws SQLException {
        String updateCustomer = String.format("UPDATE customers SET %s = '%s' WHERE customer_id = ?", columnName, newValue);
        try (Connection connection = repositoryConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateCustomer);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
