package services;

import config.RepositoryConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SkillRepository {
    RepositoryConnection repositoryConnection = new RepositoryConnection();

    public void updateSkill(String columnName, String newValue, Integer id) throws SQLException {
        String updateSkill = String.format("UPDATE skills SET %s = '%s' WHERE skill_id = ?", columnName, newValue);
        try (Connection connection = repositoryConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateSkill);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
