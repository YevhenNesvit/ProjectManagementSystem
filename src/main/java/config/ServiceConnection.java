package config;

public class ServiceConnection {

    public DatabaseManagerConnector connect() {
        String dbPassword = System.getenv("dbPassword");
        String dbUsername = System.getenv("dbUsername");
        return new DatabaseManagerConnector(new PropertiesConfig().loadProperties("application.properties"),
                dbUsername, dbPassword);
    }
}
