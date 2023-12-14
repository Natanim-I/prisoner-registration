package sample;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginForPrisoner extends Application{

    Stage LoginStage = new Stage();
    private Label lblUserName, lblPassword;
    private PasswordField passField;
    private TextField UNameTxf;
    private Text wrongText;
    private Button btnForgotPass, btnLogin, btnGoBack;

    public void start(Stage primaryStage){

        Pane Pane = new Pane();

        Image WelcomeImage = new Image("sample/Images/images.jpeg");
        ImageView WelcomeIV = new ImageView(WelcomeImage);

        WelcomeIV.fitWidthProperty().bind(Pane.widthProperty());
        WelcomeIV.fitHeightProperty().bind(Pane.heightProperty());

        wrongText = new Text("Wrong UserName or Password, PleaseTry Again");
        wrongText.setLayoutX(300);
        wrongText.setLayoutY(100);
        wrongText.setFill(Color.RED);
        wrongText.setVisible(false);
        wrongText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR,14));
        lblUserName = new Label("UserName");
        lblUserName.setLayoutX(250);
        lblUserName.setLayoutY(130);
        lblUserName.setTextFill(Color.WHEAT);
        lblUserName.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 18));
        lblPassword = new Label("Password");
        lblPassword.setLayoutX(250);
        lblPassword.setLayoutY(180);
        lblPassword.setTextFill(Color.WHEAT);
        lblPassword.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 18));
        UNameTxf = new TextField();
        UNameTxf.setPrefColumnCount(20);
        UNameTxf.setLayoutX(360);
        UNameTxf.setLayoutY(130);
        UNameTxf.setOnAction(event -> {
            UNameTxf.getText();
            passField.requestFocus();
        });
        passField = new PasswordField();
        passField.setPrefColumnCount(20);
        passField.setLayoutX(360);
        passField.setLayoutY(180);
        passField.setOnAction(event -> {
            passField.getText();
            btnLogin.requestFocus();
        });
        btnLogin = new Button("Login");
        btnLogin.setPrefSize(100,15);
        btnLogin.setLayoutX(495);
        btnLogin.setLayoutY(230);
        btnLogin.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btnLogin.setOnAction(event -> {
            Login();
        });
        btnLogin.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                Login();
            }
        });
        btnForgotPass = new Button("Forgot Password");
        btnForgotPass.setPrefSize(100, 15);
        btnForgotPass.setLayoutX(495);
        btnForgotPass.setLayoutY(270);
        btnForgotPass.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 10));
        btnGoBack = new Button("Go Back");
        btnGoBack.setPrefSize(100,15);
        btnGoBack.setLayoutX(600);
        btnGoBack.setLayoutY(500);
        btnGoBack.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btnGoBack.setOnAction(event -> {
            PrisonHome PH = new PrisonHome();
            PH.start(PH.HomeStage);
            LoginStage.hide();
        });

        Pane.getChildren().addAll(WelcomeIV, wrongText, lblUserName, lblPassword, UNameTxf, passField, btnLogin, btnForgotPass, btnGoBack);

        Scene AdminScene = new Scene(Pane,800,600);

        LoginStage.setScene(AdminScene);
        LoginStage.setTitle("Login");
        LoginStage.setResizable(false);
        LoginStage.show();
    }
    Connection conn = null;
    PreparedStatement ppst = null;
    ResultSet rsSet = null;

    public void Login(){
        try {
            conn = DatabaseConnection.connectDb();
        }catch (Exception e){
            e.printStackTrace();
        }
        String User = UNameTxf.getText();
        String Password = passField.getText();

        String sqlLogin = "SELECT * FROM Admin WHERE UserName = ? AND PasswordP = ?";
        try {
            ppst = conn.prepareStatement(sqlLogin);
            ppst.setString(1,User);
            ppst.setString(2,Password);
            rsSet = ppst.executeQuery();
            if(!rsSet.next()){
                wrongText.setVisible(true);
            }
            else {
                Prisoner PPP = new Prisoner();
                PPP.start(PPP.PrisonerStage);
                LoginStage.hide();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
