import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GlobalDataFilter {
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
        globalData = dataWithFilteredChars(globalData);
        defineAttributes(globalData);
    }

    /**
     * Removing unnecessary characters so we can treat the data with
     * only letters and integers.
     *
     * @param globalData string to filter
     * @return filtered string
     */
    public static String dataWithFilteredChars(String globalData) {
        StringBuilder filteredGlobalData = new StringBuilder();
        globalData.chars()
                .filter(c -> Character.isDigit(c) || Character.isLetter(c))
                .mapToObj(c -> (char) c)
                .forEach(filteredGlobalData::append);
        return filteredGlobalData.toString();
    }

    /**
     * Defines the attributes cases, deaths and recovered.
     *
     * @param globalData string to extract attributes from
     */
    public void defineAttributes(String globalData) {
        for (char c : globalData.toCharArray()) {
            if (Character.isLetter(c)) {
                globalData = globalData.replace(c, ' ');
            }
        }
        //mapping the whole string into a list of integers
        List<Integer> data = Arrays.stream(globalData.split(" "))
                .filter(str -> !str.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        //using the ints from the previous list and formatting it
        List<String> formattedData = new ArrayList<>();
        data.forEach(i -> formattedData.add(String.format("%,d", i)));
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
        CountryDataFilter.openCountryDataPage();
    }
    @FXML
    void quit() {
        System.exit(0);
    }
}
