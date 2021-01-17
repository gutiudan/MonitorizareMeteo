package ro.mta.se.lab.Class;

/**
 * Classname City
 * Implementeaza clasa City care contine
 * detele din fisierul de configurare
 *
 * @author Dan-Cristian Gutiu
 */

public class City {
    /**
     * Declararea membrilor
     */
    private int cityId;
    private String cityName;
    private double cityLatitude;
    private double cityLongitude;
    private String countryCode;

    /**
     * Constructor de clasa City
     */
    public City(){

    }

    /**
     * Constructor de clasa City cu parametrii
     */
    public City(int cityId, String cityName, double cityLatitude, double cityLongitude, String countryCode) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityLatitude = cityLatitude;
        this.cityLongitude = cityLongitude;
        this.countryCode = countryCode;
    }

    /**
     * Getere clasa City
     */
    public String getCityName(){
        return this.cityName;
    }
    public String getCountryCode(){
        return this.countryCode;
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
