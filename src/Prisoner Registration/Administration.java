package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Administration extends Application {

    Stage AdminStage = new Stage();

    private Button btnLogin,btnForgotPass,btnGoBack;
    private Label lblUserName,lblPassword;
    private PasswordField passField;
    private TextField UNameTxf;
    private Text wrongText;

    public void start(Stage primaryStage){
        Pane AdminPane = new Pane();

        Image AdminImage = new Image("sample/Images/imagesA.jpeg");
        ImageView AdminIV = new ImageView(AdminImage);

        AdminIV.fitWidthProperty().bind(AdminPane.widthProperty().subtract(200));
        AdminIV.fitHeightProperty().bind(AdminPane.heightProperty());

        wrongText = new Text("Wrong UserName or Password\n\t    PleaseTry Again");
        wrongText.setLayoutX(610);
        wrongText.setLayoutY(80);
        wrongText.setFill(Color.RED);
        wrongText.setVisible(false);
        wrongText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR,12));
        lblUserName = new Label("UserName");
        lblUserName.setLayoutX(620);
        lblUserName.setLayoutY(120);
        lblUserName.setTextFill(Color.BLACK);
        lblUserName.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 18));
        lblPassword = new Label("Password");
        lblPassword.setLayoutX(620);
        lblPassword.setLayoutY(190);
        lblPassword.setTextFill(Color.BLACK);
        lblPassword.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 18));
        UNameTxf = new TextField();
        UNameTxf.setPrefColumnCount(13);
        UNameTxf.setLayoutX(620);
        UNameTxf.setLayoutY(155);
        UNameTxf.setOnAction(event -> {
            UNameTxf.getText();
            passField.requestFocus();
        });
        passField = new PasswordField();
        passField.setPrefColumnCount(13);
        passField.setLayoutX(620);
        passField.setLayoutY(225);
        passField.setOnAction(event -> {
            passField.getText();
            btnLogin.requestFocus();
        });
        btnLogin = new Button("Login");
        btnLogin.setPrefSize(100,15);
        btnLogin.setLayoutX(620);
        btnLogin.setLayoutY(265);
        btnLogin.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btnLogin.setOnAction(event -> {
            Login();
        });
        btnLogin.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                Login();
            }
        });
        btnForgotPass = new Button("Forgot Password");
        btnForgotPass.setPrefSize(100, 15);
        btnForgotPass.setLayoutX(620);
        btnForgotPass.setLayoutY(300);
        btnForgotPass.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 10));
        btnGoBack = new Button("Go Back");
        btnGoBack.setPrefSize(100,15);
        btnGoBack.setLayoutX(620);
        btnGoBack.setLayoutY(550);
        btnGoBack.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btnGoBack.setOnAction(event -> {
            PrisonHome PH = new PrisonHome();
            PH.start(PH.HomeStage);
            AdminStage.hide();
        });
        AdminPane.getChildren().addAll(AdminIV,wrongText,lblUserName,lblPassword,UNameTxf,passField,btnLogin,btnForgotPass,btnGoBack);
        Scene AdminScene = new Scene(AdminPane,800,600);
        AdminStage.setScene(AdminScene);
        AdminStage.setTitle("Prison Administration");
        AdminStage.show();
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
                AdminstrationAA AdminA = new AdminstrationAA();
                AdminA.start(AdminA.AdminAStage);
                AdminStage.hide();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

