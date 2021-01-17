package ro.mta.se.lab.Class;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class World {
    private ObservableList<Country> worldCountryList;
    private static World INSTANCE = null;
    private World(){
        this.worldCountryList = FXCollections.observableArrayList();
    }
    public static World getInstance(){
        if (INSTANCE == null){
            INSTANCE = new World();
        }
        return INSTANCE;
    }

    public World(ObservableList<Country> worldCountryList) {
        this.worldCountryList = worldCountryList;
    }

    public ObservableList<Country> getWorldCountryList(){
        return worldCountryList;
    }

    public void AddCountry(Country country){
        worldCountryList.add(country);
    }
}
