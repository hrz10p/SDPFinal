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
            try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, name);
                statement.setString(2, password);
                statement.executeUpdate();

                // Retrieve the generated user ID
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);
                        System.out.println("User added with ID: " + userId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addBooking( double price, int user_id ,String description) {
        try {
            String query = "INSERT INTO booking (price,user_id, description) VALUES (?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDouble(2, price);
                statement.setInt(3 , user_id);
                statement.setString(4, description);
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
   public void showAllBookings() {
       try {
           String query = "SELECT * FROM booking";
           try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
               while (resultSet.next()) {
                   int bookingId = resultSet.getInt("id");
                   int userId = resultSet.getInt("user_id");
                   double price = resultSet.getDouble("price");
                   String description = resultSet.getString("description");

                   // Assuming you have a toString method in your Booking class
                   System.out.println("Booking ID: " + bookingId);
                   System.out.println("User ID: " + userId);
                   System.out.println("Price: " + price);
                   System.out.println("Description: " + description);
                   System.out.println();
               }
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
    public void showBookingsForUserName(String userName) {
        try {
            String query = "SELECT b.id AS booking_id, b.price, b.description, u.id AS user_id FROM booking b " +
                    "JOIN users u ON b.user_id = u.id WHERE u.name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, userName);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int bookingId = resultSet.getInt("booking_id");
                        int userId = resultSet.getInt("user_id");
                        double price = resultSet.getDouble("price");
                        String description = resultSet.getString("description");

                        // Assuming you have a toString method in your Booking class
                        System.out.println("Booking ID: " + bookingId);
                        System.out.println("User ID: " + userId);
                        System.out.println("Price: " + price);
                        System.out.println("Description: " + description);
                        System.out.println();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User getUserByName(String userName) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, userName);
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

}
