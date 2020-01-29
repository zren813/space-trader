import com.sun.xml.internal.txw2.TxwException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


/**
 * This is the game Space Trader.
 *
 * @author Qifan Yang, Jingyi Wan, Kehan Wang, Ziyao Ren
 * @version 1.0
 */
public class Welcome extends Application {

    private Stage window;
    private Scene welcomeScrn, configerationScrn;

    private BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(51, 85,  139), CornerRadii.EMPTY, Insets.EMPTY);
    private Background background = new Background(backgroundFill);
    private String buttonSetting = "-fx-background-color:\n" +
            "linear-gradient(#f0ff35, #a9ff00),\n" +
            "radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);\n" +
            "-fx-background-radius: 6, 5;\n" +
            "-fx-background-insets: 0, 1;\n" +
            "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n" +
            " -fx-text-fill: #395306;";
    private String menuBtn = "-fx-border-color: transparent;\n" +
            "    -fx-border-width: 0;\n" +
            "    -fx-background-radius: 0;\n" +
            "    -fx-background-color: transparent;\n" +
            "    -fx-font-family:\"Segoe UI\", Helvetica, Arial, sans-serif;\n" +
            "    -fx-font-size: 1em; /* 12 */\n" +
            "    -fx-text-fill: #828282;";
    private String nameOfthePlayer;

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        welcomePage();
        stage.setScene(welcomePage());
        stage.setResizable(false);
        stage.setTitle("Space Trader");
        stage.show();
        stage.setMinWidth(1500);
        stage.setMinHeight(800);
    }

    private Scene welcomePage() {
        //Welcome Scene
        Text title = new Text("Space Trader");
        Button startBtn = new Button("START GAME");
        startBtn.setFont(Font.font(20));
        startBtn.setOnAction(e -> window.setScene(confScrn()));
//        welcomeScrn.getStylesheets().add(getClass().getResource("/fontstyle.css").toExternalForm());;
        VBox WelcomeVBox = new VBox(100);
        WelcomeVBox.getChildren().add(title);
        WelcomeVBox.getChildren().add(startBtn);
        welcomeScrn = new Scene(WelcomeVBox);

        WelcomeVBox.setAlignment(Pos.CENTER);
        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 100));
        title.setFill(Color.WHITE);

        WelcomeVBox.setBackground(background);

        startBtn.setPrefSize(800, 50);
        startBtn.setStyle(buttonSetting);
        return welcomeScrn;
    }

    private Scene confScrn() {

        //Configuration Scene
        GridPane configPane = new GridPane();
            //Configuration Vbox setting
        VBox confVbox = new VBox(100);
        confVbox.setBackground(background);
        confVbox.setAlignment(Pos.CENTER);
        confVbox.setFillWidth(false);
//        confVbox.isFillWidth();

            //Text lable "Enter name"
        Label confEnter = new Label("Enter name:");
        confEnter.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 28));
        confEnter.setTextFill(Color.WHITE);
            //TextField and the hbox
        TextField enterName = new TextField();
        enterName.setPromptText("eg: Amy Smith");
        HBox nameAndText = new HBox();
        enterName.setPrefColumnCount(20);
        enterName.setPrefHeight(35);
        nameAndText.setSpacing(20);
        nameAndText.setAlignment(Pos.CENTER);
        nameAndText.getChildren().add(confEnter);
        nameAndText.getChildren().add(enterName);
            //buttom
        Button submitBtn = new Button("SUBMIT");

        EventHandler<ActionEvent> getNameEvent = i -> {
            if (!(enterName.getText().isEmpty()) && !(enterName.getText().isEmpty())) {
                nameOfthePlayer = enterName.getText();
            }
        };
        submitBtn.setOnAction(getNameEvent);

        submitBtn.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 20));
        nameAndText.getChildren().add(submitBtn);
        submitBtn.setStyle(buttonSetting);
            //deficality Create TitledPane.
        TitledPane titledPane = new TitledPane();
        titledPane.setText("DIFFICULTY");
        titledPane.setAlignment(Pos.CENTER);
        titledPane.setPrefHeight(50);
        titledPane.setMinWidth(30);
        titledPane.setStyle(buttonSetting);

            // Content for TitledPane
        VBox content = new VBox();
        Button easyBtn = new Button("EASY");
        Button hardBtn = new Button("HARD");
        Button legendaryBtn = new Button("LEGENDARY");
        content.getChildren().addAll(easyBtn, hardBtn, legendaryBtn);
        easyBtn.setTextFill(Color.BLACK);

        easyBtn.setStyle(menuBtn);
        hardBtn.setStyle(menuBtn);
        legendaryBtn.setStyle(menuBtn);
        content.setAlignment(Pos.CENTER);
        content.setStyle(buttonSetting);
        easyBtn.setOnAction(e -> titledPane.setText("EASY"));
        hardBtn.setOnAction(e -> titledPane.setText("HARD"));
        legendaryBtn.setOnAction(e -> titledPane.setText("LEGENDARY"));


        titledPane.setContent(content);
                // Set Expaneded.
        titledPane.setExpanded(false);

        //switch sence
        Button goBack = new Button("GO BACK");
        goBack.setFont(Font.font(20));
        goBack.setOnAction(e -> window.setScene(welcomePage()));
        goBack.setStyle(buttonSetting);

            //setting things into vBox
        confVbox.getChildren().add(nameAndText);
        confVbox.getChildren().add(titledPane);
        configerationScrn = new Scene(confVbox);
        confVbox.getChildren().add(goBack);


        return configerationScrn;
    }
}
