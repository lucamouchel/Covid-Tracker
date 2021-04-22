import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public final class GlobalDataHandler {
    private final String COVID_NEWS = "www.google.com/search?q=coronavirus+news";
    private final String GENERAL_COVID_INFO = "www.google.com/search?q=coronavirus+cases";
    @FXML
    private Text cases, deaths, recovered;
    @FXML
    private FontIcon SEARCH;
    @FXML
    public void refreshData() throws IOException {
        String globalData = DataProvider.getGlobalData();
        globalData = DataProvider.dataWithFilteredChars(globalData);
        List<String> formattedData = DataProvider.defineAttributes(globalData);
        cases.setText(formattedData.get(0));
        deaths.setText(formattedData.get(1));
        recovered.setText(formattedData.get(2));
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
        Stage stage = (Stage) SEARCH.getScene().getWindow();
        stage.close();
    }
    public void quit() {
        System.exit(0);
    }
}
