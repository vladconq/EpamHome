import java.util.Scanner;

public class ConsoleMain {

    static final String DEFAULT_PATH = "C:\\Users\\" + System.getProperty("user.name");
    static final String POINTER = ">";
    static final String EXIT_CONSOLE = "exit";
    static final String CHANGE_DIRECTORY_FORWARD = "cd";
    static final String CHANGE_DIRECTORY_BACKWARD = "cd..";
    static final String DIRECTORY_LIST = "dir";
    static final String HELPER = "help";
    static final String MAKE_DIRECTORY = "mkdir";
    static final String RENAME_DIRECTORY = "rename";
    static final String DELETE_DIRECTORY = "delete";
    static final String ARCHIVE_DIRECTORY = "zip";
    static final String UNARCHIVE_DIRECTORY = "unzip";
    static String currentPath = DEFAULT_PATH;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String command;
        String statement;

        System.out.print(DEFAULT_PATH + POINTER);
        while (!(statement = input.nextLine()).equalsIgnoreCase(EXIT_CONSOLE)) {
            // Parse the command from statement
            if (statement.indexOf(' ') > 0) {
                command = statement.substring(0, statement.indexOf(' '));
            } else command = statement;
            // Command execution
            switch (command) {
                case CHANGE_DIRECTORY_FORWARD: {
                    CommandExecution.changeDirectoryForward(statement);
                    break;
                }
                case CHANGE_DIRECTORY_BACKWARD: {
                    CommandExecution.changeDirectoryBackward();
                    break;
                }
                case DIRECTORY_LIST: {
                    CommandExecution.directoryList();
                    break;
                }
                case HELPER: {
                    CommandExecution.helper();
                    break;
                }
                case MAKE_DIRECTORY: {
                    CommandExecution.makeDirectory(statement);
                    break;
                }
                case RENAME_DIRECTORY: {
                    CommandExecution.renameDirectory(statement);
                    break;
                }
                case DELETE_DIRECTORY: {
                    CommandExecution.deleteDirectory(statement);
                    break;
                }
                case ARCHIVE_DIRECTORY: {
                    CommandExecution.zipDirectory(statement);
                    break;
                }
                case UNARCHIVE_DIRECTORY: {
                    CommandExecution.unzipDirectory(statement);
                    break;
                }
                default: {
                    System.out.println("\"" + command + "\"" + " is not recognized as a command.");
                    System.out.printf(currentPath + POINTER);
                }
            }
        }
    }
}
