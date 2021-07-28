import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TreeTableColumn;
import org.json.JSONObject;

public class Country {
    private final JSONObject jsonObject;

    public Country(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getName() {
        return jsonObject.getString("country");
    }

    public String getCases() {
        String keyword = "cases";
        return jsonObject.isNull(keyword) ? "N/A" : String.format("%,d", jsonObject.getInt(keyword));
    }

    public String getTodaysCases() {
        String keyword = "todayCases";
        return jsonObject.isNull(keyword) ? "N/A" : String.format("%,d", jsonObject.getInt(keyword));
    }

    public String getDeaths() {
        String keyword = "deaths";
        return jsonObject.isNull(keyword) ? "N/A" : String.format("%,d", jsonObject.getInt(keyword));
    }

    public String getTodaysDeaths() {
        String keyword = "todayDeaths";
        return jsonObject.isNull(keyword) ? "N/A" : String.format("%,d", jsonObject.getInt(keyword));
    }

    public String getRecovered() {
        String keyword = "recovered";
        return jsonObject.isNull(keyword) ? "N/A" : String.format("%,d", jsonObject.getInt(keyword));
    }

    public String getActiveCases() {
        String keyword = "active";
        return jsonObject.isNull(keyword) ? "N/A" : String.format("%,d", jsonObject.getInt(keyword));
    }

    public String getCriticalCases() {
        String keyword = "critical";
        return jsonObject.isNull(keyword) ? "N/A" : String.format("%,d", jsonObject.getInt(keyword));
    }

    public String getCasesPerMillion() {
        String keyword = "casesPerOneMillion";
        return jsonObject.isNull(keyword) ? "N/A" : String.format("%,d", jsonObject.getInt(keyword));
    }

    public String getDeathsPerMillion() {
        String keyword = "deathsPerOneMillion";
        return jsonObject.isNull(keyword) ? "N/A" : String.format("%,d", jsonObject.getInt(keyword));
    }

    public String getTotalTests() {
        String keyword = "totalTests";
        return jsonObject.isNull(keyword) ? "N/A" : String.format("%,d", jsonObject.getInt(keyword));
    }

    public String getTestsPerMillion() {
        String keyword = "testsPerOneMillion";
        return jsonObject.isNull(keyword) ? "N/A" : String.format("%,d", jsonObject.getInt(keyword));
    }

    enum Paramater {
        COUNTRY,
        CASES,
        TODAYS_CASES,
        DEATHS,
        TODAYS_DEATHS,
        RECOVERED,
        ACTIVE_CASES,
        CRITICAL_CASES,
        CASES_PER_MILLION,
        DEATHS_PER_MILLION,
        TOTAL_TESTS,
        TESTS_PER_MILLION;
    }
}
