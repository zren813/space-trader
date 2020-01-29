import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
        Text nameText = new Text("Enter your name: ");
        nameText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        nameText.setFill(Color.WHITE);
        TextField nameField = new TextField();
        HBox nameHB = new HBox();
        nameHB.getChildren().addAll(nameText, nameField);

        Text difficultyText = new Text("Choose difficulty: ");
        difficultyText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        difficultyText.setFill(Color.WHITE);
        Button difficulty0 = new Button("0");
        Button difficulty1 = new Button("1");
        Button difficulty2 = new Button("2");
        Button difficulty3 = new Button("3");
        HBox difficultyHB = new HBox();
        difficultyHB.getChildren().addAll(difficultyText, difficulty0, difficulty1, difficulty2, difficulty3);

        Text skillText = new Text("Allocate your skills: ");
        skillText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        skillText.setFill(Color.WHITE);
        Text pilotText = new Text("Pilot");
        pilotText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        pilotText.setFill(Color.WHITE);
        ChoiceBox pilotCB = new ChoiceBox();
        pilotCB.getItems().add("1");
        pilotCB.getItems().add("2");
        pilotCB.getItems().add("3");
        Text fighterText = new Text("Fighter");
        fighterText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        fighterText.setFill(Color.WHITE);
        ChoiceBox fighterCB = new ChoiceBox();
        fighterCB.getItems().add("1");
        fighterCB.getItems().add("2");
        fighterCB.getItems().add("3");
        Text merchantText = new Text("Merchant");
        merchantText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        merchantText.setFill(Color.WHITE);
        ChoiceBox merchantCB = new ChoiceBox();
        merchantCB.getItems().add("1");
        merchantCB.getItems().add("2");
        merchantCB.getItems().add("3");
        Text engineerText = new Text("Engineer");
        engineerText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        engineerText.setFill(Color.WHITE);
        ChoiceBox engineerCB = new ChoiceBox();
        engineerCB.getItems().add("1");
        engineerCB.getItems().add("2");
        engineerCB.getItems().add("3");
        GridPane skillGP = new GridPane();
        skillGP.add(skillText, 0, 0, 3, 1);
        skillGP.add(pilotText, 0, 1, 2, 1);
        skillGP.add(pilotCB, 2, 1, 1, 1);
        skillGP.add(fighterText, 0, 2, 2, 1);
        skillGP.add(fighterCB, 2, 2, 1, 1);
        skillGP.add(merchantText, 0, 3, 2, 1);
        skillGP.add(merchantCB, 2, 3, 1, 1);
        skillGP.add(engineerText, 0, 4, 2, 1);
        skillGP.add(engineerCB, 2, 4, 1, 1);

        Button configContinueBtn = new Button("Continue");

        VBox configVB = new VBox(60);
        configVB.getChildren().addAll(nameHB, difficultyHB, skillGP, configContinueBtn);
        configVB.setAlignment(Pos.TOP_CENTER);
        configVB.setBackground(background);
        configerationScrn = new Scene(configVB);



        stage.setScene(welcomeScrn);
        stage.setResizable(false);
        stage.setTitle("Space Trader");
        stage.show();
        stage.setMinWidth(1500);
        stage.setMinHeight(800);
    }
}