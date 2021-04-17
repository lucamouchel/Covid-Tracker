import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public final class CloseStage {
    @FXML
    public static void closeStage(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
