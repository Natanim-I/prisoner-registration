package sample;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class Prisoner extends Application {

    Stage PrisonerStage = new Stage();
    private Label lblTableName;
    private Button btnAdd, btnUpdate, btnDelete, btnSearch, btnGoBack;
    private TableView PTable;
    private TableColumn<PrisonerTD,Integer> colPrisonerID, colCellNo;
    private TableColumn<PrisonerTD,String> colFirstName, colLastName;
    static int View;
    private PrisonerProfile PP = new PrisonerProfile();
    @Override
    public void start(Stage primaryStage){

        PTable = new TableView();
        PTable.setEditable(false);
        PTable.setPrefSize(800,400);
        PTable.setLayoutY(50);;
        lblTableName = new Label("List Of Prisoners");
        lblTableName.setLayoutX(20);
        lblTableName.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR,30));
        btnAdd = new Button("Add Prisoner");
        btnAdd.setLayoutX(30);
        btnAdd.setLayoutY(500);
        btnAdd.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        btnAdd.setOnAction(event -> {
            AddPrisoner ADP = new AddPrisoner();
            ADP.start(ADP.AddPrisoner);
            PrisonerStage.hide();
        });
        btnUpdate = new Button("Update Profile");
        btnUpdate.setLayoutX(150);
        btnUpdate.setLayoutY(500);
        btnUpdate.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        btnUpdate.setOnAction(event -> {
            UpdateProfile UDP = new UpdateProfile();
            UDP.start(UDP.UpdateProfile);
            PrisonerStage.hide();
        });
        btnDelete = new Button("Delete Prisoner");
        btnDelete.setLayoutX(280);
        btnDelete.setLayoutY(500);
        btnDelete.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        btnDelete.setOnAction(event -> {
            DeletePrisonert DTPRS = new DeletePrisonert();
            DTPRS.start(DTPRS.DeletePrs);
            PrisonerStage.hide();
        });
        btnDelete.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                DeletePrisonert DTPRS = new DeletePrisonert();
                DTPRS.start(DTPRS.DeletePrs);
                PrisonerStage.hide();
            }
        });
        btnSearch = new Button("Search Prisoner");
        btnSearch.setLayoutX(410);
        btnSearch.setLayoutY(500);
        btnSearch.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        btnSearch.setOnAction(event -> {
            SearchPrisoner srchPrisoner = new SearchPrisoner();
            srchPrisoner.start(srchPrisoner.SearchStage);
            PrisonerStage.hide();
        });
        btnGoBack = new Button("Go Back");
        btnGoBack.setLayoutX(700);
        btnGoBack.setLayoutY(550);
        btnGoBack.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        btnGoBack.setOnAction(event -> {
            PrisonHome PRSHome = new PrisonHome();
            PRSHome.start(PRSHome.HomeStage);
            PrisonerStage.hide();
        });
        Group tblGroup = new Group();
        tblGroup.getChildren().addAll(lblTableName,btnAdd,btnUpdate,btnDelete,btnSearch,btnGoBack);

        colPrisonerID = new TableColumn<PrisonerTD,Integer>("Prisoner ID");
        colPrisonerID.setPrefWidth(80);
        colPrisonerID.setResizable(false);
        colFirstName = new TableColumn<PrisonerTD,String>("FirstName");
        colFirstName.setPrefWidth(150);
        colFirstName.setResizable(false);
        colLastName = new TableColumn<PrisonerTD,String>("LastName");
        colLastName.setPrefWidth(150);
        colLastName.setResizable(false);
        colCellNo = new TableColumn<PrisonerTD,Integer>("Cell Number");
        colCellNo.setPrefWidth(80);
        colCellNo.setResizable(false);
        PTable.getColumns().addAll(colPrisonerID,colFirstName,colLastName,colCellNo);
        colPrisonerID.setOnEditCommit(event -> {
            View = Integer.parseInt((String) PTable.editingCellProperty().getValue());
            PP.ViewProfile(View);
        });
        Initialize();
        Scene TableScene = new Scene(tblGroup, 800,600);

        ((Group)TableScene.getRoot()).getChildren().add(PTable);

        PrisonerStage.setScene(TableScene);
        PrisonerStage.setTitle("Prisoners");
        PrisonerStage.show();
    }
    public void Initialize(){
        colPrisonerID.setCellValueFactory(cellData -> cellData.getValue().getPRSId().asObject());
        colFirstName.setCellValueFactory(cellData -> cellData.getValue().getPRSFN());
        colLastName.setCellValueFactory(cellData -> cellData.getValue().getPRSLN());
        colCellNo.setCellValueFactory(cellData -> cellData.getValue().getPRSCN().asObject());
        ObservableList<PrisonerTD> PrisonerList = Functions.getAllPrsList();
        populateTable(PrisonerList);
    }
    private void populateTable(ObservableList<PrisonerTD>PrisonerList) {
        PTable.setItems(PrisonerList);
    }
}

class PrisonerTD {

    private IntegerProperty PID;
    private IntegerProperty PCN;
    private StringProperty PFN;
    private StringProperty PLN;

    public PrisonerTD() {
        this.PID = new SimpleIntegerProperty();
        this.PFN = new SimpleStringProperty();
        this.PLN = new SimpleStringProperty();
        this.PCN = new SimpleIntegerProperty();
    }

    public int getID(){
        return PID.get();
    }
    public void setID(int pid){
        this.PID.set(pid);
    }
    public IntegerProperty getPRSId(){
        return PID;
    }

    public int getCN(){
        return PCN.get();
    }
    public void setCN(int pcn){
        this.PCN.set(pcn);
    }
    public IntegerProperty getPRSCN(){
        return PCN;
    }

    public String getFirstName(){
        return PFN.get();
    }
    public void setFirstName(String pfn){
        this.PFN.set(pfn);
    }
    public StringProperty getPRSFN(){
        return PFN;
    }

    public String  getLastName(){
        return PLN.get();
    }
    public void setLastName(String pln){
        this.PLN.set(pln);
    }
    public StringProperty getPRSLN(){
        return PLN;
    }

}
