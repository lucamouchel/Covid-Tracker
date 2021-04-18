import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
    private Button cases, deaths, recovered;
    //as much as these values are ints,
    //the numbers will be formatted as ###,###,###
    private String globalCases;
    private String globalDeaths;
    private String globalRecovered;
    private static final String LINK = "https://www.google.com/search?q=coronavirus+global+";

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
     * we will work with strings such as these to treat global data.
     *
     * @param globalData String to filter into ints
     * @Link {"cases":140598859,"deaths":3014240,"recovered":119432902}
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
    public String dataWithFilteredChars(String globalData) {
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

    @FXML
    JFXDrawer drawer = new JFXDrawer();

    public void closeStageAndOpenSelectionBox() {
        Stage stage = (Stage) drawer.getScene().getWindow();
        stage.close();
    }

    private void openURL(String URL) {
        try {
            Desktop.getDesktop().browse(new URI(URL));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    public void openCasesInfo() {
        openURL(LINK + "cases");
    }

    public void openDeathsInfo() {
        openURL(LINK + "deaths");
    }

    public void openRecoveredInfo() {
        openURL(LINK + "recovered");
    }

    @FXML
    void quit() {
        System.exit(0);
    }


}
