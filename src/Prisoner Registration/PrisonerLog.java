package sample;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PrisonerLog extends Application {

    Stage PrisonLog = new Stage();
    private Label lblTableName;
    private Button btnGoBack,btnSearch;
    private TableView PTable;
    private TableColumn<PrisonerLogTD,Integer> colPrisonerID, colCellNo;
    private TableColumn<PrisonerLogTD,String> colFirstName, colLastName, colPrsStatus;

    public void start(Stage primaryStage){
        PTable = new TableView();
        PTable.setEditable(false);
        PTable.setPrefSize(800,400);
        PTable.setLayoutY(50);
        PTable.setOnMouseClicked(event -> {

        });
        lblTableName = new Label("List Of Prisoners");
        lblTableName.setLayoutX(20);
        lblTableName.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR,30));
        btnGoBack = new Button("Go Back");
        btnGoBack.setLayoutX(700);
        btnGoBack.setLayoutY(550);
        btnGoBack.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        btnGoBack.setOnAction(event -> {
            AdminstrationAA AdminAA = new AdminstrationAA();
            AdminAA.start(AdminAA.AdminAStage);
            PrisonLog.hide();
        });
        btnSearch = new Button("Search Prisoner");
        btnSearch.setLayoutX(50);
        btnSearch.setLayoutY(500);
        btnSearch.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        btnSearch.setOnAction(event -> {
            SearchPrisonerLog srchPrisonerLog = new SearchPrisonerLog();
            srchPrisonerLog.start(srchPrisonerLog.SearchStage);
            PrisonLog.hide();
        });

        Group tblGroup = new Group();
        tblGroup.getChildren().addAll(lblTableName,btnSearch,btnGoBack);

        colPrisonerID = new TableColumn<PrisonerLogTD,Integer>("Prisoner ID");
        colPrisonerID.setPrefWidth(80);
        colPrisonerID.setResizable(false);
        colFirstName = new TableColumn<PrisonerLogTD,String>("FirstName");
        colFirstName.setPrefWidth(150);
        colFirstName.setResizable(false);
        colLastName = new TableColumn<PrisonerLogTD,String>("LastName");
        colLastName.setPrefWidth(150);
        colLastName.setResizable(false);
        colCellNo = new TableColumn<PrisonerLogTD,Integer>("Cell Number");
        colCellNo.setPrefWidth(80);
        colCellNo.setResizable(false);
        colPrsStatus = new TableColumn<PrisonerLogTD,String>("Status");
        colPrsStatus.setPrefWidth(120);
        colPrsStatus.setResizable(false);
        PTable.getColumns().addAll(colPrisonerID,colFirstName,colLastName,colCellNo,colPrsStatus);
        Initialize();

        Scene LogTableScene = new Scene(tblGroup, 800,600);
        ((Group)LogTableScene.getRoot()).getChildren().add(PTable);

        PrisonLog.setTitle("Prisoners Log");
        PrisonLog.setScene(LogTableScene);
        PrisonLog.show();
    }
    public void Initialize(){
        colPrisonerID.setCellValueFactory(cellData -> cellData.getValue().getPRSId().asObject());
        colFirstName.setCellValueFactory(cellData -> cellData.getValue().getPRSFN());
        colLastName.setCellValueFactory(cellData -> cellData.getValue().getPRSLN());
        colCellNo.setCellValueFactory(cellData -> cellData.getValue().getPRSCN().asObject());
        colPrsStatus.setCellValueFactory(cellData -> cellData.getValue().getPRSLS());
        ObservableList<PrisonerLogTD> PrisonerList = Functions.getAllPrsListLog();
        populateTable(PrisonerList);
    }
    private void populateTable(ObservableList<PrisonerLogTD>PrisonerList) {
        PTable.setItems(PrisonerList);
    }
}

class PrisonerLogTD {

    private IntegerProperty PID;
    private IntegerProperty PCN;
    private StringProperty PFN;
    private StringProperty PLN;
    private StringProperty PStatus;

    public PrisonerLogTD() {
        this.PID = new SimpleIntegerProperty();
        this.PFN = new SimpleStringProperty();
        this.PLN = new SimpleStringProperty();
        this.PCN = new SimpleIntegerProperty();
        this.PStatus = new SimpleStringProperty();
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

    public String getPrsStatus () {
        return PStatus.get();
    }
    public void setPrsStatus (String prsStat){
        this.PStatus.set(prsStat);
    }
    public StringProperty getPRSLS(){
        return PStatus;
    }
}

