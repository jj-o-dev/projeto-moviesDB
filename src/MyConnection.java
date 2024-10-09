import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://127.0.0.1:3306/fav_movies";
    private final String user = "root";
    private final String password = "XvZV8*5$i0bI";

    protected java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        return DriverManager.getConnection(this.url, this.user, this.password);
    }
}
