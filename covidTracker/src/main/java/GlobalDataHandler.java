import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public final class GlobalDataHandler implements Initializable {
  private final String COVID_NEWS = "www.google.com/search?q=coronavirus+news";
  private final String GENERAL_COVID_INFO = "www.google.com/search?q=coronavirus+cases";
  @FXML public Button a;
  @FXML private Text cases, deaths, recovered;
  @FXML private FontIcon SEARCH;
  @FXML private FontIcon GLOBE;

  @FXML
  public void refreshData() throws IOException {
    cases.setText(DataProvider.getGlobalCases());
    deaths.setText(DataProvider.getGlobalDeaths());
    recovered.setText(DataProvider.getGlobalRecovered());
  }

  private void openURL(String URL) throws IOException, URISyntaxException {
    Desktop.getDesktop().browse(new URI(URL));
  }

  public final void openNews() throws IOException, URISyntaxException {
    openURL(COVID_NEWS);
  }

  public final void openGeneralInfo() throws IOException, URISyntaxException {
    openURL(GENERAL_COVID_INFO);
  }

  public void openCountryPage() throws IOException {
    CountryDataHandler.openCountryDataPage();
    Stage currentStage = (Stage) SEARCH.getScene().getWindow();
    currentStage.close();
  }

  public void openAllCountriesPage() throws IOException {
    new AllCountriesHandler().openCountryDataPage();
  }

  public void quit() {
    System.exit(0);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      refreshData();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
