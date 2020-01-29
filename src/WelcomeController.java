import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {
    public void welcomeBtnPressed(ActionEvent event) throws IOException {
        Parent configParent = FXMLLoader.load(getClass().getResource("Configuration.fxml"));
        Scene configScene = new Scene(configParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
}
