package ro.mta.se.lab.Class;

import javafx.collections.FXCollections;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Classname Parser
 * Implementeaza clasa Singleton Parser
 * care se ocupa cu parsarea datelor JSON
 *
 * @author Dan-Cristian Gutiu
 */

public class Parser {
    /**
     * Declararea membrilor
     */
    private static Parser INSTANCE = null;

    /**
     * Obtinerea instantei
     */
    public static Parser getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Parser();
        }
        return INSTANCE;
    }

    /**
     * Constructor de clasa Paser
     */
    public Parser(){

    }

    /**
     * Parsarea datelor dupa obiectul JSON
     * si o anumita valoare
     */
    public String getValue(String searchWord, String dataType, String jsonText){
        JSONObject obj = new JSONObject(jsonText);
        if (dataType.equals("int")){
            String value = String.valueOf(obj.getInt(searchWord));
            return value;
        }
        if (dataType.equals("String")){
            String value = obj.getString(searchWord);
            return value;
        }
        if (dataType.equals("long")){
            String value = String.valueOf(obj.getLong(searchWord));
            return value;
        }
        return "null";
    }

    /**
     * Parsarea datelor dupa o anumita valoare
     */
    public String getObject_getValue(String objectName, String searchWord, String dataType, String jsonText){
        JSONObject obj = new JSONObject(jsonText);
        JSONObject newObj = obj.getJSONObject(objectName);
        if (dataType.equals("double")){
            String value = String.valueOf(newObj.getDouble(searchWord));
            return value;
        }
        if (dataType.equals("String")){
            String value = newObj.getString(searchWord);
            return value;
        }
        if (dataType.equals("long")){
            String value = String.valueOf(newObj.getLong(searchWord));
            return value;
        }
        if (dataType.equals("int")){
            String value = String.valueOf(newObj.getInt(searchWord));
            return value;
        }
        return "null";
    }

    /**
     * Parsarea datelor dupa un anumit
     * vector, obiect si o anumita
     * valoare
     */
    public String getArray_getObject_getValue(String objectArray, String searchWord, String dataType, String jsonText){
        JSONObject obj = new JSONObject(jsonText);
        JSONArray objArray = obj.getJSONArray(objectArray);
        if (dataType.equals("String")) {
            String value = objArray.getJSONObject(0).getString(searchWord);
            return value;
        }
        return "null";
    }

}
