package services;

import config.ServiceConnection;
import converter.SkillConverter;
import model.dao.SkillDao;
import model.dto.SkillDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillService {
    ServiceConnection serviceConnection = new ServiceConnection();
    private static final String DELETE_SKILL = "DELETE FROM skills where skill_id = ?";
    private static final String SELECT = "SELECT skill_id, name, skill_level FROM skills";
    private static final String SELECT_BY_ID = "SELECT skill_id, name, skill_level FROM skills WHERE skill_id = ?";
    SkillConverter skillConverter = new SkillConverter();

    public List<SkillDto> skillsList() throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = serviceConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT);

            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<SkillDao> list = new ArrayList<>();
        while (resultSet.next()) {
            SkillDao skill = new SkillDao(resultSet.getInt("skill_id"),
                    resultSet.getString("name"), resultSet.getString("skill_level"));

            list.add(skill);
        }

        return skillConverter.fromList(list);
    }

    public SkillDto skillById(Integer id) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = serviceConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SkillDao skill = new SkillDao();
        while (resultSet.next()) {
            skill = new SkillDao(resultSet.getInt("skill_id"),
                    resultSet.getString("name"), resultSet.getString("skill_level"));
        }

        return skillConverter.from(skill);
    }

    public void updateSkill(String columnName, String newValue, Integer id) throws SQLException {
        String updateSkill = String.format("UPDATE skills SET %s = '%s' WHERE skill_id = ?", columnName, newValue);
        try (Connection connection = serviceConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateSkill);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSkill(Integer id) throws SQLException {

        try (Connection connection = serviceConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SKILL);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
