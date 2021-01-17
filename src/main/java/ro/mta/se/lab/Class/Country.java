package ro.mta.se.lab.Class;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classname Countru
 * Implementeaza clasa Country care contine
 * o lista de orase
 *
 * @author Dan-Cristian Gutiu
 */

public class Country  {
    /**
     * Declararea membrilor
     */
    private ObservableList<City> countryCityList;
    private String countryCode;

    /**
     * Constructor de clasa Country
     */
    public Country(){
        this.countryCityList = FXCollections.observableArrayList();
    }

    /**
     * Constructor de clasa Country
     * cu parametrii
     */
    public Country(String countryCode){
        this.countryCode = countryCode;
        this.countryCityList = FXCollections.observableArrayList();
    }

    /**
     * Functii de adaugare orase
     */
    public Country(ObservableList<City> countryCityList) {
        this.countryCityList = countryCityList;
    }
    public void Add_City(City city){
        this.countryCityList.add(city);
    }

    /**
     * Getere clasa City
     */
    public String getCountryCode(){
        return countryCode;
    }
    public ObservableList<City> getCountryCityList(){
        return countryCityList;
    }
}
