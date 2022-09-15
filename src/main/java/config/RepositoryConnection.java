package config;

public class RepositoryConnection {

    public DatabaseManagerConnector connect() {
        return new DatabaseManagerConnector(new PropertiesConfig().loadProperties("application.properties"),
                "postgres", "1011");
    }
}
