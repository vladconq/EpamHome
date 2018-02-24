import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.tritonus.share.sampled.TAudioFormat;

public class GetNameAndRenameJLayer {
    static String cacheName;

    public static void getName() {
        int count = 0;
        String songTitle = "", songAuthor = "";
        for (String temp : Browser.listWithMp3Files) {
            cacheName = ChoiceUserFolderAndBrowser.chosenFolder + "\\" + temp + ".mp3";
            File file = new File(cacheName);
            AudioFileFormat baseFileFormat = null;
            AudioFormat baseFormat = null;
            try {
                baseFileFormat = AudioSystem.getAudioFileFormat(file);
            } catch (UnsupportedAudioFileException e) {
                System.out.println("The format of this file is not supported, the file extension is incorrect or the file is corrupted.");
                file.delete();
                continue;
            } catch (IOException e) {
                e.printStackTrace();
            }
            baseFormat = baseFileFormat.getFormat();
            // TAudioFormat properties
            if (baseFormat instanceof TAudioFormat) {
                Map properties = baseFileFormat.properties();
                songAuthor = (String) properties.get("author");
                if (songAuthor == null)
                    songAuthor = "Unknown";
                songTitle = (String) properties.get("title");
                if (songTitle == null)
                    songTitle = "Artist";
            }
            count++;
            rename(count + ". " + songAuthor + " - " + songTitle + ".mp3");
        }
    }

    public static void rename(String nameFull) {
        final File oldFile = new File(cacheName);
        final File newFile = new File(ChoiceUserFolderAndBrowser.chosenFolder, nameFull);
        newFile.delete();
        if (oldFile.exists() && !newFile.exists()) {
            if (oldFile.renameTo(newFile)) {
                System.out.println("The file " + cacheName + " is renamed.");
            } else {
                System.err.println("The file " + cacheName + " isn't renamed.");
            }
        }
    }
}
