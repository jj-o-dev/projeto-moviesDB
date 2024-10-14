import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://sql10.freesqldatabase.com:3306/sql10737403";
    private final String user = "sql10737403";
    private final String password = "4GyPIBWvnw";

    protected java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        return DriverManager.getConnection(this.url, this.user, this.password);
    }
}
