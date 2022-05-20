import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "vetabe";
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
    }
}
