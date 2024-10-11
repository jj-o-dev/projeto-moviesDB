import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MyConnection myConnection = new MyConnection();
        Connection connection = myConnection.getConnection();
        Scanner scanner = new Scanner(System.in);

        String sqlSelect = "SELECT * FROM movies"; // pronto
        String sqlInsert = "INSERT INTO movies (title, release_year, director, genre) VALUES (?, ?, ?, ?)";// pronto
        String sqlUpdate = "UPDATE movies SET title = 'Godzilla -1' WHERE title = 'Godzilla Minus One'";
        String sqlDelete = "DELETE FROM movies WHERE id = '4'";

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        PreparedStatement insertState = connection.prepareStatement(sqlInsert);

        ResultSet result = statement.executeQuery(sqlSelect);

        while (true) {
            printMenu();
            System.out.print("Digite a operação desejada: ");
            int operation = scanner.nextInt();

            switch (operation) {
                case 1:
                    clearTerminal();
                    printResultSet(result);
                    int r;
                    do {
                        System.out.print("digite 1 para voltar ao menu: ");
                        r = scanner.nextInt();
                    } while (r != 1);
                    clearTerminal();
                    continue;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Encerrando...");
                    statement.close();result.close();connection.close();scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Número inválido");
                    continue;
            }
        }
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

    public static void printMenu() {
        System.out.println("--------------- FAV MOVIES DATABASE ---------------");
        System.out.println();
        System.out.println("1 - Mostrar filmes salvos");
        System.out.println("2 - Inserir novo filme");
        System.out.println("3 - Modificar informação");
        System.out.println("4 - Remover um filme");
        System.out.println("5 - Sair");
        System.out.println("---------------------------------------------------");
    }

    public static void clearTerminal() {
        try {
            // Identifica o sistema operacional
            String sistemaOperacional = System.getProperty("os.name");

            if (sistemaOperacional.contains("Windows")) {
                // Executa o comando 'cls' no CMD para limpar a tela
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Executa o comando 'clear' em sistemas UNIX/Linux/Mac
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
