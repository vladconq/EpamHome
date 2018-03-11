import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class CommandExecution {

    public static void changeDirectoryForward(String statement) {
        final int LENGTH_CD_AND_BS = 3;
        File file = new File(ConsoleMain.currentPath + "\\" + statement.substring(LENGTH_CD_AND_BS));
        if (file.exists() && file.isDirectory()) {
            if (ConsoleMain.currentPath.charAt(ConsoleMain.currentPath.length() - 1) != '\\')
                ConsoleMain.currentPath = ConsoleMain.currentPath + "\\" + statement.substring(LENGTH_CD_AND_BS);
            else ConsoleMain.currentPath = ConsoleMain.currentPath + statement.substring(LENGTH_CD_AND_BS);
            System.out.printf(ConsoleMain.currentPath + ConsoleMain.POINTER);
        } else {
            System.out.println("The system cannot find the path specified.");
            System.out.printf(ConsoleMain.currentPath + ConsoleMain.POINTER);
        }
    }

    public static void changeDirectoryBackward() {
        File file = new File(ConsoleMain.currentPath);
        if (file.getParent() != null)
            ConsoleMain.currentPath = file.getParent();
        System.out.printf(ConsoleMain.currentPath + ConsoleMain.POINTER);
    }

    public static void directoryList() {
        Set<String> list = new TreeSet();
        File file = new File(ConsoleMain.currentPath);
        for (File temp : file.listFiles()) {
            list.add(temp.toString().substring(temp.toString().lastIndexOf('\\')));
        }
        for (String name : list) {
            System.out.println(name);
        }
        System.out.printf(ConsoleMain.currentPath + ConsoleMain.POINTER);
    }

    public static void helper() {
        try (FileReader reader = new FileReader(System.getProperty("user.dir") + "\\Helper.txt")) {
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            System.out.println("The system cannot find the helper.");
        }
        System.out.printf(ConsoleMain.currentPath + ConsoleMain.POINTER);
    }

    public static void makeDirectory(String statement) {
        final int LENGTH_MKDIR_AND_BS = 6;
        File file = new File(ConsoleMain.currentPath + "\\" + statement.substring(LENGTH_MKDIR_AND_BS));
        if (file.exists()) {
            System.out.println("A subdirectory or file already exists.");
        } else {
            file.mkdir();
        }
        System.out.printf(ConsoleMain.currentPath + ConsoleMain.POINTER);
    }

    public static void renameDirectory(String statement) {
        String oldName = "";
        String newName = "";

        int countOfQuotes = 0;
        for (int i = 0; i < statement.length(); i++) {
            if (countOfQuotes == 1 && statement.charAt(i) != '\"') {
                oldName = oldName + statement.charAt(i);
            }
            if (countOfQuotes == 3 && statement.charAt(i) != '\"') {
                newName = newName + statement.charAt(i);
            }
            if (statement.substring(i, i + 1).equals("\""))
                countOfQuotes++;
        }

        if (countOfQuotes != 4) {
            System.out.println("The syntax of the command is incorrect.");
        }

        File oldFile = new File(ConsoleMain.currentPath + "\\" + oldName);
        File newFile = new File(ConsoleMain.currentPath + "\\" + newName);
        if (oldFile.exists()) {
            oldFile.renameTo(newFile);
        } else System.out.println("The system cannot find the file specified.");
        System.out.printf(ConsoleMain.currentPath + ConsoleMain.POINTER);
    }

    public static void deleteDirectory(String statement) {
        final int LENGTH_DELETE_AND_BS = 7;
        File file = new File(ConsoleMain.currentPath + "\\" + statement.substring(LENGTH_DELETE_AND_BS));
        helperForDeleteDirectory(file);
        System.out.printf(ConsoleMain.currentPath + ConsoleMain.POINTER);
    }

    public static void helperForDeleteDirectory(File file) {
        if (file.isDirectory()) {
            String[] filesToDelete = file.list();
            for (int i = 0; i < filesToDelete.length; i++) {
                File f = new File(file, filesToDelete[i]);
                helperForDeleteDirectory(f);
            }
            file.delete();
        } else file.delete();
    }

    public static void zipDirectory(String statement) {
        final int LENGTH_ZIP_AND_BS = 4;
        String name = statement.substring(LENGTH_ZIP_AND_BS);

        byte[] buffer = new byte[1024];
        try {
            FileOutputStream fos = new FileOutputStream(ConsoleMain.currentPath + "\\" + name + ".zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            ZipEntry ze = new ZipEntry(name);
            zos.putNextEntry(ze);
            FileInputStream in = new FileInputStream(ConsoleMain.currentPath + "\\" + name);
            int len;
            while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
        } catch (IOException ex) {
            System.out.println("Can't zip the file!");
        }
        System.out.printf(ConsoleMain.currentPath + ConsoleMain.POINTER);
    }

    public static void unzipDirectory(String statement) {
        final int LENGTH_UNZIP_AND_BS = 4;
        String name = statement.substring(LENGTH_UNZIP_AND_BS);

        String INPUT_ZIP_FILE = ConsoleMain.currentPath + "\\" + name + ".zip";
        String OUTPUT_FOLDER = ConsoleMain.currentPath + "\\" + name;
        byte[] buffer = new byte[1024];
        try {
            File folder = new File(OUTPUT_FOLDER);
            if (!folder.exists()) {
                folder.mkdir();
            }
            ZipInputStream zis = new ZipInputStream(new FileInputStream(INPUT_ZIP_FILE));
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(OUTPUT_FOLDER + File.separator + fileName);
                System.out.println("file unzip : " + newFile.getAbsoluteFile());
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                ze = zis.getNextEntry();
            }
        } catch (IOException ex) {
            System.out.println("Can't zip the file!");
        }
    }
}