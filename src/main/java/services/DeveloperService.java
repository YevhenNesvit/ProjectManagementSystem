package services;

import config.RepositoryConnection;
import converter.DeveloperConverter;
import model.dao.DeveloperDao;
import model.dto.DeveloperDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DeveloperService {
    RepositoryConnection repositoryConnection = new RepositoryConnection();
    private static final String SALARY_BY_PROJECT_ID = "SELECT SUM(d.salary) as salary " +
            "FROM developers d JOIN developers_per_projects dpp ON dpp.developer_id = d.developer_id " +
            "JOIN projects p ON p.project_id = dpp.project_id WHERE p.project_id = ?";
    private static final String DEVELOPERS_BY_PROJECT_ID = "SELECT d.developer_id, d.first_name, d.last_name, d.gender, " +
            "d.age, d.company_id, d.salary " +
            "FROM developers d " +
            "JOIN developers_per_projects dpp ON dpp.developer_id = d.developer_id " +
            "JOIN projects p ON p.project_id = dpp.project_id " +
            "WHERE p.project_id = ?";
    private static final String DEVELOPERS_BY_SKILL_NAME = "SELECT d.developer_id, d.first_name, d.last_name, d.gender, " +
            "d.age, d.company_id, d.salary " +
            "FROM developers d " +
            "JOIN developers_skills ds ON ds.developer_id = d.developer_id " +
            "JOIN skills s ON s.skill_id = ds.skill_id " +
            "WHERE s.name = ?";
    private static final String DEVELOPERS_BY_SKILL_LEVEL = "SELECT d.developer_id, d.first_name, d.last_name, d.gender," +
            "d.age, d.company_id, d.salary " +
            "FROM developers d " +
            "JOIN developers_skills ds ON ds.developer_id = d.developer_id " +
            "JOIN skills s ON s.skill_id = ds.skill_id " +
            "WHERE s.skill_level = ?";
    private static final String DELETE_DEVELOPER = "DELETE FROM developers where developer_id = ?";
    //    private static final String INSERT_DEVELOPER = "INSERT INTO developers (developer_id, first_name, last_name, gender, " +
//            "age, company_id, salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
    DeveloperConverter developerConverter = new DeveloperConverter();

    public Integer salaryByProjectId(Integer id) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = repositoryConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SALARY_BY_PROJECT_ID);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DeveloperDao developer = new DeveloperDao();
        while (resultSet.next()) {
            developer.setSalary(resultSet.getInt("salary"));
        }
        return developerConverter.from(developer).getSalary();
    }

    public List<DeveloperDto> developersByProjectId(Integer id) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = repositoryConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DEVELOPERS_BY_PROJECT_ID);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<DeveloperDao> list = new ArrayList<>();
        while (resultSet.next()) {
            DeveloperDao developer = new DeveloperDao(resultSet.getInt("developer_id"),
                    resultSet.getString("first_name"), resultSet.getString("last_name"),
                    resultSet.getString("gender"), resultSet.getInt("age"),
                    resultSet.getInt("company_id"), resultSet.getInt("salary"));

            list.add(developer);
        }

        return developerConverter.fromList(list);
    }

    public List<DeveloperDto> developersBySkillName(String name) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = repositoryConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DEVELOPERS_BY_SKILL_NAME);
            statement.setString(1, name);

            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<DeveloperDao> list = new ArrayList<>();
        while (resultSet.next()) {
            DeveloperDao developer = new DeveloperDao(resultSet.getInt("developer_id"),
                    resultSet.getString("first_name"), resultSet.getString("last_name"),
                    resultSet.getString("gender"), resultSet.getInt("age"),
                    resultSet.getInt("company_id"), resultSet.getInt("salary"));

            list.add(developer);
        }

        return developerConverter.fromList(list);
    }

    public List<DeveloperDto> developersBySkillLevel(String level) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = repositoryConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DEVELOPERS_BY_SKILL_LEVEL);
            statement.setString(1, level);

            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<DeveloperDao> list = new ArrayList<>();
        while (resultSet.next()) {
            DeveloperDao developer = new DeveloperDao(resultSet.getInt("developer_id"),
                    resultSet.getString("first_name"), resultSet.getString("last_name"),
                    resultSet.getString("gender"), resultSet.getInt("age"),
                    resultSet.getInt("company_id"), resultSet.getInt("salary"));

            list.add(developer);
        }

        return developerConverter.fromList(list);
    }

    public void updateDeveloper(String columnName, String newValue, Integer id) throws SQLException {
        String updateDeveloper = String.format("UPDATE developers SET %s = '%s' WHERE developer_id = ?", columnName, newValue);
        try (Connection connection = repositoryConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateDeveloper);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDeveloper(Integer id) throws SQLException {

        try (Connection connection = repositoryConnection.connect().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_DEVELOPER);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
