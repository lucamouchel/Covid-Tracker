import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GlobalDataFilter {
    @FXML
    private Button cases;
    @FXML
    private Button deaths;
    @FXML
    private Button recovered;

    private int globalCases;
    private int globalDeaths;
    private int globalRecovered;

    /**
     * Types of Strings we will be working with for GLOBAL DATA:
     * {"cases":140476554,"deaths":3010657,"recovered":119302906}
     */
    public void globalDataHandler() throws IOException {
        String globalData = DataProvider.getGlobalData();
        globalData = filteredGlobalData(globalData);

    }

    /**
     * Returns a filtered string that contains only letters or integers
     *
     * @param globalData string to filter
     * @return filtered string
     */
    public String filteredGlobalData(String globalData) {
        StringBuilder filteredGlobalData = new StringBuilder();
        globalData.chars()
                .filter(c -> Character.isDigit(c) || Character.isLetter(c))
                .mapToObj(c -> (char) c)
                .forEach(filteredGlobalData::append);
        return filteredGlobalData.toString();
    }

    /**
     * Returns a list of the total Global:
     * cases, deaths and recovered in real time.
     *
     * @param globalData String to filter into a List of ints
     * @return List of ints that represent values mentioned above
     */
    public List<Integer> getIntegerData(String globalData) {
        for (char c : globalData.toCharArray()) {
            if (Character.isLetter(c)) {
                globalData = globalData.replace(c, ' ');
            }
        }
        List<String> data = Arrays.stream(globalData.split(" "))
                .filter(str -> !str.isEmpty())
                .collect(Collectors.toList());

        this.globalCases = Integer.parseInt(data.get(0));
        this.globalDeaths = Integer.parseInt(data.get(1));
        this.globalRecovered = Integer.parseInt(data.get(2));

        return List.of(this.globalCases, this.globalDeaths, this.globalRecovered);
    }
}
