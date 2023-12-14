package sample;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;

public class UpdateProfile extends Application {

    Stage UpdateProfile = new Stage();
    private Label lblPrsProfile,lblPrsID,lblFName,lblMidName,lblLName,lblEntranceDate,lblReleaseDate,lblCrime,lblEmergency;
    private Label lblSecuLevel, lblCellShare, lblCellNo,lblDateofBirth,lblMaritalStatus,lblBloodType,lblHealth;
    private TextField txfPrsId, txfFName, txfMidName, txfLName, txfSecLev, txfCellSh,txfMS, txfBlood,txfCellNo;
    private RadioButton rbtSecLevelH,rbtSecLevelN,rbtCellShareY,rbtCellShareN,rbtSingle,rbtMarried,rbtDivorced;
    private RadioButton rbtA,rbtB,rbtAB,rbtO;
    DatePicker dateEntry, dateRelease, birthDate;
    private Button btnGoBack, btnChooseP, btnFindProfile, btnUpdate;
    private FileChooser photoFile1 = new FileChooser();
    File photoFile;
    private Rectangle ImgRect;
    private ImageView ImgView;
    private Image img;
    private TextArea taHealth, taCrime, taEmegency;

    @Override
    public void start(Stage primaryStage){
        Pane UpdatePane = new Pane();

        lblPrsProfile = new Label("Add Prisoner");
        lblPrsProfile.setLayoutX(40);
        lblPrsProfile.setLayoutY(20);
        lblPrsProfile.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR,20));
        lblPrsID = new Label("Prisoner ID");
        lblPrsID.setLayoutX(60);
        lblPrsID.setLayoutY(60);
        lblPrsID.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        txfPrsId = new TextField();
        txfPrsId.setPrefColumnCount(5);
        txfPrsId.setLayoutX(190);
        txfPrsId.setLayoutY(60);
        txfPrsId.setOnAction(event -> {
            txfPrsId.getText();
            btnFindProfile.requestFocus();
        });
        txfPrsId.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
            txfPrsId.getText();
            btnFindProfile.requestFocus();}
        });
        btnFindProfile = new Button("Find");
        btnFindProfile.setLayoutX(280);
        btnFindProfile.setLayoutY(60);
        btnFindProfile.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        btnFindProfile.setOnAction(event -> {
            updateProfile(Integer.parseInt(txfPrsId.getText()));
        });
        lblFName = new Label("FirstName");
        lblFName.setLayoutX(50);
        lblFName.setLayoutY(100);
        lblFName.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR,16));
        lblMidName = new Label("MiddleName");
        lblMidName.setLayoutX(50);
        lblMidName.setLayoutY(140);
        lblMidName.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR,16));
        lblLName = new Label("LastName");
        lblLName.setLayoutX(50);
        lblLName.setLayoutY(180);
        lblLName.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR,16));
        lblDateofBirth = new Label("Date of Birth");
        lblDateofBirth.setLayoutX(50);
        lblDateofBirth.setLayoutY(220);
        lblDateofBirth.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.REGULAR,16));
        lblMaritalStatus = new Label("Marital Status");
        lblMaritalStatus.setLayoutX(50);
        lblMaritalStatus.setLayoutY(260);
        lblMaritalStatus.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.REGULAR,16));
        lblEntranceDate = new Label("Entrance Date");
        lblEntranceDate.setLayoutX(50);
        lblEntranceDate.setLayoutY(330);
        lblEntranceDate.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR,16));
        lblReleaseDate = new Label("Release Date");
        lblReleaseDate.setLayoutX(50);
        lblReleaseDate.setLayoutY(370);
        lblReleaseDate.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR,16));
        lblSecuLevel = new Label("Security Level");
        lblSecuLevel.setLayoutX(50);
        lblSecuLevel.setLayoutY(410);
        lblSecuLevel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR,16));
        lblCellShare = new Label("Cell Sharing");
        lblCellShare.setLayoutX(50);
        lblCellShare.setLayoutY(450);
        lblCellShare.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR,16));
        lblCellNo = new Label("Cell Number");
        lblCellNo.setLayoutX(50);
        lblCellNo.setLayoutY(490);
        lblCellNo.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR,16));
        lblBloodType = new Label("Blood Type");
        lblBloodType.setLayoutX(450);
        lblBloodType.setLayoutY(200);
        lblBloodType.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR,16));
        lblHealth = new Label("Health Information");
        lblHealth.setLayoutX(450);
        lblHealth.setLayoutY(230);
        lblHealth.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.REGULAR,16));
        lblCrime = new Label("Crime Committed and Sentence Passed");
        lblCrime.setLayoutX(450);
        lblCrime.setLayoutY(320);
        lblCrime.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.REGULAR,16));
        lblEmergency = new Label("Emergency Contacts");
        lblEmergency.setLayoutX(450);
        lblEmergency.setLayoutY(410);
        lblEmergency.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.REGULAR,16));

        txfFName = new TextField();
        txfFName.setPrefColumnCount(15);
        txfFName.setLayoutX(180);
        txfFName.setLayoutY(100);
        txfFName.setEditable(true);
        txfFName.setOnAction(event -> {
            txfFName.getText();
        });
        txfFName.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                txfFName.getText();
                txfMidName.requestFocus();}
        });
        txfMidName = new TextField();
        txfMidName.setPrefColumnCount(15);
        txfMidName.setLayoutX(180);
        txfMidName.setLayoutY(140);
        txfMidName.setEditable(true);
        txfMidName.setOnAction(event -> {
            txfMidName.getText();
        });
        txfMidName.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                txfMidName.getText();
                txfLName.requestFocus();}
        });
        txfLName = new TextField();
        txfLName.setPrefColumnCount(15);
        txfLName.setLayoutX(180);
        txfLName.setLayoutY(180);
        txfLName.setEditable(true);
        txfLName.setOnAction(event -> {
            txfLName.getText();
        });
        txfLName.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                txfLName.getText();
                dateEntry.requestFocus();}
        });
        birthDate = new DatePicker();
        birthDate.setLayoutX(180);
        birthDate.setLayoutY(220);
        birthDate.setEditable(true);
        birthDate.setOnAction(event -> {
            birthDate.getValue();
        });
        rbtSingle = new RadioButton("Single");
        rbtSingle.setLayoutX(180);
        rbtSingle.setLayoutY(260);
        rbtSingle.setOnAction(event -> {
            txfMS.setText("Single");
        });
        rbtMarried = new RadioButton("Married");
        rbtMarried.setLayoutX(240);
        rbtMarried.setLayoutY(260);
        rbtMarried.setOnAction(event -> {
            txfMS.setText("Married");
        });
        rbtDivorced = new RadioButton("Divorced");
        rbtDivorced.setLayoutX(310);
        rbtDivorced.setLayoutY(260);
        rbtDivorced.setOnAction(event -> {
            txfMS.setText("Divorced");
        });
        ToggleGroup tgMS = new ToggleGroup();
        rbtSingle.setToggleGroup(tgMS);
        rbtMarried.setToggleGroup(tgMS);
        rbtDivorced.setToggleGroup(tgMS);
        txfMS = new TextField();
        txfMS.setPrefColumnCount(8);
        txfMS.setLayoutX(180);
        txfMS.setLayoutY(290);
        txfMS.setEditable(true);
        txfMS.setOnAction(event ->{
            txfMS.getText();
        });
        txfMS.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                txfMS.getText();
            }
        });
        dateEntry = new DatePicker();
        dateEntry.setLayoutX(180);
        dateEntry.setLayoutY(330);
        dateEntry.setEditable(true);
        dateEntry.setOnAction(event -> {
            dateEntry.getValue();
        });
        dateRelease = new DatePicker();
        dateRelease.setLayoutX(180);
        dateRelease.setLayoutY(370);
        dateRelease.setEditable(true);
        dateRelease.setOnAction(event -> {
            dateRelease.getValue();
        });
        rbtSecLevelH = new RadioButton("High");
        rbtSecLevelH.setLayoutX(180);
        rbtSecLevelH.setLayoutY(410);
        rbtSecLevelH.setOnAction(event -> {
            txfSecLev.setText("High");
        });
        rbtSecLevelN = new RadioButton("Normal");
        rbtSecLevelN.setLayoutX(230);
        rbtSecLevelN.setLayoutY(410);
        rbtSecLevelN.setOnAction(event -> {
            txfSecLev.setText("Normal");
        });
        ToggleGroup tgSecLev = new ToggleGroup();
        rbtSecLevelH.setToggleGroup(tgSecLev);
        rbtSecLevelN.setToggleGroup(tgSecLev);
        txfSecLev = new TextField();
        txfSecLev.setPrefColumnCount(5);
        txfSecLev.setLayoutX(300);
        txfSecLev.setLayoutY(410);
        txfSecLev.setEditable(true);
        txfSecLev.setOnAction(event ->{
            txfSecLev.getText();
        });
        txfSecLev.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                txfSecLev.getText();
                txfCellSh.requestFocus();}
        });
        rbtCellShareY = new RadioButton("Yes");
        rbtCellShareY.setLayoutX(180);
        rbtCellShareY.setLayoutY(450);
        rbtCellShareY.setOnAction(event -> {
            txfCellSh.setText("Yes");
        });
        rbtCellShareN = new RadioButton("No");
        rbtCellShareN.setLayoutX(230);
        rbtCellShareN.setLayoutY(450);
        rbtCellShareN.setOnAction(event -> {
            txfCellSh.setText("No");
        });
        ToggleGroup tgCellShare = new ToggleGroup();
        rbtCellShareY.setToggleGroup(tgCellShare);
        rbtCellShareN.setToggleGroup(tgCellShare);
        txfCellSh = new TextField();
        txfCellSh.setPrefColumnCount(5);
        txfCellSh.setLayoutX(300);
        txfCellSh.setLayoutY(450);
        txfCellSh.setEditable(true);
        txfCellSh.setOnAction(event -> {
            txfCellSh.getText();
        });
        txfCellSh.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                txfCellSh.getText();
                txfCellNo.requestFocus();
            }
        });
        txfCellNo = new TextField();
        txfCellNo.setPrefColumnCount(15);
        txfCellNo.setLayoutX(180);
        txfCellNo.setLayoutY(490);
        txfCellNo.setEditable(true);
        txfCellNo.setOnAction(event -> {
            txfCellNo.getText();
        });
        txfCellNo.setOnKeyPressed(event -> {

        });
        rbtA = new RadioButton("A");
        rbtA.setLayoutX(540);
        rbtA.setLayoutY(200);
        rbtA.setOnAction(event -> {
            txfBlood.setText("A");
        });
        rbtB = new RadioButton("B");
        rbtB.setLayoutX(580);
        rbtB.setLayoutY(200);
        rbtB.setOnAction(event -> {
            txfBlood.setText("B");
        });
        rbtAB = new RadioButton("AB");
        rbtAB.setLayoutX(620);
        rbtAB.setLayoutY(200);
        rbtAB.setOnAction(event -> {
            txfBlood.setText("AB");
        });
        rbtO = new RadioButton("O");
        rbtO.setLayoutX(660);
        rbtO.setLayoutY(200);
        rbtO.setOnAction(event -> {
            txfBlood.setText("O");
        });
        ToggleGroup tgBlood = new ToggleGroup();
        rbtA.setToggleGroup(tgBlood);
        rbtB.setToggleGroup(tgBlood);
        rbtAB.setToggleGroup(tgBlood);
        rbtO.setToggleGroup(tgBlood);
        txfBlood = new TextField();
        txfBlood.setLayoutX(700);
        txfBlood.setLayoutY(195);
        txfBlood.setPrefColumnCount(5);
        txfBlood.setEditable(true);
        txfBlood.setOnAction(event -> {
            txfBlood.getText();
        });
        txfBlood.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                txfBlood.getText();
                taHealth.requestFocus();
            }
        });
        taHealth = new TextArea();
        taHealth.setLayoutX(450);
        taHealth.setLayoutY(260);
        taHealth.setPrefSize(300,50);
        taCrime = new TextArea();
        taCrime.setLayoutX(450);
        taCrime.setLayoutY(350);
        taCrime.setPrefSize(300,50);
        taEmegency = new TextArea();
        taEmegency.setLayoutX(450);
        taEmegency.setLayoutY(440);
        taEmegency.setPrefSize(300,50);
        ImgRect = new Rectangle(450,80,100,100);
        ImgRect.setStroke(Color.BLACK);
        ImgRect.setFill(Color.WHITE);
        img = new Image("sample/Images/imagesPP.jpeg");
        ImgView = new ImageView(img);
        ImgView.setFitHeight(99);
        ImgView.setFitWidth(99);
        ImgView.setLayoutX(450);
        ImgView.setLayoutY(80);
        btnChooseP = new Button("Choose Photo");
        btnChooseP.setLayoutX(555);
        btnChooseP.setLayoutY(150);
        btnChooseP.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        btnChooseP.setOnAction(event -> {
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpeg*"
                    ,"*.jpg*","*.png*");
            photoFile1.getExtensionFilters().add(imageFilter);
            photoFile = photoFile1.showOpenDialog(UpdateProfile);
            if(photoFile != null){
                Image Img = new Image(photoFile.toURI().toString());
                ImgView.setImage(Img);
            }
        });
        btnUpdate = new Button("Update");
           btnUpdate.setLayoutX(70);
           btnUpdate.setLayoutY(550);
           btnUpdate.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
           btnUpdate.setOnAction(event -> {
               Alert AddAlert = new Alert(Alert.AlertType.CONFIRMATION);
               AddAlert.setTitle("Add Prisoner");
               AddAlert.setHeaderText(null);
               AddAlert.setContentText("Updating this Prisoner ?");
               Optional<ButtonType > action = AddAlert.showAndWait();
               if(action.get()==ButtonType.OK){
               Functions.Update(txfFName.getText(),txfMidName.getText(),txfLName.getText(), birthDate.getValue(),txfMS.getText()
                       ,dateEntry.getValue(),dateRelease.getValue(),txfSecLev.getText(),txfCellSh.getText(),
                       Integer.parseInt(txfCellNo.getText()),photoFile.toURI().toString(),txfBlood.getText(),taHealth.getText()
                       ,taCrime.getText(),taEmegency.getText(),Integer.parseInt(txfPrsId.getText()));}
           });
        btnGoBack = new Button("Go Back");
        btnGoBack.setLayoutX(700);
        btnGoBack.setLayoutY(550);
        btnGoBack.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        btnGoBack.setOnAction(event -> {
            Prisoner Pris = new Prisoner();
            Pris.start(Pris.PrisonerStage);
            UpdateProfile.hide();
        });
        UpdatePane.getChildren().addAll(lblPrsProfile, lblPrsID, txfPrsId, btnFindProfile, lblFName, lblMidName, lblLName,
                lblDateofBirth, lblMaritalStatus,lblEntranceDate, lblReleaseDate, lblSecuLevel, lblCellShare, lblCellNo,
                lblBloodType,lblHealth,lblCrime,lblEmergency, txfFName, txfMidName, txfLName,birthDate, dateEntry, dateRelease,
                rbtSecLevelH, rbtSecLevelN, rbtCellShareY, rbtCellShareN,rbtSingle,rbtMarried,rbtDivorced,txfMS,rbtA,rbtB,rbtAB,
                rbtO,txfBlood,taCrime,taHealth,ImgRect,ImgView,btnChooseP,taEmegency,txfCellNo,btnUpdate,btnGoBack);

        Scene UpdateScene = new Scene(UpdatePane, 800, 600);

        UpdateProfile.setTitle("Prisoner Profile");
        UpdateProfile.setResizable(false);
        UpdateProfile.setScene(UpdateScene);
        UpdateProfile.show();
    }
        public void updateProfile ( int PID){

            String sql = "SELECT * FROM Prisoner WHERE PrisonerId = '" + PID + "'";
            ResultSet rsSet = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/PrisonManagementSystem", "root", "12345678");
                Statement statement = connection.createStatement();
                rsSet = statement.executeQuery(sql);
                UpdateProfile UP = new UpdateProfile();
                while (rsSet.next()) {
                    txfFName.setText(rsSet.getString(2));
                    txfMidName.setText(rsSet.getString(3));
                    txfLName.setText(rsSet.getString(4));
                    birthDate.setValue(LocalDate.parse(rsSet.getString(5)));
                    txfMS.setText(rsSet.getString(6));
                       if(txfMS.getText().equals("Single"))
                           rbtSingle.setSelected(true);
                       if(txfMS.getText().equals("Married"))
                           rbtMarried.setSelected(true);
                       if(txfMS.getText().equals("Divorced"))
                           rbtMarried.setSelected(true);
                    dateEntry.setValue(LocalDate.parse(rsSet.getString(7)));
                    dateRelease.setValue(LocalDate.parse(rsSet.getString(8)));
                    txfSecLev.setText(rsSet.getString(9));
                       if(txfSecLev.getText().equals("High"))
                           rbtSecLevelH.setSelected(true);
                       if(txfSecLev.getText().equals("Normal"))
                           rbtSecLevelN.setSelected(true);
                    txfCellSh.setText(rsSet.getString(10));
                       if(txfCellSh.getText().equals("Yes"))
                           rbtCellShareY.setSelected(true);
                       if(txfCellSh.getText().equals("No"))
                           rbtCellShareY.setSelected(true);
                    txfCellNo.setText(rsSet.getString(11));
                    Image ImgV = new Image(rsSet.getString(12));
                    ImgView.setImage(ImgV);
                    txfBlood.setText(rsSet.getString(13));
                        if(txfBlood.getText().equals("A"))
                            rbtA.setSelected(true);
                        if(txfBlood.getText().equals("B"))
                            rbtB.setSelected(true);
                        if(txfBlood.getText().equals("AB"))
                            rbtAB.setSelected(true);
                        if(txfBlood.getText().equals("O"))
                            rbtO.setSelected(true);
                    taHealth.setText(rsSet.getString(14));
                    taCrime.setText(rsSet.getString(15));
                    taEmegency.setText(rsSet.getString(16));
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }