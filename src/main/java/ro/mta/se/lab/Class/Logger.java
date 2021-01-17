package ro.mta.se.lab.Class;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static Logger INSTANCE = null;
    private String fileName = "log.txt";
    public static Logger getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Logger();
        }
        return INSTANCE;
    }
    public void writeDate_City(String text){
        try {
            BufferedWriter myWriter = new BufferedWriter(
                    new FileWriter(fileName, true));
            SimpleDateFormat formatter= new SimpleDateFormat("EEEE dd hh:mm a");
            Date date = new Date(System.currentTimeMillis());
            myWriter.write(formatter.format(date) + " " + text + "\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void writeToFile(String text){
        try {
            BufferedWriter myWriter = new BufferedWriter(
                    new FileWriter(fileName, true));
            SimpleDateFormat formatter= new SimpleDateFormat("EEEE dd hh:mm a");
            Date date = new Date(System.currentTimeMillis());
            myWriter.write(text + "\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
