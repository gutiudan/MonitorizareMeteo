package ro.mta.se.lab.Class;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Country  {
    private ObservableList<City> countryCityList;
    private String countryCode;

    public Country(){
        this.countryCityList = FXCollections.observableArrayList();
    }

    public Country(String countryCode){
        this.countryCode = countryCode;
        this.countryCityList = FXCollections.observableArrayList();
    }
    public Country(ObservableList<City> countryCityList) {
        this.countryCityList = countryCityList;
    }

    public String getCountryCode(){
        return countryCode;
    }

    public void Add_City(City city){
        this.countryCityList.add(city);
    }

    public ObservableList<City> getCountryCityList(){
        return countryCityList;
    }
}
