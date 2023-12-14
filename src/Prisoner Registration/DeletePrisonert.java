package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DeletePrisonert extends Application {

    Stage DeletePrs = new Stage();
    private Label lblTableName, lblDelete;
    private Button btnDeletePrs, btnGoBack;
    private TextField txfDelete;
    private TableView PTable;
    private TableColumn<PrisonerTD,Integer> colPrisonerID;
    private TableColumn<PrisonerTD,String> colFirstName, colLastName;

    @Override
    public void start(Stage primaryStage){

        PTable = new TableView();
        PTable.setEditable(false);
        PTable.setPrefSize(800,400);
        PTable.setLayoutY(50);;
        lblTableName = new Label("List Of Prisoners");
        lblTableName.setLayoutX(20);
        lblTableName.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR,30));
        lblDelete = new Label("Insert the PrisonerID : ");
        lblDelete.setLayoutX(50);
        lblDelete.setLayoutY(500);
        lblDelete.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        txfDelete = new TextField();
        txfDelete.setPrefColumnCount(5);
        txfDelete.setLayoutX(200);
        txfDelete.setLayoutY(500);
        txfDelete.setOnAction(event -> {
            txfDelete.getText();
        });
        txfDelete.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                txfDelete.getText();
                btnDeletePrs.requestFocus();
            }
        });
        btnDeletePrs = new Button("Delete Prisoner");
        btnDeletePrs.setLayoutX(280);
        btnDeletePrs.setLayoutY(500);
        btnDeletePrs.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        btnDeletePrs.setOnAction(event -> {
            Alert AddAlert = new Alert(Alert.AlertType.CONFIRMATION);
            AddAlert.setTitle("Add Prisoner");
            AddAlert.setHeaderText(null);
            AddAlert.setContentText("Are you sure adding this Prisoner ?");
            Optional<ButtonType > action = AddAlert.showAndWait();
            if(action.get()==ButtonType.OK){
                Functions.deletePrisoner(Integer.parseInt(txfDelete.getText()));
                Initialize();}
        });
        btnDeletePrs.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                Alert AddAlert = new Alert(Alert.AlertType.CONFIRMATION);
                AddAlert.setTitle("Add Prisoner");
                AddAlert.setHeaderText(null);
                AddAlert.setContentText("Deleting this Prisoner ?");
                Optional<ButtonType > action = AddAlert.showAndWait();
                if(action.get()==ButtonType.OK){
                    Functions.deletePrisoner(Integer.parseInt(txfDelete.getText()));
                    Initialize();}
            }
        });
        btnGoBack = new Button("Go Back");
        btnGoBack.setLayoutX(700);
        btnGoBack.setLayoutY(550);
        btnGoBack.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        btnGoBack.setOnAction(event -> {
            Prisoner PRS = new Prisoner();
            PRS.start(PRS.PrisonerStage);
            DeletePrs.hide();
        });
        Group tblGroup = new Group();
        tblGroup.getChildren().addAll(lblTableName,lblDelete,txfDelete,btnDeletePrs,btnGoBack);

        colPrisonerID = new TableColumn<PrisonerTD,Integer>("Prisoner ID");
        colPrisonerID.setPrefWidth(80);
        colPrisonerID.setResizable(false);
        colFirstName = new TableColumn<PrisonerTD,String>("FirstName");
        colFirstName.setPrefWidth(150);
        colFirstName.setResizable(false);
        colLastName = new TableColumn<PrisonerTD,String>("LastName");
        colLastName.setPrefWidth(150);
        colLastName.setResizable(false);
        PTable.getColumns().addAll(colPrisonerID,colFirstName,colLastName);
        Initialize();

        Scene TableScene = new Scene(tblGroup, 800,600);

        ((Group)TableScene.getRoot()).getChildren().add(PTable);


        DeletePrs.setScene(TableScene);
        DeletePrs.setTitle("Prisoners");
        DeletePrs.show();
    }

    public void Initialize(){
        colPrisonerID.setCellValueFactory(cellData -> cellData.getValue().getPRSId().asObject());
        colFirstName.setCellValueFactory(cellData -> cellData.getValue().getPRSFN());
        colLastName.setCellValueFactory(cellData -> cellData.getValue().getPRSLN());
        ObservableList<PrisonerTD> PrisonerList = Functions.getAllPrsList();
        populateTable(PrisonerList);
    }
    private void populateTable(ObservableList<PrisonerTD>PrisonerList) {
        PTable.setItems(PrisonerList);
    }
}
