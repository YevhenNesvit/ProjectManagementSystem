package repository;

import config.RepositoryConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompanyRepository {
    RepositoryConnection repositoryConnection = new RepositoryConnection();

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
}
