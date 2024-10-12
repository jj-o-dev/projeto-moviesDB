import java.sql.*;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        MyConnection myConnection = new MyConnection();
        Connection connection = myConnection.getConnection();

        if (connection.isValid(10)){
            System.out.println(ANSI_GREEN + "Conexão estabelecida" + ANSI_RESET);
            Thread.sleep(600);
        }

        String sqlSelect = "SELECT * FROM filmes"; // pronto
        String selectId = "SELECT 1 FROM filmes WHERE id = ?";
        String sqlInsert = "INSERT INTO filmes (titulo, ano, diretor, genero) VALUES (?, ?, ?, ?)";// pronto
        String sqlDelete = "DELETE FROM filmes WHERE id = ?";// pronto

        Statement selectState = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        PreparedStatement insertState = connection.prepareStatement(sqlInsert);
        PreparedStatement deleteState = connection.prepareStatement(sqlDelete);
        PreparedStatement idState = connection.prepareStatement(selectId);
        ResultSet haveID = null;
        int status, id;

        while (true) {
            Printers.clearTerminal();
            ResultSet result = selectState.executeQuery(sqlSelect);
            Printers.printMenu();
            System.out.print("Digite a operação desejada: ");
            int operation = scanner.nextInt();

            switch (operation) {
                case 1:
                    Printers.clearTerminal();
                    Printers.printResultSet(result);
                    result.close();
                    int r;
                    do {
                        System.out.print(ANSI_YELLOW + "digite 0 para voltar ao menu: " + ANSI_RESET);
                        r = scanner.nextInt();
                    } while (r != 0);
                    continue;
                case 2:
                    Printers.clearTerminal();
                    scanner.nextLine();
                    System.out.println("Insira as informações do filme");
                    System.out.print("Título: ");
                    String title = scanner.nextLine();
                    insertState.setString(1, title);

                    System.out.print("Ano de lançamento: ");
                    String year = scanner.nextLine();
                    insertState.setString(2, year);

                    System.out.print("Nome do diretor(a): ");
                    String dirName = scanner.nextLine();
                    insertState.setString(3, dirName);

                    System.out.print("gênero: ");
                    String genre = scanner.nextLine();
                    insertState.setString(4, genre);
                    status = insertState.executeUpdate();

                    if (status > 0) {
                        System.out.println("\n" + ANSI_GREEN + "Filme salvo!" + ANSI_RESET);
                    }
                    Thread.sleep(700);
                    continue;
                case 3:
                    Printers.clearTerminal();
                    Printers.printResultSet(result);
                    System.out.print(ANSI_YELLOW + "Selecione o id do filme que deseja modificar: " + ANSI_RESET);
                    id = scanner.nextInt();
                    scanner.nextLine();

                    idState.setInt(1, id);
                    haveID = idState.executeQuery();
                    if (!(haveID.next())) {
                        System.out.println(ANSI_RED + "\nid não encontrado" + ANSI_RESET);
                        Thread.sleep(500);
                        continue;
                    }

                    boolean x;
                    String column = null;
                    String newValue = null;

                    do {
                        Printers.clearTerminal();
                        System.out.println("Agora selecione qual dos campos quer editar:");
                        Printers.printColumns();
                        int option = scanner.nextInt();
                        scanner.nextLine();
                        switch (option) {
                            case 1:
                                column = "titulo";
                                System.out.print("Digite o novo título: ");
                                newValue = scanner.nextLine();
                                x = true;
                                break;
                            case 2:
                                column = "ano";
                                System.out.print("Digite o novo ano: ");
                                newValue = scanner.nextLine();
                                x = true;
                                break;
                            case 3:
                                column = "diretor";
                                System.out.print("Digite o novo nome: ");
                                newValue = scanner.nextLine();
                                x = true;
                                break;
                            case 4:
                                column = "genero";
                                System.out.print("Digite o novo gênero: ");
                                newValue = scanner.nextLine();
                                x = true;
                                break;
                            default:
                                System.out.println(ANSI_RED +
                                        "Número inválido: digite apenas entre 1 - 5\n" + ANSI_RESET);
                                x = false;
                        }
                    } while (!x);

                    String sqlUpdate = "UPDATE filmes SET " + column + " = ? WHERE id = ?"; // pronto
                    PreparedStatement updateState = connection.prepareStatement(sqlUpdate);

                    updateState.setString(1, newValue);
                    updateState.setInt(2, id);
                    status = updateState.executeUpdate();

                    if (status > 0) {
                        System.out.println("\n" + ANSI_GREEN + "Atualizado!" + ANSI_RESET);
                    }
                    Thread.sleep(600);
                    continue;
                case 4:
                    Printers.clearTerminal();
                    Printers.printResultSet(result);
                    result.close();
                    System.out.print(ANSI_YELLOW + "\nDigite o id do filme que deseja remover: " + ANSI_RESET);
                    id = scanner.nextInt();

                    idState.setInt(1, id);
                    haveID = idState.executeQuery();
                    if (!(haveID.next())) {
                        System.out.println(ANSI_RED + "\nid não encontrado" + ANSI_RESET);
                        Thread.sleep(500);
                        continue;
                    }

                    deleteState.setInt(1, id);
                    status = deleteState.executeUpdate();
                    if (status > 0) {
                        System.out.println("\n" + ANSI_GREEN + "Filme removido!" + ANSI_RESET);
                    }
                    Thread.sleep(600);
                    continue;
                case 5:
                    System.out.println("\nEncerrando...");
                    selectState.close();insertState.close();deleteState.close();
                    connection.close();scanner.close();
                    Thread.sleep(500);
                    System.exit(0);
                default:
                    System.out.println(ANSI_RED + "Número inválido: digite apenas entre 1 - 5\n" + ANSI_RESET);
                    Thread.sleep(1500);
                    Printers.clearTerminal();
            }
        }
    }
}
