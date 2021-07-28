import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AllCountriesHandler {
    TreeTableView<Country> treeTableView;
    List<TreeTableColumn<Country, String>> columns = new ArrayList<>();

    public void openCountryDataPage() throws IOException {
        final Scene scene = new Scene(new Group(), 1100, 700);
        createTableView();
        Group sceneRoot = (Group) scene.getRoot();
        sceneRoot.getChildren().add(treeTableView);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void createTableView() throws IOException {
        DataProvider.filterDataOnGlobalScale(DataProvider.countryDataOnGlobalScale());
        final TreeItem<Country> root = new TreeItem<>(DataProvider.countryList.get(0));
        DataProvider.countryList.remove(0);
        root.setExpanded(true);
        DataProvider.countryList.forEach((country) -> root.getChildren().add(new TreeItem<>(country)));
        createAndSetValueToColumns();
        this.treeTableView = new TreeTableView<>(root);
        treeTableView.setPrefHeight(700);
        treeTableView.setPrefWidth(1100);
        treeTableView.getColumns().setAll(columns.get(0), columns.get(1), columns.get(2), columns.get(3),
                columns.get(4), columns.get(5), columns.get(6), columns.get(7),
                columns.get(8), columns.get(9), columns.get(10), columns.get(11));
    }


    private void createAndSetValueToColumns() {
        TreeTableColumn<Country, String> country =
                createColumn("Country", Country.Paramater.COUNTRY);
        TreeTableColumn<Country, String> cases =
                createColumn("Cases", Country.Paramater.CASES);
        TreeTableColumn<Country, String> todaysCases =
                createColumn("Today's cases", Country.Paramater.TODAYS_CASES);
        TreeTableColumn<Country, String> deaths =
                createColumn("Deaths", Country.Paramater.DEATHS);
        TreeTableColumn<Country, String> todaysDeaths =
                createColumn("Today's deaths", Country.Paramater.TODAYS_DEATHS);
        TreeTableColumn<Country, String> recovered =
                createColumn("Recovered", Country.Paramater.RECOVERED);
        TreeTableColumn<Country, String> activeCases =
                createColumn("Active Cases", Country.Paramater.ACTIVE_CASES);
        TreeTableColumn<Country, String> criticalCases =
                createColumn("Critical Cases", Country.Paramater.CRITICAL_CASES);
        TreeTableColumn<Country, String> casesPerMillion =
                createColumn("Cases / million", Country.Paramater.CASES_PER_MILLION);
        TreeTableColumn<Country, String> deathsPerMillion =
                createColumn("Deaths / million", Country.Paramater.DEATHS_PER_MILLION);
        TreeTableColumn<Country, String> totalTests =
                createColumn("Total tests", Country.Paramater.TOTAL_TESTS);
        TreeTableColumn<Country, String> testsPerMillion =
                createColumn("Tests / million", Country.Paramater.TESTS_PER_MILLION);

        columns.addAll(List.of(country, cases, todaysCases,
                deaths, todaysDeaths, recovered, activeCases, criticalCases,
                casesPerMillion, deathsPerMillion, totalTests, testsPerMillion));
    }

    private TreeTableColumn<Country, String> createColumn(String name, Country.Paramater param) {
        TreeTableColumn<Country, String> column = new TreeTableColumn<>(name);
        column.setPrefWidth(120);
        setValueToColumn(column, param);
        return column;
    }

    private void setValueToColumn(TreeTableColumn<Country, String> column, Country.Paramater param) {
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
                    -> new ReadOnlyStringWrapper(p.getValue().getValue().getRecovered().toString()));
            case ACTIVE_CASES -> column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Country, String> p)
                    -> new ReadOnlyStringWrapper(p.getValue().getValue().getActiveCases().toString()));
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
