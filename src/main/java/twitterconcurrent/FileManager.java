package twitterconcurrent;

import twitter4j.JSONObject;

import java.io.*;
import java.net.URL;

class FileManager {
    final static String folder = "C:\\Users\\gabri\\IdeaProjects\\Twitter\\data\\";
    final static String urlFolder = "C:\\Users\\gabri\\IdeaProjects\\Twitter\\data\\url\\";

    static void writeJson(String filename, JSONObject object) {

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(folder + filename));
            outputStream.writeObject(object.toString(2));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    static String downloadUrl(String url) {
        String saveAs;
        try {
            URL objURL = new URL(url);
            saveAs = String.format("%s%s.html", urlFolder, objURL.getFile());
            try (BufferedInputStream inputStream = new BufferedInputStream(objURL.openStream());
                 FileOutputStream fileOS = new FileOutputStream(saveAs)) {
                byte[] data = new byte[1024];
                int byteContent;
                while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                    fileOS.write(data, 0, byteContent);
                }
            } catch (IOException e) {
                // handles IO exceptions
            }
            return saveAs;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
