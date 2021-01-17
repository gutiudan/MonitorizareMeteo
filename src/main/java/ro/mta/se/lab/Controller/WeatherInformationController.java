package ro.mta.se.lab.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.CDATASection;
import ro.mta.se.lab.Class.*;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image ;
import javafx.scene.paint.Color;

/**
 * Classname clasa WeatherInformationController
 * Implementeaza clasa WeatherInformationController
 * care reprezinta controlerul arhitecturei
 * MVC
 *
 * @author Dan-Cristian Gutiu
 */

public class WeatherInformationController implements Initializable {
    /**
     * Declararea membrilor
     */
    private ObservableList<Country> countryList;
    private int minTemperature;
    private int maxTemperature;

    @FXML
    private ComboBox countryField;
    @FXML
    private ComboBox cityField;

    @FXML
    private Label cityName;

    @FXML
    private Label dateField;

    @FXML
    private Label descriptionField;

    @FXML
    private Label temperatureField;

    @FXML
    private Label minTemperatureField;

    @FXML
    private Label maxTemperatureField;

    @FXML
    private ImageView imageField;

    @FXML
    private Label celsiusField;

    @FXML
    private Label lineField;

    @FXML
    private Label fahrenheitField;

    @FXML
    private Label pressureField;

    @FXML
    private Label humidityField;

    @FXML
    private Label windSpeed;

    @FXML
    private Label sunRise;

    @FXML
    private Label sunSet;

    private boolean celsiusButton = true;
    private boolean fahrenheitButton = false;

    /**
     * Transformare din grade Celsius
     * in grade Fahrenheit
     */
    public double toFahrenheit(int fromCelsius){
        return ((fromCelsius) * 1.8 + 32);
    }

    /**
     * Transformare din grade Fahrenheit
     * in grade Celsius
     */
    public double toCelsius(int fromFahrenheit){
        return ((fromFahrenheit - 32) / 1.8);
    }

    /**
     * Seteaza toate elementele interfetei
     * cu null
     */
    public void setAllNull(){
        cityName.setText("");
        dateField.setText("");
        descriptionField.setText("");
        temperatureField.setText("");
        minTemperatureField.setText("");
        maxTemperatureField.setText("");
        imageField.setImage(null);
        celsiusField.setText("");
        lineField.setText("");
        fahrenheitField.setText("");
        pressureField.setText("");
        humidityField.setText("");
        windSpeed.setText("");
        sunRise.setText("");
        sunSet.setText("");
    }

    /**
     * Functie care la selectarea
     * tarii afiseaza orasele
     * corespunzatoare
     */
    @FXML
    private void SelectCountry(ActionEvent event){
        setAllNull();
        ObservableList<City> cityList = FXCollections.observableArrayList();
        ObservableList<String> citiesNames = FXCollections.observableArrayList();
        for(Country country : countryList){
            if (country.getCountryCode().equals(countryField.getSelectionModel().getSelectedItem().toString())){
                cityList = country.getCountryCityList();
                break;
            }
        }
        for(City city : cityList){
            citiesNames.add(city.getCityName());
        }
        cityField.setItems(citiesNames);
    }

