package repository;

import config.DatabaseManagerConnector;
import converter.ProjectConverter;
import dao.ProjectDao;
import dto.ProjectDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    private final DatabaseManagerConnector connector;
    private static final String PROJECTS_LIST = "SELECT p.creation_date, p.name, count(d.developer_id) as count, p.project_id," +
            "p.company_id, p.customer_id, p.cost " +
            "FROM projects p " +
            "JOIN developers_per_projects dpp ON dpp.project_id = p.project_id " +
            "JOIN developers d ON d.developer_id = dpp.developer_id " +
            "GROUP BY p.project_id, p.creation_date, p.name";
//    private static final String INSERT_PROJECT = "INSERT INTO projects (project_id, name, customer_id, company_id, cost, " +
//            "creation_date) VALUES (?, ?, ?, ?, ?, ?)";
    ProjectConverter projectConverter = new ProjectConverter();

    public ProjectRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }

    public List<ProjectDto> projectsList() throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PROJECTS_LIST);

            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<ProjectDao> list = new ArrayList<>();
        while (resultSet.next()) {
            ProjectDao project = new ProjectDao(resultSet.getInt("project_id"), resultSet.getString("name"),
                    resultSet.getInt("customer_id"), resultSet.getInt("company_id"),
                    resultSet.getInt("cost"), resultSet.getDate("creation_date"),
                    resultSet.getInt("count"));

            list.add(project);
        }

        return projectConverter.fromList(list);
    }
}
