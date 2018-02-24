import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

public class Browser {

    static int countAllFiles = 0;
    static int countMP3Files = 0;
    static List<String> listWithMp3Files = new LinkedList();
    static final int LIMIT_SIZE_OF_BYTES = 120000;
    static final int SIZE_OF_TAG_ID3 = 3;

    public static void findFile() {
        File dir = new File(MusicCacheMain.nameSourceDir);
        String[] filesInCatalog = dir.list();
        try {
            for (String tempName : filesInCatalog) {
                if ((ChoiceUserFolderAndBrowser.currentBrowser.equals("Chrome") && tempName.charAt(0) == 'f') || ChoiceUserFolderAndBrowser.currentBrowser.equals("Firefox"))
                    findMP3(tempName);
                countAllFiles++;
            }
        } catch (NullPointerException e) {
            System.err.println("Cache files don't found in the folder!");
            System.exit(0);
        }
    }

    public static void findMP3(String nameFile) {
        int tagID3Symbols = 0; // Если в файле имеется тэг ID3, то это mp3 файл
        String wayToFile = MusicCacheMain.nameSourceDir + "\\" + nameFile;
        File file = new File(wayToFile);
        StringBuilder sb = new StringBuilder();

        try (Reader reader = new InputStreamReader(new FileInputStream(wayToFile), "UTF8")) {
            int data = reader.read();
            while (tagID3Symbols < SIZE_OF_TAG_ID3 && file.length() > LIMIT_SIZE_OF_BYTES) {
                sb.append((char) data);
                data = reader.read();
                tagID3Symbols++;
            }
            if (sb.toString().equals("ID3")) {
                listWithMp3Files.add(nameFile.toString());
                countMP3Files++;
            }
            sb.setLength(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyCaches() {
        for (String temp : listWithMp3Files) {
            File target = new File(ChoiceUserFolderAndBrowser.chosenFolder + "\\" + temp + ".mp3");
            String wayToFile = MusicCacheMain.nameSourceDir + "\\" + temp;
            File source = new File(wayToFile);
            try {
                Files.copy(source.toPath(), target.toPath());
            } catch (FileAlreadyExistsException e) {
                System.err.println(temp + " is already exists in folder!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
