import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class CountryDataHandler {
    @FXML
    private TextField countrySelected;
    @FXML
    private Text noCountryInput;
    @FXML
    private Text cases, todaysCases, deaths, todaysDeaths, recovered,
            active, critical, casesPerMillion, deathsPerMillion, totalTests, testsPerMillion;


    static void openCountryDataPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("CountryData.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        Stage stage = new Stage();
        stage.setTitle("Covid-19");
        stage.setScene(scene);
        stage.show();
    }

    public void displayCountryData() {
        String toFilter = countrySelected.getText();
        try {
            toFilter = DataProvider.getCountryData(toFilter);
            //filter the big boy of unnecessary characters
            toFilter = DataProvider.dataWithFilteredChars(toFilter);
            List<Text> allTexts = List.of(cases, todaysCases, deaths, todaysDeaths, recovered,
                    active, critical, casesPerMillion, deathsPerMillion, totalTests, testsPerMillion);
            List<String> attributes = DataProvider.defineAttributes(toFilter);
            noCountryInput.setText("");
            IntStream.range(0, allTexts.size()).forEach(i -> allTexts.get(i).setText(attributes.get(i)));
        } catch (RuntimeException | IOException e) {
            if (toFilter.isEmpty())
            noCountryInput.setText("no input");
            else noCountryInput.setText("Not a valid country");
        }
    }
}
