package services;

import config.RepositoryConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompanyService {
    RepositoryConnection repositoryConnection = new RepositoryConnection();
    private static final String DELETE_COMPANY = "DELETE FROM companies where company_id = ?";

    public void updateCompany(String columnName, String newValue, Integer id) throws SQLException {
        String updateCompany = String.format("UPDATE companies SET %s = '%s' WHERE company_id = ?", columnName, newValue);
        try (Connection connection = repositoryConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateCompany);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCompany(Integer id) throws SQLException {

        try (Connection connection = repositoryConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_COMPANY);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}