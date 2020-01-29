import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is the game Space Trader.
 *
 * @author Qifan Yang, Jingyi Wan, Kehan Wang, Ziyao Ren, Chiche Tsai
 * @version 1.0
 */
public class Game extends Application {

    Stage window;
    Scene welcomeScrn, configerationScrn;

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;

        //Welcome Scene
        Text title = new Text("Space Trader");
        Button startBtn = new Button("START GAME");
        startBtn.setOnAction(e -> window.setScene(configerationScrn));

        VBox WelcomeVBox = new VBox(100);
        WelcomeVBox.getChildren().add(title);
        WelcomeVBox.getChildren().add(startBtn);
        welcomeScrn = new Scene(WelcomeVBox);

        WelcomeVBox.setAlignment(Pos.CENTER);
        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 100));
        title.setFill(Color.WHITE);

        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(51, 85,  139), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        WelcomeVBox.setBackground(background);

        startBtn.setPrefSize(800, 50);
        startBtn.setStyle("-fx-background-color:\n" +
                "linear-gradient(#f0ff35, #a9ff00),\n" +
                "radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);\n" +
                "-fx-background-radius: 6, 5;\n" +
                "-fx-background-insets: 0, 1;\n" +
                "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n" +
                " -fx-text-fill: #395306;");

        //Configuration Scene
        Text name = new Text("Enter your name: ");
        TextField nameField = new TextField();
        GridPane configPane = new GridPane();


        configPane.setBackground(background);
        configerationScrn = new Scene(configPane);



        stage.setScene(welcomeScrn);
        stage.setResizable(false);
        stage.setTitle("Space Trader");
        stage.show();
        stage.setMinWidth(1500);
        stage.setMinHeight(800);
    }
}