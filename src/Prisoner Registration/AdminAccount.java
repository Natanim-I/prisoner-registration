package sample;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.Optional;

public class AdminAccount extends Application {

    Stage CreateA = new Stage();
    private Label lblUserName, lblPassword, lblAdminId;
    private TextField txfUserName, txfAdminId;
    private PasswordField psfPassword;
    private Button btnCreate, btnDelete, btnSubmit, btnDeleteA, btnGoBack;
    public static void main(String[]args){
        launch(args);
    }
    public void start(Stage primaryStage){
        Pane CreatePane = new Pane();

        Image AdminImage = new Image("sample/Images/imagesA.jpeg");
        ImageView AdminIV = new ImageView(AdminImage);

        AdminIV.fitWidthProperty().bind(CreatePane.widthProperty().subtract(300));
        AdminIV.fitHeightProperty().bind(CreatePane.heightProperty());

        lblUserName = new Label("UserName");
        lblUserName.setLayoutX(590);
        lblUserName.setLayoutY(100);
        lblUserName.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR,16));
        lblPassword = new Label("Password");
        lblPassword.setLayoutX(590);
        lblPassword.setLayoutY(180);
        lblPassword.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR,16));
        lblAdminId = new Label("Admin Id");
        lblAdminId.setLayoutX(570);
        lblAdminId.setLayoutY(120);
        lblAdminId.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR,16));
        txfUserName = new TextField();
        txfUserName.setLayoutX(590);
        txfUserName.setLayoutY(140);
        txfUserName.setPrefColumnCount(15);
        txfAdminId = new TextField();
        txfAdminId.setLayoutX(570);
        txfAdminId.setLayoutY(150);
        txfAdminId.setPrefColumnCount(10);
        psfPassword = new PasswordField();
        psfPassword.setLayoutX(590);
        psfPassword.setLayoutY(220);
        psfPassword.setPrefColumnCount(15);
        Text txtSuccess = new Text(510,350,"Admin Account Created Successfully");
        txtSuccess.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,16));
        txtSuccess.setFill(Color.BLUE);
        txtSuccess.setVisible(false);
        Text txtDeleted = new Text(510,350,"Admin Account Deleted Successfully");
        txtDeleted.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,16));
        txtDeleted.setFill(Color.BLUE);
        txtDeleted.setVisible(false);
        btnSubmit = new Button("Create");
        btnSubmit.setLayoutX(690);
        btnSubmit.setLayoutY(260);
        btnSubmit.setTextFill(Color.BLUE);
        btnSubmit.setPrefWidth(80);
        btnSubmit.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR,16));
        btnSubmit.setOnAction(event -> {
            if(txfUserName.getText().isEmpty())
                txfUserName.setPromptText("Insert UserName");
            else if(psfPassword.getText().isEmpty())
                psfPassword.setPromptText("Insert Password");
            else{
                Alert AddAlert = new Alert(Alert.AlertType.CONFIRMATION);
                AddAlert.setTitle("Add Prisoner");
                AddAlert.setHeaderText(null);
                AddAlert.setContentText("Creating this Admin Account ?");
                Optional<ButtonType > action = AddAlert.showAndWait();
                if(action.get()==ButtonType.OK){
                    Functions.insertIntoAdmin(txfUserName.getText(),psfPassword.getText());
                    txtSuccess.setVisible(true);}
            }
        });
        btnDeleteA = new Button("Delete");
        btnDeleteA.setLayoutX(570);
        btnDeleteA.setLayoutY(200);
        btnDeleteA.setTextFill(Color.RED);
        btnDeleteA.setPrefWidth(80);
        btnDeleteA.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR,16));
        btnDeleteA.setOnAction(event -> {
            if(txfAdminId.getText().isEmpty())
                txfAdminId.setPromptText("Insert Admin Id");
            else{
                Alert AddAlert = new Alert(Alert.AlertType.CONFIRMATION);
                AddAlert.setTitle("Add Prisoner");
                AddAlert.setHeaderText(null);
                AddAlert.setContentText("Deleting This Admin Account ?");
                Optional<ButtonType > action = AddAlert.showAndWait();
                if(action.get()==ButtonType.OK){
                    Functions.deleteAdmin(Integer.parseInt(txfAdminId.getText()));
                    txtDeleted.setVisible(true);}
            }
        });
        btnCreate = new Button("Create");
        btnCreate.setLayoutX(590);
        btnCreate.setLayoutY(70);
        btnCreate.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 14));
        btnCreate.setOnAction(event -> {
            Pane CreateP = new Pane();
            CreateP.getChildren().addAll(AdminIV,lblUserName,txfUserName,lblPassword,psfPassword,btnSubmit,txtSuccess,btnGoBack);
            Scene CreateSc = new Scene(CreateP,800,600);
            CreateA.setScene(CreateSc);
        });
        btnDelete = new Button("Delete");
        btnDelete.setLayoutX(680);
        btnDelete.setLayoutY(70);
        btnDelete.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 14));
        btnDelete.setOnAction(event -> {
            Pane DeleteP = new Pane();
            DeleteP.getChildren().addAll(AdminIV,lblAdminId,txfAdminId,btnDeleteA,txtDeleted,btnGoBack);
            Scene DeleteSc = new Scene(DeleteP,800,600);
            CreateA.setScene(DeleteSc);
        });
        btnGoBack = new Button("Go Back");
        btnGoBack.setPrefSize(100,15);
        btnGoBack.setLayoutX(620);
        btnGoBack.setLayoutY(550);
        btnGoBack.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 14));
        btnGoBack.setOnAction(event -> {
            AdminstrationAA AdminAA = new AdminstrationAA();
            AdminAA.start(AdminAA.AdminAStage);
            CreateA.hide();
        });

        CreatePane.getChildren().addAll(AdminIV,btnCreate,btnDelete,btnGoBack);
        Scene CreateScene = new Scene(CreatePane,800,600);
        CreateA.setScene(CreateScene);
        CreateA.setTitle("Admin Account");
        CreateA.show();
    }
}

