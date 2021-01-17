package ro.mta.se.lab.Class;

    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Scene;
    import javafx.stage.Stage;
    import ro.mta.se.lab.Controller.WeatherInformationController;

    import java.io.File;
    import java.io.IOException;
    import java.util.List;
    import java.util.Scanner;

public class Main extends Application
    {
        public static void main(String[] args)
        {
            launch(args);
        }

        public void ReadFile(){
            World world = World.getInstance();
            try {
                File myObj = new File("src\\main\\java\\entry.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    String[] words = line.split("\\s+");
                    if (words.length != 5) {
                        throw new Exception();
                    }
                    else{
                        int cityId = Integer.parseInt(words[0]);
                        String cityName = words[1];
                        double cityLatitude = Double.parseDouble(words[2]);
                        double cityLongitude = Double.parseDouble(words[3]);
                        String countryCode = words[4];

                        boolean isOk = true;
                        Country existCountry = new Country();
                        List<Country> countryList = world.getWorldCountryList();
                        for(Country myCountry : countryList){
                            if (myCountry.getCountryCode().equals(countryCode)){
                                isOk = false;
                                existCountry = myCountry;
                                break;
                            }
                        }
                        if (isOk){
                            Country newCountry = new Country(countryCode);
                            City newCity = new City(cityId, cityName, cityLatitude, cityLongitude, countryCode);
                            newCountry.Add_City(newCity);
                            world.AddCountry(newCountry);
                        }
                        else{
                            City newCity = new City(cityId, cityName, cityLatitude, cityLongitude, countryCode);
                            existCountry.Add_City(newCity);
                        }
                    }
                }
                myReader.close();
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        public void start(Stage primaryStage) throws IOException
        {
            ReadFile();
            FXMLLoader loader = new FXMLLoader();
            try {
                loader.setLocation(this.getClass().getResource("/view/Test.fxml"));
                loader.setController(new WeatherInformationController(World.getInstance().getWorldCountryList()));
                primaryStage.setScene(new Scene(loader.load()));
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
