import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeViewController {

    /**
     * this method is used to switch to the next page.
     *
     * @param event fired when button is pressed
     * @throws IOException some possible exception
     */
    public void welcomeBtnPressed(ActionEvent event) throws IOException {
        Parent configParent = FXMLLoader.load(getClass().getResource("ConfigurationView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
}
