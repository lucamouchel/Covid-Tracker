import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(CountryDataHandler.class.getResource("CountryData.fxml")));
        Stage primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.show();
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
            if (toFilter.isEmpty()) noCountryInput.setText("no input");
            else noCountryInput.setText("Not a valid country");
        }
    }

    public void quit() {
        System.exit(0);
    }
}
