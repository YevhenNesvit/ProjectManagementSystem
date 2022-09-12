package repository;

import config.DatabaseManagerConnector;

public class CustomerRepository {
    private final DatabaseManagerConnector connector;
//    private static final String INSERT_CUSTOMER = "INSERT INTO customers (customer_id, name, country) VALUES (?, ?, ?)";

    public CustomerRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }
}
