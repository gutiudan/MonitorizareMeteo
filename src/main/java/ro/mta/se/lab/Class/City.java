package ro.mta.se.lab.Class;

public class City {
    private int cityId;
    private String cityName;
    private double cityLatitude;
    private double cityLongitude;
    private String countryCode;

    public City(){

    }

    public City(int cityId, String cityName, double cityLatitude, double cityLongitude, String countryCode) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityLatitude = cityLatitude;
        this.cityLongitude = cityLongitude;
        this.countryCode = countryCode;
    }

    public String getCityName(){
        return this.cityName;
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

    public String getCountryCode(){
        return this.countryCode;
    }
}
