import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

public class CountryDataHandler {
  @FXML private TextField countrySelected;
  @FXML private Text noCountryInput;
  @FXML
  private Text cases,
      todaysCases,
      deaths,
      todaysDeaths,
      recovered,
      active,
      critical,
      casesPerMillion,
      deathsPerMillion,
      totalTests,
      testsPerMillion;

  static void openCountryDataPage() throws IOException {
    Parent root =
        FXMLLoader.load(
            Objects.requireNonNull(CountryDataHandler.class.getResource("CountryData.fxml")));
    Stage primaryStage = new Stage();
    primaryStage.setScene(new Scene(root, 900, 700));
    primaryStage.show();
  }

  public void displayCountryData() {
    String toFilter = countrySelected.getText();
    try {
      Country c =
          new Country(new JSONObject(DataProvider.getCountryData(countrySelected.getText())));
      cases.setText(c.getCases());
      todaysCases.setText(c.getTodaysCases());
      deaths.setText(c.getDeaths());
      todaysDeaths.setText(c.getTodaysDeaths());
      recovered.setText(c.getRecovered());
      active.setText(c.getActiveCases());
      critical.setText(c.getCriticalCases());
      casesPerMillion.setText(c.getCasesPerMillion());
      deathsPerMillion.setText(c.getDeathsPerMillion());
      totalTests.setText(c.getTotalTests());
      testsPerMillion.setText(c.getTestsPerMillion());
      noCountryInput.setText("");
    } catch (RuntimeException | IOException e) {
      if (toFilter.isEmpty()) noCountryInput.setText("no input");
      else noCountryInput.setText("Not a valid country");
    }
  }

  public void quit() {
    System.exit(0);
  }
}
