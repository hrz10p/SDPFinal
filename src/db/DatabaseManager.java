package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String URL = "jdbc:h2:./data/sample;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "sa";

    private static DatabaseManager instance;

    private Connection connection;

    private DatabaseManager() {
        initializeDatabase();
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private void initializeDatabase() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTables() {
        try {
            String createUserTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "password VARCHAR(255) NOT NULL)";
            executeUpdate(createUserTableQuery);

            String createBookingTableQuery = "CREATE TABLE IF NOT EXISTS booking (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "user_id INT," +
                    "price DOUBLE NOT NULL," +
                    "description VARCHAR(255)," +
                    "FOREIGN KEY (user_id) REFERENCES users(id))";
            executeUpdate(createBookingTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String name, String password) {
        try {
            String query = "INSERT INTO users (name, password) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, name);
                statement.setString(2, password);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBooking(int userId, double price, String description) {
        try {
            String query = "INSERT INTO booking (user_id, price, description) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                statement.setDouble(2, price);
                statement.setString(3, description);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int userId) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        user = new User(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("password")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

//    public BookingDB getBookingWithUser(int bookingId) {
//        BookingDB booking = null;
//        try {
//            String query = "SELECT * FROM booking b JOIN users u ON b.user_id = u.id WHERE b.id = ?";
//            try (PreparedStatement statement = connection.prepareStatement(query)) {
//                statement.setInt(1, bookingId);
//                try (ResultSet resultSet = statement.executeQuery()) {
//                    if (resultSet.next()) {
//                        User user = new User(
//                                resultSet.getInt("u.id"),
//                                resultSet.getString("u.name"),
//                                resultSet.getString("u.password")
//                        );
//
//                        booking = new BookingDB(
//                                resultSet.getInt("b.id"),
//                                user,
//                                resultSet.getDouble("price"),
//                                resultSet.getString("description")
//                        );
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return booking;
//    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeUpdate(String query) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        }
    }

    public static void main(String[] args) {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // Example usage: Add a user and a booking
        dbManager.addUser("John Doe", "password123");
        dbManager.addBooking(1, 50.0, "Booking for John Doe");

        // You can add more methods for reading data as needed

        System.out.println(
        dbManager.getUserById(1));
        dbManager.closeConnection();
    }
}
