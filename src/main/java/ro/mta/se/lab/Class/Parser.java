package ro.mta.se.lab.Class;

import javafx.collections.FXCollections;
import org.json.JSONArray;
import org.json.JSONObject;

public class Parser {

    private static Parser INSTANCE = null;

    public static Parser getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Parser();
        }
        return INSTANCE;
    }

    public Parser(){

    }
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
