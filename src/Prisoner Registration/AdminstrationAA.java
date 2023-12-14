package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class AdminstrationAA extends Application {

    Stage AdminAStage = new Stage();
    private Button btnShowPLog,btnShowStaff,btnShowVisitorsLog,btnShowPrisInfo,btnReports,btnCreateAdminAcc,btnGoBack;
    public void start(Stage primaryStage){

        Pane AdminA = new Pane();

        Image AdminImage = new Image("sample/Images/imagesA.jpeg");
        ImageView AdminIV = new ImageView(AdminImage);

        AdminIV.fitWidthProperty().bind(AdminA.widthProperty());
        AdminIV.fitHeightProperty().bind(AdminA.heightProperty().subtract(400));

        btnShowPLog = new Button("Prisoner Log");
        btnShowPLog.setLayoutX(150);
        btnShowPLog.setLayoutY(350);
        btnShowPLog.setPrefSize(150,50);
        btnShowPLog.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC,16));
        btnShowPLog.setOnAction(event -> {
            PrisonerLog PLog = new PrisonerLog();
            PLog.start(PLog.PrisonLog);
            AdminAStage.hide();
        });
        btnShowPLog.setTextFill(Color.BLUE);
        btnShowStaff = new Button("Staff Log");
        btnShowStaff.setLayoutX(150);
        btnShowStaff.setLayoutY(250);
        btnShowStaff.setPrefSize(150,50);
        btnShowStaff.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,16));
        btnShowStaff.setTextFill(Color.BLUE);
        btnShowVisitorsLog = new Button("Visitors Log");
        btnShowVisitorsLog.setLayoutX(150);
        btnShowVisitorsLog.setLayoutY(450);
        btnShowVisitorsLog.setPrefSize(150,50);
        btnShowVisitorsLog.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,16));
        btnShowVisitorsLog.setTextFill(Color.BLUE);
        btnCreateAdminAcc = new Button("Create Admin");
        btnCreateAdminAcc.setLayoutX(520);
        btnCreateAdminAcc.setLayoutY(250);
        btnCreateAdminAcc.setPrefSize(150,50);
        btnCreateAdminAcc.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,16));
        btnCreateAdminAcc.setTextFill(Color.BLUE);
        btnCreateAdminAcc.setOnAction(event -> {
            AdminAccount AdminAcc = new AdminAccount();
            AdminAcc.start((AdminAcc.CreateA));
            AdminAStage.hide();
        });
        btnShowPrisInfo = new Button("Show Information");
        btnShowPrisInfo.setLayoutX(520);
        btnShowPrisInfo.setLayoutY(350);
        btnShowPrisInfo.setPrefSize(150,50);
        btnShowPrisInfo.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,16));
        btnShowPrisInfo.setTextFill(Color.BLUE);
        btnReports = new Button("Reports");
        btnReports.setLayoutX(520);
        btnReports.setLayoutY(450);
        btnReports.setPrefSize(150,50);
        btnReports.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,16));
        btnReports.setTextFill(Color.BLUE);
        btnReports.setOnAction(event -> {
            AdminReports AdR = new AdminReports();
            try {
                AdR.start(AdR.AdminReport);
            }catch (Exception e){
                e.printStackTrace();
            }
              AdminAStage.hide();
        });
        btnGoBack = new Button("Go Back");
        btnGoBack.setPrefSize(100,15);
        btnGoBack.setLayoutX(620);
        btnGoBack.setLayoutY(550);
        btnGoBack.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btnGoBack.setOnAction(event -> {
            Administration Admin = new Administration();
            Admin.start(Admin.AdminStage);
            AdminAStage.hide();
        });

        AdminA.getChildren().addAll(AdminIV,btnCreateAdminAcc,btnShowVisitorsLog,btnReports,btnShowPLog,btnShowPrisInfo,btnShowStaff,btnGoBack);
        Scene AdminAScene = new Scene(AdminA,800,600);
        AdminAStage.setScene(AdminAScene);
        AdminAStage.setTitle("Administration");
        AdminAStage.show();
    }
}
