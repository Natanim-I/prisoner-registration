package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PrisonHome extends Application {

    private Text WelcomeText;
    private Button btnAdmin, btnPrisoner, btnVisitation, btnExit;
    private Pane Welcome;
    Stage HomeStage = new Stage();
    private Administration AdminClass = new Administration();
    private LoginForPrisoner LoginClassPrisonner = new LoginForPrisoner();
    private Visitation VisitationClass = new Visitation();

    @Override
    public void start(Stage primaryStage) {

        Welcome = new Pane();

        Image WelcomeImage = new Image("sample/Images/images.jpeg");
        ImageView WelcomeIV = new ImageView(WelcomeImage);

        WelcomeIV.fitWidthProperty().bind(Welcome.widthProperty());
        WelcomeIV.fitHeightProperty().bind(Welcome.heightProperty());

        WelcomeText = new Text("Prison Management System");
        WelcomeText.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 60));
        WelcomeText.setTextAlignment(TextAlignment.JUSTIFY);
        WelcomeText.setFill(Color.WHEAT);
        WelcomeText.xProperty().bind(Welcome.widthProperty().divide(10));
        WelcomeText.yProperty().bind(Welcome.heightProperty().divide(8));
        btnExit = new Button("Exit");
        btnExit.setLayoutX(730);
        btnExit.setLayoutY(550);
        btnExit.setBackground(Background.EMPTY);
        btnExit.setTextFill(Color.WHEAT);
        btnExit.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,16));
        btnExit.setOnAction(event -> {
            HomeStage.close();
            primaryStage.close();
        });
        Welcome.getChildren().addAll(WelcomeIV, WelcomeText, btnExit, getHbox());

        btnAdmin.setOnAction(event -> {
            AdminClass.start(AdminClass.AdminStage);
            HomeStage.hide();
        });
        btnPrisoner.setOnAction(event -> {
            LoginClassPrisonner.start(LoginClassPrisonner.LoginStage);
            HomeStage.hide();
        });
        btnVisitation.setOnAction(event ->  {
            VisitationClass.start(VisitationClass.VisitStage);
            HomeStage.hide();
        });
        Scene WelcomeScene = new Scene(Welcome, 800, 600);

        HomeStage.setTitle("Prison Management System");
        HomeStage.setResizable(false);
        HomeStage.setScene(WelcomeScene);
        HomeStage.show();

    }
    private HBox getHbox(){
        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.setLayoutX(100);
        hBox.setLayoutY(280);

        ImageView btnAdminIV = new ImageView("sample/Images/images11.jpeg");
        btnAdminIV.setFitWidth(150);
        btnAdminIV.setFitHeight(100);
        ImageView btnPrisonerIV = new ImageView("sample/Images/images111.jpeg");
        btnPrisonerIV.setFitWidth(150);
        btnPrisonerIV.setFitHeight(100);
        ImageView btnVisitationIV = new ImageView("sample/Images/images1111.jpg");
        btnVisitationIV.setFitWidth(150);
        btnVisitationIV.setFitHeight(100);
        btnAdmin = new Button("Administration", btnAdminIV);
        btnAdmin.setPrefSize(200,150);
        btnAdmin.setFont(Font.font("Courier", FontWeight.EXTRA_BOLD, FontPosture.REGULAR,20));
        btnAdmin.setBackground(Background.EMPTY);
        btnAdmin.setTextFill(Color.WHEAT);
        btnAdmin.setContentDisplay(ContentDisplay.TOP);
        btnPrisoner = new Button("Prisoner", btnPrisonerIV);
        btnPrisoner.setPrefSize(200,150);
        btnPrisoner.setFont(Font.font("Courier", FontWeight.EXTRA_BOLD, FontPosture.REGULAR,20));
        btnPrisoner.setBackground(Background.EMPTY);
        btnPrisoner.setTextFill(Color.WHEAT);
        btnPrisoner.setContentDisplay(ContentDisplay.TOP);
        btnVisitation = new Button("Visitation", btnVisitationIV);
        btnVisitation.setPrefSize(200,150);
        btnVisitation.setFont(Font.font("Courier", FontWeight.EXTRA_BOLD, FontPosture.REGULAR,20));
        btnVisitation.setBackground(Background.EMPTY);
        btnVisitation.setTextFill(Color.WHEAT);
        btnVisitation.setContentDisplay(ContentDisplay.TOP);

        hBox.getChildren().addAll(btnAdmin, btnPrisoner, btnVisitation);
        return hBox;
    }
}
