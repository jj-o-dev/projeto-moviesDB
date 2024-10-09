import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MyConnection myConnection = new MyConnection();
        Connection connection = myConnection.getConnection();

        String sqlSelect = "SELECT * FROM movies";
        String sqlInsert = "INSERT INTO movies (title, release_year, director, genre) VALUES (?, ?, ?, ?)";
        String sqlUpdate = "UPDATE movies SET title = 'Godzilla -1' WHERE title = 'Godzilla Minus One'";
        String sqlDelete = "DELETE FROM movies WHERE id = '4'";

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//      PreparedStatement prepStatement = connection.prepareStatement(sqlInsert);
        ResultSet result = statement.executeQuery(sqlSelect);
        printResultSet(result);

        statement.close();
        result.close();
        connection.close();
    }

    public static void printLine(int[] columnWidths) {
        for (int width : columnWidths) {
            System.out.print("+");
            System.out.print("-".repeat(width + 2));
        }
        System.out.println("+");
    }

    public static void printResultSet(ResultSet rs) throws SQLException {
        // Obter os metadados do ResultSet
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        // Definir as larguras de cada coluna (pode ser ajustado manualmente)
        int[] columnWidths = new int[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            if (rsmd.getColumnName(i).equalsIgnoreCase("id")) {
                columnWidths[i - 1] = 5;
            } else {
                // Para as outras colunas, calculamos dinamicamente
                columnWidths[i - 1] = Math.max(rsmd.getColumnName(i).length(), 15); // 15 é o tamanho mínimo para outras colunas
            }
        }

        // Ajustar largura das colunas de acordo com os dados
        while (rs.next()) { // Itera normalmente para calcular a largura das colunas
            for (int i = 1; i <= columnCount; i++) {
                String value = rs.getString(i);
                if (value != null) {
                    columnWidths[i - 1] = Math.max(columnWidths[i - 1], value.length());
                }
            }
        }
        rs.beforeFirst(); // Voltar ao início novamente

        // Imprimir a linha de cabeçalho
        printLine(columnWidths);
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("| %-"+ (columnWidths[i - 1] + 1) +"s", rsmd.getColumnName(i));
        }
        System.out.println("|");

        // Imprimir a linha separadora
        printLine(columnWidths);

        // Imprimir os dados do ResultSet
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String value = rs.getString(i);
                if (value == null) {
                    value = ""; // Tratar valores nulos
                }
                System.out.printf("| %-"+ (columnWidths[i - 1] + 1) +"s", value);
            }
            System.out.println("|");
        }

        // Imprimir a última linha de rodapé
        printLine(columnWidths);
    }
}
