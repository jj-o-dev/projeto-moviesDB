import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Printers {

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
            if (rsmd.getColumnName(i).equalsIgnoreCase("id") || rsmd.getColumnName(i).equalsIgnoreCase("ano")) {
                columnWidths[i - 1] = 4;
            } else {
                // Para as outras colunas, calculamos dinamicamente
                columnWidths[i - 1] = Math.max(rsmd.getColumnName(i).length(), 18); // 15 é o tamanho mínimo para outras colunas
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

    public static void printColumns() {
        System.out.println("----------------------");
        System.out.println("1 - Título");
        System.out.println("2 - Ano");
        System.out.println("3 - Nome do diretor(a)");
        System.out.println("4 - gênero");
        System.out.println("----------------------");
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
