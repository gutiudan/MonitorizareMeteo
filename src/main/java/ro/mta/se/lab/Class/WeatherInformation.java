package ro.mta.se.lab.Class;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class WeatherInformation {

    private String cityName;
    private String currentTime;
    private String descriptionField;
    private int temperatureField;
    private int minTemperatureField;
    private int maxTemperatureField;
    private int pressureField;
    private int humidityField;
    private double windSpeed;
    private String icon;
    private String sunRiseTime;
    private String sunSetTime;

    public WeatherInformation(){

    }
    public void Work(String responseBody){
        Parser parser = Parser.getInstance();
        this.cityName = parser.getValue("name", "String", responseBody);

        int timeZone = Integer.parseInt(parser.getValue("timezone", "int", responseBody));
        long currentTime = Long.parseLong(parser.getValue("dt", "long", responseBody));

        Instant instant = Instant.ofEpochSecond(currentTime + timeZone);
        LocalDateTime datetime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        String formatted = DateTimeFormatter.ofPattern("EEEE dd hh:mm a").format(datetime);

        this.currentTime = formatted;

        long sunRise = Long.parseLong(parser.getObject_getValue("sys", "sunrise", "long", responseBody));
        long sunSet = Long.parseLong(parser.getObject_getValue("sys", "sunset", "long", responseBody));

        instant = Instant.ofEpochSecond(sunRise + timeZone);
        datetime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        formatted = DateTimeFormatter.ofPattern("hh:mm a").format(datetime);
        this.sunRiseTime = formatted;

        instant = Instant.ofEpochSecond(sunSet + timeZone);
        datetime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        formatted = DateTimeFormatter.ofPattern("hh:mm a").format(datetime);
        this.sunSetTime = formatted;

        this.descriptionField = parser.getArray_getObject_getValue("weather", "description", "String", responseBody);
        this.descriptionField = this.descriptionField.substring(0, 1).toUpperCase() + this.descriptionField.substring(1);

        String icon = parser.getArray_getObject_getValue("weather", "icon", "String", responseBody);
        this.icon = "http://openweathermap.org/img/wn/" + icon + "@2x.png";

        this.temperatureField = Integer.parseInt(parser.getObject_getValue("main", "temp", "int", responseBody));
        this.minTemperatureField = Integer.parseInt(parser.getObject_getValue("main", "temp_min", "int", responseBody));
        this.maxTemperatureField = Integer.parseInt(parser.getObject_getValue("main", "temp_max", "int", responseBody));
        this.pressureField = Integer.parseInt(parser.getObject_getValue("main", "pressure", "int", responseBody));
        this.humidityField = Integer.parseInt(parser.getObject_getValue("main", "humidity", "int", responseBody));
        this.windSpeed = Double.parseDouble(parser.getObject_getValue("wind", "speed", "double", responseBody));
    }

    public String getCityName(){
        return this.cityName;
    }

    public String getCurrentTime() {
        return this.currentTime;
    }

    public String getDescriptionField(){
        return this.descriptionField;
    }

    public int getTemperatureField(){
        return this.temperatureField;
    }

    public String getIcon(){
        return this.icon;
    }

    public int getMinTemperatureField(){
        return this.minTemperatureField;
    }

    public int getMaxTemperatureField(){
        return this.maxTemperatureField;
    }

    public int getPressureField(){
        return this.pressureField;
    }

    public int getHumidityField(){
        return this.humidityField;
    }

    public double getWindSpeed(){
        return this.windSpeed;
    }

    public String getSunRiseTime(){
        return  this.sunRiseTime;
    }

    public String getSunSetTime(){
        return this.sunSetTime;
    }
}
