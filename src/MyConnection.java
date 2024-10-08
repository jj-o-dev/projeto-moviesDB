import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/fav_movies";
    private String user = "root";
    private String password = "XvZV8*5$i0bI";

    protected java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        return DriverManager.getConnection(this.url, this.user, this.password);
    }
}
