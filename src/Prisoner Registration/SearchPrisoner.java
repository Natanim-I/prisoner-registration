package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class SearchPrisoner extends Application {

    Stage SearchStage = new Stage();
    private Label lblTableName, lblSearchType, lblSearch;
    private Button btnSearch, btnGoBack;
    private TextField txfSearchStr;
    private TableView PTable;
    private TableColumn<PrisonerTD,Integer> colPrisonerID, colPrisonerCN;
    private TableColumn<PrisonerTD,String> colFirstName, colLastName;
    private ComboBox<String> cbxSearchType;

    @Override
    public void start(Stage primaryStage){

        PTable = new TableView();
        PTable.setEditable(false);
        PTable.setPrefSize(800,400);
        PTable.setLayoutY(50);;
        lblTableName = new Label("List Of Prisoners");
        lblTableName.setLayoutX(20);
        lblTableName.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR,30));
        lblSearchType = new Label("Select Search Type ");
        lblSearchType.setLayoutX(50);
        lblSearchType.setLayoutY(500);
        lblSearchType.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        ObservableList<String> Entries = FXCollections.observableArrayList("ID","FirstName","LastName","Cell No");
        cbxSearchType = new ComboBox<String>(Entries);
        cbxSearchType.setValue("ID");
        cbxSearchType.setLayoutX(190);
        cbxSearchType.setLayoutY(495);
        cbxSearchType.setOnAction(event -> {
            btnSearch.setText("Search By "+cbxSearchType.getValue());
        });
        lblSearch = new Label("Enter Search String");
        lblSearch.setLayoutX(300);
        lblSearch.setLayoutY(500);
        lblSearch.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        txfSearchStr = new TextField();
        txfSearchStr.setPrefColumnCount(10);
        txfSearchStr.setLayoutX(440);
        txfSearchStr.setLayoutY(495);
        btnSearch = new Button("Search By ID");
        btnSearch.setLayoutX(580);
        btnSearch.setLayoutY(490);
        btnSearch.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        btnSearch.setOnAction(event -> {
            if(btnSearch.getText().equals("Search By ID")){
                ObservableList<PrisonerTD> SearchList = Functions.searchById(Integer.parseInt(txfSearchStr.getText()));
                populateTable(SearchList);}
            else ;
            if(btnSearch.getText().equals("Search By FirstName")){
                ObservableList<PrisonerTD> SearchList = Functions.searchByFN(txfSearchStr.getText());
                populateTable(SearchList);}
            else ;
            if(btnSearch.getText().equals("Search By LastName")){
                ObservableList<PrisonerTD> SearchList = Functions.searchByLN(txfSearchStr.getText());
                populateTable(SearchList);}
            else ;
            if(btnSearch.getText().equals("Search By Cell No")){
                ObservableList<PrisonerTD> SearchList = Functions.searchByCN(Integer.parseInt(txfSearchStr.getText()));
                populateTable(SearchList);}
            else ;
        });
        btnGoBack = new Button("Go Back");
        btnGoBack.setLayoutX(700);
        btnGoBack.setLayoutY(550);
        btnGoBack.setFont(Font.font("Courier",FontWeight.BOLD,FontPosture.REGULAR,14));
        btnGoBack.setOnAction(event -> {
            Prisoner PRS = new Prisoner();
            PRS.start(PRS.PrisonerStage);
            SearchStage.hide();
        });
        Group tblGroup = new Group();
        tblGroup.getChildren().addAll(lblTableName,lblSearchType,lblSearch,txfSearchStr,cbxSearchType,btnSearch,btnGoBack);

        colPrisonerID = new TableColumn<PrisonerTD,Integer>("Prisoner ID");
        colPrisonerID.setPrefWidth(80);
        colPrisonerID.setResizable(false);
        colFirstName = new TableColumn<PrisonerTD,String>("FirstName");
        colFirstName.setPrefWidth(150);
        colFirstName.setResizable(false);
        colLastName = new TableColumn<PrisonerTD,String>("LastName");
        colLastName.setPrefWidth(150);
        colLastName.setResizable(false);
        colPrisonerCN = new TableColumn<PrisonerTD,Integer>("Cell Number");
        colPrisonerCN.setPrefWidth(80);
        colPrisonerCN.setResizable(false);
        PTable.getColumns().addAll(colPrisonerID,colFirstName,colLastName,colPrisonerCN);
        Initialize();
        Scene TableScene = new Scene(tblGroup, 800,600);

        ((Group)TableScene.getRoot()).getChildren().add(PTable);

        SearchStage.setScene(TableScene);
        SearchStage.setTitle("Prisoners");
        SearchStage.show();
    }
    public void Initialize(){
        colPrisonerID.setCellValueFactory(cellData -> cellData.getValue().getPRSId().asObject());
        colFirstName.setCellValueFactory(cellData -> cellData.getValue().getPRSFN());
        colLastName.setCellValueFactory(cellData -> cellData.getValue().getPRSLN());
        colPrisonerCN.setCellValueFactory(cellData -> cellData.getValue().getPRSCN().asObject());
        ObservableList<PrisonerTD> PrisonerList = Functions.getAllPrsList();
        populateTable(PrisonerList);
    }
    private void populateTable(ObservableList<PrisonerTD>PrisonerList) {
        PTable.setItems(PrisonerList);
    }
}
