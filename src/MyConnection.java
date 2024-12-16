import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;

public class MyConnection {

    private final String info1 = "c3FsMTA3NTIzODE=";
    private final String info2 = "WUg1ZElLQnBBcg==";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://sql10.freesqldatabase.com:3306/sql10752381";
    private final String user = new String(Base64.getDecoder().decode(info1));
    private final String password = new String(Base64.getDecoder().decode(info2));

    protected java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        return DriverManager.getConnection(this.url, this.user, this.password);
    }
}
