import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String urlLocal = "jdbc:mysql://127.0.0.1:3306/fav_movies";
    private final String urlRemote = "jdbc:mysql://sql10.freesqldatabase.com:3306/sql10737403";
    private final String userLocal = "root";
    private final String userRemote = "sql10737403";
    private final String passwordLocal = "XvZV8*5$i0bI";
    private final String passwordRemote = "4GyPIBWvnw";

    protected java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        return DriverManager.getConnection(this.urlRemote, this.userRemote, this.passwordRemote);
    }
}
