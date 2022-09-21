package services;

import config.ServiceConnection;
import converter.CustomerConverter;
import model.dao.CustomerDao;
import model.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    ServiceConnection serviceConnection = new ServiceConnection();
    private static final String DELETE_CUSTOMER = "DELETE FROM customers where customer_id = ?";
    private static final String SELECT = "SELECT customer_id, name, country FROM customers";
    private static final String SELECT_BY_ID = "SELECT customer_id, name, country FROM customers WHERE customer_id = ?";
    //    private static final String INSERT_CUSTOMER = "INSERT INTO customers (customer_id, name, country) VALUES (?, ?, ?)";
    CustomerConverter customerConverter = new CustomerConverter();

    public List<CustomerDto> customerList() throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = serviceConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT);

            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<CustomerDao> list = new ArrayList<>();
        while (resultSet.next()) {
            CustomerDao customer = new CustomerDao(resultSet.getInt("customer_id"),
                    resultSet.getString("name"), resultSet.getString("country"));

            list.add(customer);
        }

        return customerConverter.fromList(list);
    }

    public CustomerDto customerById(Integer id) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = serviceConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CustomerDao customer = new CustomerDao();
        while (resultSet.next()) {
            customer = new CustomerDao(resultSet.getInt("customer_id"),
                    resultSet.getString("name"), resultSet.getString("country"));
        }

        return customerConverter.from(customer);
    }

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
