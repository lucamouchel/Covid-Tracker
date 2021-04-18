import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataProvider {
    private final static String GLOBAL_SEARCH = "https://www.worldometers.info/coronavirus/";
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
}