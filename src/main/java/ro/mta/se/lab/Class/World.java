package ro.mta.se.lab.Class;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classname World
 * Implementeaza clasa Singleton World care contine
 * o lista de tari
 *
 * @author Dan-Cristian Gutiu
 */

public class World {
    /**
     * Declararea membrilor
     */
    private ObservableList<Country> worldCountryList;
    private static World INSTANCE = null;

    /**
     * Constructor de clasa World
     */
    private World(){
        this.worldCountryList = FXCollections.observableArrayList();
    }

    /**
     * Obtinerea instantei
     */
    public static World getInstance(){
        if (INSTANCE == null){
            INSTANCE = new World();
        }
        return INSTANCE;
    }

    /**
     * Constructor de clasa World cu parametrii
     */
    public World(ObservableList<Country> worldCountryList) {
        this.worldCountryList = worldCountryList;
    }

    /**
     * Adaugare tari
     */
    public void AddCountry(Country country){
        worldCountryList.add(country);
    }

    /**
     * Geter clasa World
     */
    public ObservableList<Country> getWorldCountryList(){
        return worldCountryList;
    }

}
