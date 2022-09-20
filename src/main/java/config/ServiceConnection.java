package config;

public class ServiceConnection {
    private final String dbPassword = System.getenv("dbPassword");
    private final String dbUsername = System.getenv("dbUsername");
    DatabaseManagerConnector connector = new DatabaseManagerConnector((new PropertiesConfig().loadProperties("application.properties")),
            dbUsername, dbPassword);

    public DatabaseManagerConnector connect() {

        return connector;
    }
}
