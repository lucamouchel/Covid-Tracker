import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CountryDataFilter {

    public  static  void openCountryDataPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("CountryData.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 800);
        Stage stage = new Stage();
        stage.setTitle("Covid data per country");
        stage.setScene(scene);
        stage.show();
    }
}
