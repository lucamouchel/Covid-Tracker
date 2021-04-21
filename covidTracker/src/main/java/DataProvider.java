import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataProvider {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://coronavirus-19-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final CovidAPI covidAPI = retrofit.create(CovidAPI.class);

    public static List<String> globalDataList = new ArrayList<>();
    public static List<String> countryDataList = new ArrayList<>();

    public static String getGlobalData() throws IOException {
        String globalData = Objects.requireNonNull(
                covidAPI.getGlobalData().execute().body()).toString();
        globalDataList.add(globalData);
        return globalData;
    }

    public static String getCountryData(String countryName) throws IOException {
        String countryData = Objects.requireNonNull(
                covidAPI.getCountryData(countryName).execute().body()).toString();
        countryDataList.add(countryData);
        return countryData;
    }

    /**
     * Removing unnecessary characters so we can treat the data with
     * only letters and integers.
     *
     * @param dataToFilter string to filter
     * @return filtered string
     */
    public static String dataWithFilteredChars(String dataToFilter) {
        StringBuilder filteredGlobalData = new StringBuilder();
        dataToFilter.chars()
                .filter(c -> Character.isDigit(c) || Character.isLetter(c))
                .mapToObj(c -> (char) c)
                .forEach(filteredGlobalData::append);
        return filteredGlobalData.toString();
    }
    /**
     * Defines the attributes cases, deaths and recovered.
     *
     * @param dataToDefine string to extract attributes from
     */
    public static List<String> defineAttributes(String dataToDefine) {
        for (char c : dataToDefine.toCharArray()) {
            if (Character.isLetter(c)) {
                dataToDefine = dataToDefine.replace(c, ' ');
            }
        }
        //mapping the whole string into a list of integers
        List<Integer> data = Arrays.stream(dataToDefine.split(" "))
                .filter(str -> !str.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        //using the ints from the previous list and formatting it
        List<String> formattedData = new ArrayList<>();
        data.forEach(i -> formattedData.add(String.format("%,d", i)));
        return formattedData;
    }
}