    /**
     * Functie care se ocupa cu
     * parsarea datelor JSON si
     * completeaza elementele intefetei
     */
    @FXML
    private void SelectCity(ActionEvent event){
        if (!cityField.getSelectionModel().isEmpty()){

            String apiKey = null;
            try {
                apiKey = "98de787066a4c24216f834be520b4726";
                String newURL = "http://api.openweathermap.org/data/2.5/weather?q=" + cityField.getSelectionModel().getSelectedItem().toString() + "&appid=" + apiKey;
                World world = World.getInstance();
                City currentCity = new City();
                for(Country country : world.getWorldCountryList()){
                    for(City city : country.getCountryCityList()){
                        if (city.getCityName().equals(cityField.getAccessibleText())){
                            currentCity = city;
                            break;
                        }
                    }
                }

                Page page = new Page(currentCity);
                page.Read(newURL);
                boolean isOk = page.verifyInformation();
                if (isOk){
                    String responseBody = page.getResponseBody();
                    WeatherInformation weatherInformation = new WeatherInformation();
                    weatherInformation.Work(responseBody);

                    this.cityName.setText(weatherInformation.getCityName());
                    this.dateField.setText(weatherInformation.getCurrentTime());
                    this.descriptionField.setText(weatherInformation.getDescriptionField());
                    this.temperatureField.setText(String.valueOf(weatherInformation.getTemperatureField() - 273));
                    this.minTemperatureField.setText("Temp min: " + String.valueOf(weatherInformation.getMinTemperatureField()- 273) + " \u2103");
                    this.maxTemperatureField.setText("Temp max: " + String.valueOf(weatherInformation.getMaxTemperatureField() - 273) + " \u2103");
                    this.pressureField.setText("Presiune: " + String.valueOf(weatherInformation.getPressureField()) + " hPa");
                    this.humidityField.setText("Umiditate: " + String.valueOf(weatherInformation.getHumidityField()) + "%");
                    this.windSpeed.setText("Viteza: " + String.valueOf(weatherInformation.getWindSpeed()) + "km/h");
                    this.sunRise.setText("Rasarit: " + weatherInformation.getSunRiseTime());
                    this.sunSet.setText("Apus: " + weatherInformation.getSunSetTime());

                    Image img = new Image(weatherInformation.getIcon());
                    imageField.setImage(img);
                    this.imageField.setImage(img);
                    celsiusField.setTextFill(Color.web("Black"));
                    celsiusField.setText("\u2103");
                    lineField.setText("|");
                    fahrenheitField.setTextFill(Color.web("#26a6bf"));
                    fahrenheitField.setText("\u2109");

                    minTemperature = weatherInformation.getMinTemperatureField() - 273;
                    maxTemperature = weatherInformation.getMaxTemperatureField() - 273;
                    fahrenheitButton = false;
                    celsiusButton = true;

                    Logger logger = Logger.getInstance();
                    logger.writeDate_City(this.cityName.getText());
                    logger.writeToFile("Temp: " + this.temperatureField.getText() + " C"
                            + " Temp_min: " + minTemperature + " C" +
                            " Temp_max: " + maxTemperature + " C");
                    logger.writeToFile(this.pressureField.getText() + " " + this.humidityField.getText() + " " +
                            this.windSpeed.getText());
                    logger.writeToFile(this.sunRise.getText() + " " + this.sunSet.getText());
                    logger.writeToFile("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Functie care transforma temperaturile
     * din grade Celsius in Fahrenheit
     */
    @FXML
    private void fahrenheitClick(MouseEvent event){
        if (!fahrenheitButton && celsiusButton) {

            fahrenheitField.setTextFill(Color.web("Black"));
            celsiusField.setTextFill(Color.web("#26a6bf"));

            int celsius_temperature = Integer.parseInt(temperatureField.getText());
            double fahrenheit_temperature = this.toFahrenheit(celsius_temperature);
            temperatureField.setText(String.valueOf((int) fahrenheit_temperature));

            double minTemp = this.toFahrenheit(minTemperature);
            minTemperature = (int) minTemp;
            minTemperatureField.setText("Temp min " + String.valueOf(minTemperature) + " \u2109");

            double maxTemp = this.toFahrenheit(maxTemperature);
            maxTemperature = (int) maxTemp;
            maxTemperatureField.setText("Temp max " + String.valueOf(maxTemperature) + " \u2109");

            celsiusButton = false;
            fahrenheitButton= true;

        }
    }

    /**
     * Functie care transforma temperaturile
     * din grade Fahrenheit in Celsius
     */
    @FXML
    private void celsiusClick(MouseEvent event){
        if (!celsiusButton && fahrenheitButton) {


            celsiusField.setTextFill(Color.web("Black"));
            fahrenheitField.setTextFill(Color.web("#26a6bf"));
            int fahrenheit_temperature = Integer.parseInt(temperatureField.getText());
            double celsius_temperature = this.toCelsius(fahrenheit_temperature);
            temperatureField.setText(String.valueOf((int) celsius_temperature));

            double minTemp = this.toCelsius(minTemperature);
            minTemperature = (int) minTemp;
            minTemperatureField.setText("Temp min " + String.valueOf(minTemperature) + " \u2103");

            double maxTemp = this.toCelsius(maxTemperature);
            maxTemperature = (int) maxTemp;
            maxTemperatureField.setText("Temp max " + String.valueOf(maxTemperature) + " \u2103");

            fahrenheitButton = false;
            celsiusButton = true;

        }
    }

    /**
     * Supreascrierea Clasei initialize din
     * Initializable
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> CountriesCode = FXCollections.observableArrayList();
        for(Country country : countryList){
            CountriesCode.add(country.getCountryCode());
        }
        countryField.setItems(CountriesCode);
    }

    /**
     * Geter clasa WeatherInformationController
     */
    public WeatherInformationController(ObservableList<Country> countryList) {
        this.countryList = countryList;
    }
}
