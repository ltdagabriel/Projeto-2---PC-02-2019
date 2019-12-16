package twitterconcurrent;

import twitter4j.JSONObject;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

class JsonData{
    final static  String folder = "C:\\Users\\gabri\\IdeaProjects\\Twitter\\data\\";
    static void writeJson(String filename, JSONObject object) {

        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(folder + filename));
            outputStream.writeObject(object.toString(2));
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            System.err.println("Error: " + e);
        }
    }
}
