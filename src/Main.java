import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application {
    static Media music = new Media(Main.class.getResource("Feel_it_still.mp3").toExternalForm());
    static MediaPlayer player = new MediaPlayer(music);

    @Override
    public void start(Stage primaryStage) throws Exception {
        player.setAutoPlay(true);
        player.play();
        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        primaryStage.setTitle("Space Trader");
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add("app.css");
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


