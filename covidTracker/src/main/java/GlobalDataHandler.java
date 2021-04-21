import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class GlobalDataHandler {
    @FXML
    private Text cases, deaths, recovered;
    @FXML
    private FontIcon SEARCH;
    //as much as these values are ints,
    //the numbers will be formatted as ###,###,###
    private String globalCases;
    private String globalDeaths;
    private String globalRecovered;
    private final String COVID_NEWS = "www.google.com/search?q=coronavirus+news";
    private final String GENERAL_COVID_INFO = "www.google.com/search?q=coronavirus+cases";

    @FXML
    public void refreshData() throws IOException {
        String globalData = DataProvider.getGlobalData();
        filterData(globalData);
        cases.setText(this.globalCases);
        deaths.setText(this.globalDeaths);
        recovered.setText(this.globalRecovered);
    }

    /**
     * Filters the data into only integers.
     * These represent the number of cases, deaths and recovered.
     *
     * @param globalData String to filter
     */
    public void filterData(String globalData) {
        globalData = DataProvider.dataWithFilteredChars(globalData);
        List<String> formattedData = DataProvider.defineAttributes(globalData);
        //we know the list contains only three elements, respectively cases, deaths and recovered
        this.globalCases = formattedData.get(0);
        this.globalDeaths = formattedData.get(1);
        this.globalRecovered = formattedData.get(2);
    }


    private void openURL(String URL) {
        try {
            Desktop.getDesktop().browse(new URI(URL));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    public void openNews() {
        openURL(COVID_NEWS);
    }

    public void openGeneralInfo() {
        openURL(GENERAL_COVID_INFO);
    }

    public void openCountryPage() throws IOException {
        Stage stage = (Stage) SEARCH.getScene().getWindow();
        stage.close();
        CountryDataHandler.openCountryDataPage();
    }
}
