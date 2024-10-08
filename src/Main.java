import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MyConnection myConnection = new MyConnection();
        Connection connection = myConnection.getConnection();

        String sqlSelect = "SELECT * FROM movies";
        String sqlInsert = "INSERT INTO movies (title, release_year, director, genre) VALUES (?, ?, ?, ?)";
        String sqlUpdate = "UPDATE movies SET title = 'Godzilla -1' WHERE title = 'Godzilla Minus One'";
        String sqlDelete = "DELETE FROM movies WHERE id = '4'";

        Statement statement = connection.createStatement();
//        PreparedStatement prepStatement = connection.prepareStatement(sqlInsert);
//        prepStatement.setString(1, "Godzilla Minus One");
//        prepStatement.setString(2, "2023");
//        prepStatement.setString(3, "Takashi Yamazaki");
//        prepStatement.setString(4, "sci-fi");

//        prepStatement.execute();
//        prepStatement.close();
//        ResultSet result = statement.executeQuery(sqlSelect);
//
//        while (result.next()) {
//            System.out.print(result.getInt(1) + " - ");
//            System.out.print(result.getString("title") + " ");
//            System.out.print(result.getString("release_year") + " ");
//            System.out.print(result.getString("director") + " ");
//            System.out.print(result.getString("genres") + "\n");
//            System.out.println();
//        }
//
//        statement.close();

        statement.execute(sqlDelete);
        statement.close();
    }
}
