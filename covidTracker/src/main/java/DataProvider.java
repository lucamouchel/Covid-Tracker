import org.json.JSONArray;
import org.json.JSONObject;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataProvider {
  private static final Retrofit retrofit =
      new Retrofit.Builder()
          .baseUrl("https://coronavirus-19-api.herokuapp.com/")
          .addConverterFactory(GsonConverterFactory.create())
          .build();
  private static final CovidAPI covidAPI = retrofit.create(CovidAPI.class);
  public static List<Country> countryList = new ArrayList<>();
  private static JSONObject globalData;

  static {
    try {
      globalData =
          new JSONObject(
              Objects.requireNonNull(covidAPI.getGlobalData().execute().body()).toString());
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public DataProvider() throws IOException {}

  public static String getGlobalCases() throws IOException {
    return String.format("%,d", globalData.getInt("cases"));
  }

  public static String getGlobalDeaths() throws IOException {
    return String.format("%,d", globalData.getInt("deaths"));
  }

  public static String getGlobalRecovered() throws IOException {
    return String.format("%,d", globalData.getInt("recovered"));
  }

  public static String getCountryData(String countryName) throws IOException {
    return Objects.requireNonNull(covidAPI.getCountryData(countryName).execute().body()).toString();
  }

  public static String countryDataOnGlobalScale() throws IOException {
    URL url = new URL("https://coronavirus-19-api.herokuapp.com/countries");
    Scanner scan = new Scanner(url.openStream());
    StringBuilder sb = new StringBuilder();
    while (scan.hasNext()) {
      sb.append(scan.next());
    }
    return sb.toString();
  }


  public static void filterDataOnGlobalScale(String list) throws IOException {
    JSONArray arr = new JSONArray(list);
    IntStream.range(0, arr.length()).forEach(i -> countryList.add(new Country(arr.getJSONObject(i))));
  }
}
