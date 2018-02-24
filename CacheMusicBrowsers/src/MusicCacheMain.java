import java.util.Scanner;

public class MusicCacheMain {

    static public String nameSourceDir;

    public static void main(String[] args) {

        System.out.print(
                "Create or select a folder to store the converted cache files. " +
                        "Example input: C:\\Users\\" + System.getProperty("user.name") + "\\New Folder" + "\n" +
                        "Your input: ");
        ChoiceUserFolderAndBrowser.chooseFolder(new Scanner(System.in).nextLine());

        System.out.print(
                "\n" + "This program can work with browsers: " + "\n" +
                        "1. Firefox" + "\n" +
                        "2. Chrome" + "\n" +
                        "To select a browser, press 1 or 2 respectively (0 - exit the program)." + "\n" +
                        "Your choice: ");
        ChoiceUserFolderAndBrowser.chooseBrowser(new Scanner(System.in).nextInt());
    }
}
