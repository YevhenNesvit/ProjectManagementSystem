import config.DatabaseManagerConnector;
import config.PropertiesConfig;
import repository.DeveloperRepository;
import repository.ProjectRepository;

import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");

        DatabaseManagerConnector manager = new DatabaseManagerConnector(properties, "postgres", "1011");
        DeveloperRepository developerRepository = new DeveloperRepository(manager);
        ProjectRepository projectRepository = new ProjectRepository(manager);

        System.out.println(developerRepository.salaryByProjectId(2));

        System.out.println(developerRepository.developersByProjectId(2));

        System.out.println(developerRepository.developersBySkillName("Java"));

        System.out.println(developerRepository.developersBySkillLevel("Middle"));

        System.out.println(projectRepository.projectsList());
    }
}
