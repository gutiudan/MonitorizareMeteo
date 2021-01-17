package ro.mta.se.lab.Class;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classname Logger
 * Implementeaza clasa Singleton Logger
 * care scrie informatiile despre istoricul
 * afisarilor
 *
 * @author Dan-Cristian Gutiu
 */

public class Logger {
    /**
     * Declararea membrilor
     */
    private static Logger INSTANCE = null;
    private String fileName = "log.txt";

    /**
     * Obtinerea instantei
     */
    public static Logger getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Logger();
        }
        return INSTANCE;
    }

    /**
     * Scrierea in fisier a datei
     *  impreuna cu numele orasului
     */
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

    /**
     * Scrierea in fisier a informatiilor
     */
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
