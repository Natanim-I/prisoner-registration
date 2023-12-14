package sample.Chat;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.PrisonHome;

public class ChatGUI extends Application {

    private boolean isServer = true;
    private Button btnGoBack;
    private TextArea messsageArea = new TextArea();
    //private Networking connect = isServer ? createServer() : createClient();

    private Parent createContent(){

        messsageArea.setPrefSize(300,550);
        messsageArea.setEditable(false);
        TextField msgInput = new TextField();
        msgInput.setPrefHeight(50);
        msgInput.setPrefWidth(700);
        msgInput.setOnAction(event -> {
            String message = isServer ? "Server: " : "Client: ";
            message += msgInput.getText();
            msgInput.clear();

            messsageArea.appendText(message + "\n");
            try {
                //connect.Send(message);
            }catch (Exception e){
                messsageArea.appendText("Failed to send data" + "\n");
            }
        });

        VBox chatRoom = new VBox(5,messsageArea,msgInput);
        chatRoom.setPrefSize(400,600);

        return chatRoom;
    }
    public void init(){
        try {
            //connect.startConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void start(Stage primaryStage){

        Pane chatPane = new Pane();

        Image WelcomeImage = new Image("sample/Images/images.jpeg");
        ImageView ChatIV = new ImageView(WelcomeImage);

        ChatIV.fitWidthProperty().bind(chatPane.widthProperty());
        ChatIV.fitHeightProperty().bind(chatPane.heightProperty());

        btnGoBack = new Button("Go Back");
        btnGoBack.setPrefSize(100,15);
        btnGoBack.setLayoutX(620);
        btnGoBack.setLayoutY(550);
        btnGoBack.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btnGoBack.setOnAction(event -> {
            PrisonHome PH = new PrisonHome();
               PH.start(primaryStage);
               primaryStage.close();
        });
           chatPane.getChildren().addAll(ChatIV,btnGoBack,createContent());
        Scene chatScene = new Scene(chatPane,800,600);
        primaryStage.setScene(chatScene);
        primaryStage.show();
    }

//    private Server createServer(){
//        return new Server(1897, data -> {
//            Platform.runLater(() -> {messsageArea.appendText(data.toString() + "\n");});
//        });
//    }

//    private Client createClient(){
//        return new Client("127.0.0.1",1897, data -> {
//            Platform.runLater(() -> {messsageArea.appendText(data.toString() + "\n");});
//        });
//    }

    public void stop(){
        try {
            //connect.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
