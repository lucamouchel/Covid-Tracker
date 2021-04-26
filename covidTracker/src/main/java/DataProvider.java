import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class DataProvider {
    public static List<Country> countryList = new ArrayList<>();
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://coronavirus-19-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final CovidAPI covidAPI = retrofit.create(CovidAPI.class);

    public static String getGlobalData() throws IOException {
        return Objects.requireNonNull(covidAPI.getGlobalData().execute().body()).toString();
    }

    public static String getCountryData(String countryName) throws IOException {
        return Objects.requireNonNull(
                covidAPI.getCountryData(countryName).execute().body()).toString();
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

    /**
     * Defines the attributes cases, deaths and recovered.
     *
     * @param dataToDefine string to filter and extract attributes from
     */
    public static List<String> defineAttributes(String dataToDefine) throws IOException {
        String filtered = dataToDefine.replaceAll("[^0-9]", " ");
        //mapping the whole string into a list of strings
        return Arrays.stream(filtered.split(" "))
                .filter(str -> !str.isEmpty())
                .mapToInt(Integer::parseInt)
                .mapToObj(i -> String.format("%,d", i))
                .collect(Collectors.toList());
    }

    public static void filterDataOnGlobalScale(String list) throws IOException {
        String a = list.replaceAll("[;\",:{]", " ").replace("[", "");
        List<String> individualCountries = Arrays.stream(a.split("}"))
                .filter(str -> !str.isEmpty())
                .collect(Collectors.toList());
        //now we are working with strings such as : "country   USA   cases  32735704
        // todayCases  0  deaths  585075  todayDeaths  0  recovered  25296047  active  6854582
        // critical  9832  casesPerOneMillion  98431  deathsPerOneMillion  1759
        // totalTests  437068081  testsPerOneMillion  1314196"
        //so the indices we are interested in are always fixed, ie country is at index 1 etc
        List<List<String>> separatedElements = new ArrayList<>();
        for (String individualCountry : individualCountries) {
            separatedElements.add((Arrays.stream(individualCountry.split(" "))
                    .filter(s -> !s.isEmpty()))
                    .collect(Collectors.toList()));
        }
        //now we have lists in lists, separating each useful element for each country,
        //allowing to define a list for all the countries, cases etc ...
        //index 1 -country, index 3 - cases, index 5 -today cases
        //index 7 -deaths, index 9 today deaths, index 11 - recovered
        //index 13 - active, index 15 - critical, index 17 casespermillion
        //index 19 deaths per million, index 21 - total tests, index 23, total tests per million
        for (int i = 0; i < separatedElements.size() - 1; i++) {
            countryList.add(new Country(separatedElements.get(i).get(1),separatedElements.get(i).get(3),
                    separatedElements.get(i).get(5),separatedElements.get(i).get(7),
                    separatedElements.get(i).get(9), separatedElements.get(i).get(11),
                    separatedElements.get(i).get(13), separatedElements.get(i).get(15), separatedElements.get(i).get(17),
                    separatedElements.get(i).get(19), separatedElements.get(i).get(21), separatedElements.get(i).get(23)));
        }
    }
}