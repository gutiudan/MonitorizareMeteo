package ro.mta.se.lab.Class;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Classname Page
 * Implementeaza clasa Page care extrage
 * obiectul JSON
 *
 * @author Dan-Cristian Gutiu
 */

public class Page {
    /**
     * Declararea membrilor
     */
    private int cityId;
    private String cityName;
    private double cityLatitude;
    private double cityLongitude;
    private String countryCode;
    private City cityInformation;
    private String responseBody;

    /**
     * Constructor de clasa Page
     */
    public Page(){

    }

    /**
     * Constructor de clasa Page
     * cu parametrii
     */
    public Page(City city) {
        this.cityInformation = city;
    }

    /**
     * Obtinerea obiectului JSON
     */
    public void Read(String newURL){
        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(newURL)).build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(this::getInformation)
                    .join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verificicarea corectitudinii
     * informatiilor
     */
    public boolean verifyInformation(){
        if (this.cityId == this.getCityId() && this.cityName.equals(this.getCityName()) &&
                this.cityLatitude == this.getCityLatitude() && this.cityLongitude == this.getCityLongitude() &&
                this.countryCode.equals(this.getCountryCode())){
            return true;
        }
        return false;
    }

    /**
     * Obtinerea informatiilor pe baza
     * obiectului JSON
     */
    public void getInformation(String responseBody){
        Parser parser = Parser.getInstance();
        this.cityId = Integer.parseInt(parser.getValue("id", "int", responseBody));
        this.cityName = parser.getValue("name", "String", responseBody);
        this.cityLatitude = Double.parseDouble(parser.getObject_getValue("coord", "lat",
                "double", responseBody));
        this.cityLongitude = Double.parseDouble(parser.getObject_getValue("coord", "lon",
                "double", responseBody));
        this.countryCode = parser.getObject_getValue("sys", "country", "String",
                responseBody);

        this.responseBody = responseBody;
    }

    /**
     * Getere clasa Page
     */
    public String getCityName(){
        return this.cityName;
    }
    public String getCountryCode(){
        return this.countryCode;
    }
    public String getResponseBody(){
        return this.responseBody;
    }
    public int getCityId(){
        return this.cityId;
    }
    public double getCityLatitude(){
        return this.cityLatitude;
    }
    public double getCityLongitude(){
        return this.cityLongitude;
    }
}
