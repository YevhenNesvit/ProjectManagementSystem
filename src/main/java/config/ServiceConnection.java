package config;

public class ServiceConnection {
    private final String dbPassword = System.getenv("dbPassword");
    private final String dbUsername = System.getenv("dbUsername");
    private final PropertiesConfig propertiesConfig = new PropertiesConfig();
    private final DatabaseManagerConnector connector = new DatabaseManagerConnector(propertiesConfig.loadProperties("application.properties"),
            dbUsername, dbPassword);

    public DatabaseManagerConnector connect() {

        return connector;
    }
}
