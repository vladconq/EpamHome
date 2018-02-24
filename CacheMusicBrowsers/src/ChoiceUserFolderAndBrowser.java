import java.io.File;

public class ChoiceUserFolderAndBrowser {
    static String chosenFolder;
    static String currentBrowser;

    public static void chooseFolder(String choiceFolder) {
        File folder = new File(choiceFolder);
        if (folder.exists() && folder.isDirectory()) {
            System.out.println("Folder is selected.");
            for (File temp : folder.listFiles()) {
                temp.delete();
            }
            chosenFolder = choiceFolder;
        } else {
            folder.mkdirs();
            System.out.println("Folder is created.");
            chosenFolder = choiceFolder;
        }
    }

    public static void chooseBrowser(int choice) {
        switch (choice) {
            case 1: {
                currentBrowser = "Firefox";
                MusicCacheMain.nameSourceDir =
                        "C:\\Users\\" + System.getProperty("user.name") +
                                "\\AppData\\Local\\Mozilla\\Firefox\\Profiles\\ga47irro.default\\cache2\\entries";
                Browser.findFile();
                Browser.copyCaches();
                GetNameAndRenameJLayer.getName();
                break;
            }
            case 2: {
                currentBrowser = "Chrome";
                MusicCacheMain.nameSourceDir =
                        "C:\\Users\\" + System.getProperty("user.name") +
                                "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Media Cache";
                Browser.findFile();
                Browser.copyCaches();
                GetNameAndRenameJLayer.getName();
                break;
            }
            case 0: {
                System.exit(0);
            }
        }
    }
}
