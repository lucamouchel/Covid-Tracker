import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TreeTableColumn;

public record Country(String name, String cases, String todaysCases,
                      String deaths, String todaysDeaths, String recovered,
                      String activeCases, String criticalCases, String casesPerMillion,
                      String deathsPerMillion, String totalTests,
                      String testsPerMillion) {

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

    public String getName() {
        return name;
    }

    public String getCases() {
        return String.format("%,d", Integer.parseInt(cases));
    }

    public String getTodaysCases() {
        return String.format("%,d", Integer.parseInt(todaysCases));
    }

    public String getDeaths() {
        return String.format("%,d", Integer.parseInt(deaths));
    }

    public String getTodaysDeaths() {
        return String.format("%,d", Integer.parseInt(todaysDeaths));
    }

    public String getRecovered() {
        return String.format("%,d", Integer.parseInt(recovered));
    }

    public String getActiveCases() {
        return String.format("%,d", Integer.parseInt(activeCases));
    }

    public String getCriticalCases() {
        return String.format("%,d", Integer.parseInt(criticalCases));
    }

    public String getCasesPerMillion() {
        return String.format("%,d", Integer.parseInt(casesPerMillion));
    }

    public String getDeathsPerMillion() {
        return String.format("%,d", Integer.parseInt(deathsPerMillion));
    }

    public String getTotalTests() {
        return String.format("%,d", Integer.parseInt(totalTests));
    }

    public String getTestsPerMillion() {
        return String.format("%,d", Integer.parseInt(testsPerMillion));
    }

    public static void setValueToColumn(TreeTableColumn<Country, String> column, Paramater param) {
        switch (param) {
            case COUNTRY -> column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Country, String> p)
                    -> new ReadOnlyStringWrapper(p.getValue().getValue().getName()));
            case CASES -> column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Country, String> p)
                    -> new ReadOnlyStringWrapper(p.getValue().getValue().getCases()));
            case TODAYS_CASES -> column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Country, String> p)
                    -> new ReadOnlyStringWrapper(p.getValue().getValue().getTodaysCases()));
            case DEATHS -> column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Country, String> p)
                    -> new ReadOnlyStringWrapper(p.getValue().getValue().getDeaths()));
            case TODAYS_DEATHS -> column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Country, String> p)
                    -> new ReadOnlyStringWrapper(p.getValue().getValue().getTodaysDeaths()));
            case RECOVERED -> column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Country, String> p)
                    -> new ReadOnlyStringWrapper(p.getValue().getValue().getRecovered()));
            case ACTIVE_CASES -> column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Country, String> p)
                    -> new ReadOnlyStringWrapper(p.getValue().getValue().getActiveCases()));
            case CRITICAL_CASES -> column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Country, String> p)
                    -> new ReadOnlyStringWrapper(p.getValue().getValue().getCriticalCases()));
            case CASES_PER_MILLION -> column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Country, String> p)
                    -> new ReadOnlyStringWrapper(p.getValue().getValue().getCasesPerMillion()));
            case DEATHS_PER_MILLION -> column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Country, String> p)
                    -> new ReadOnlyStringWrapper(p.getValue().getValue().getDeathsPerMillion()));
            case TOTAL_TESTS -> column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Country, String> p)
                    -> new ReadOnlyStringWrapper(p.getValue().getValue().getTotalTests()));
            case TESTS_PER_MILLION -> column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Country, String> p)
                    -> new ReadOnlyStringWrapper(p.getValue().getValue().getTestsPerMillion()));
        }
    }
}
