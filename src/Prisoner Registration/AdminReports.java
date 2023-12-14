package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AdminReports extends Application{
    Stage AdminReport = new Stage();
    private TextArea txaTE = new TextArea();
    private ComboBox<String> cbxFont = new ComboBox<>();
    private ComboBox<Integer> cbxFSize = new ComboBox<>();
    private Label lblSample = new Label("Sample Font");
    private Button btnGoBack;
    @Override
    public void start(Stage primaryStage) throws Exception{

        MenuBar MBar = new MenuBar();

        Menu MFile = new Menu("_File");
        MFile.setMnemonicParsing(true);
        MenuItem MINew = new MenuItem("New  ");
        MINew.setAccelerator(KeyCombination.keyCombination("shortcut + N"));
        MenuItem MIOpen = new MenuItem("Open  ");
        MIOpen.setAccelerator(KeyCombination.keyCombination("shortcut + O"));
        MenuItem MISave = new MenuItem("Save  ");
        MISave.setAccelerator(KeyCombination.keyCombination("shortcut + S"));
        MenuItem MIPrint = new MenuItem("Print  ");
        MIPrint.setAccelerator(KeyCombination.keyCombination("shortcut + P"));
        MenuItem MIExit = new MenuItem("Exit  ");
        MIExit.setAccelerator(KeyCombination.keyCombination("shortcut + E"));
        MFile.getItems().addAll(MINew,MIOpen,MISave,MIPrint,MIExit);
        MBar.getMenus().add(MFile);

        Menu MEdit = new Menu("_Edit");
        MEdit.setMnemonicParsing(true);
        MenuItem MICut = new MenuItem("Cut  ");
        MICut.setAccelerator(KeyCombination.keyCombination("shortcut + X"));
        MenuItem MICopy = new MenuItem("Copy  ");
        MICopy.setAccelerator(KeyCombination.keyCombination("shortcut + C"));
        MenuItem MIPaste = new MenuItem("Paste  ");
        MIPaste.setAccelerator(KeyCombination.keyCombination("shortcut + V"));
        MenuItem MIUndo = new MenuItem("Undo  ");
        MIUndo.setAccelerator(KeyCombination.keyCombination("shortcut + Z"));
        MenuItem MIRedo = new MenuItem("Redo  ");
        MIRedo.setAccelerator(KeyCombination.keyCombination("shortcut + Y"));
        MenuItem MIFind = new MenuItem("Find  ");
        MIFind.setAccelerator(KeyCombination.keyCombination("shortcut + F"));
        MenuItem MISelectAll = new MenuItem("Select All  ");
        MISelectAll.setAccelerator(KeyCombination.keyCombination("shortcut + A"));
        MEdit.getItems().addAll(MICut,MICopy,MIPaste,MIUndo,MIRedo,MIFind,MISelectAll);
        MBar.getMenus().add(MEdit);

        Menu MFormat = new Menu("_Format");
        MFormat.setMnemonicParsing(true);
        MenuItem MIFont = new MenuItem("Font ");
        MenuItem MIParSpacing = new MenuItem("Paragraph-spacing");
        MFormat.getItems().addAll(MIFont,MIParSpacing);
        MBar.getMenus().add(MFormat);

        Menu MHelp = new Menu("_Help");
        MHelp.setMnemonicParsing(true);
        MenuItem MIAbout = new MenuItem("About TextEditor");
        MHelp.getItems().addAll(MIAbout);
        MBar.getMenus().add(MHelp);

        txaTE.setPrefWidth(800);
        txaTE.setPrefHeight(500);

        Label lblAbout = new Label();
        lblAbout.setText("Natanim Coorporation\n\nVersion 2020 (0S build 14393.2020)\nBuild with JavaFX 8.0 Java JDK 12.0.1"
                +"\nBuild by Natanim Issa in Ethiopia\ncopyright,All rights reserved!");
        BorderPane PaneAbout = new BorderPane();
        PaneAbout.setCenter(lblAbout);
        Scene About = new Scene(PaneAbout,300,200);
        Stage SAbout = new Stage();
        SAbout.setResizable(false);
        SAbout.setTitle("ABOUT");
        SAbout.setScene(About);

        Label lblFont = new Label("Font");
        Label lblFSize = new Label("Size");
        cbxFont.getItems().addAll(Font.getFamilies());
        cbxFont.setValue(txaTE.getFont().getFamily());
        cbxFont.setOnAction(e -> update());
        Integer[] sizes = new Integer[100];
        cbxFSize.getItems().addAll(getSizes());
        cbxFSize.setValue((int)txaTE.getFont().getSize());
        cbxFSize.setOnAction(e -> update());
        for (int i = 0; i < 100; i++)
            sizes[i] = i + 1;

        VBox FVbox1 = new VBox(lblFont,cbxFont);
        VBox FVbox3 = new VBox(lblFSize,cbxFSize);

        HBox FHbox = new HBox(FVbox1,FVbox3);
        FHbox.setSpacing(10);
        FHbox.setAlignment(Pos.CENTER);
        BorderPane FBpane = new BorderPane();
        FBpane.setTop(FHbox);
        FBpane.setCenter(lblSample);
        Scene FScene = new Scene(FBpane,400,400);

        Stage SFont = new Stage();
        SFont.setScene(FScene);
        SFont.setResizable(false);
        SFont.setTitle("Choose Font");

        btnGoBack = new Button("Go Back");
        btnGoBack.setPrefSize(100,15);
        btnGoBack.setLayoutX(620);
        btnGoBack.setLayoutY(550);
        btnGoBack.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 14));
        btnGoBack.setOnAction(event -> {
            AdminstrationAA AdminAA = new AdminstrationAA();
            AdminAA.start(AdminAA.AdminAStage);
            AdminReport.hide();
        });

        VBox Vbox = new VBox(MBar,txaTE,btnGoBack);
        Vbox.setSpacing(0);

        Scene TEscene = new Scene(Vbox,800,600);

        AdminReport.setTitle("Text Editor");
        AdminReport.setScene(TEscene);
        AdminReport.show();
        AdminReport.setResizable(false);

        MINew.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent Aevent){
                    }
                });
        File file = new File("C:\\Users\\natan\\OneDrive\\Documents\\Boston University\\JavaFX\\PrisonMangementSystemJFX\\Reports\\Report.txt");
        MIOpen.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle( ActionEvent Aevent){
                        FileChooser FChooser = new FileChooser();
                        File Open = FChooser.showOpenDialog(AdminReport);
                        try {
                            Scanner input = new Scanner(file);
                            while (input.hasNext()){
                                txaTE.setText(input.nextLine());
                            }
                            input.close();
                        }catch (FileNotFoundException e){
                            System.out.println("File Not Found");
                        }
                    }
                });
        MISave.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent Aevent){
                        FileChooser FChooser = new FileChooser();
                        File NewFile = FChooser.showSaveDialog(AdminReport);
                        try{
                            PrintWriter output = new PrintWriter(file);
                            output.print(txaTE.getText());
                            output.close();
                        }catch(FileNotFoundException e){
                            e.printStackTrace();
                        }
                    }
                });
        MIPrint.setOnAction(e ->{
            new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent Aevent){

                }
            };
        } );
        MIExit.setOnAction(e -> Platform.exit());
        MICut.setOnAction(e -> txaTE.cut());
        MICopy.setOnAction(e -> txaTE.copy());
        MIPaste.setOnAction(e -> txaTE.paste());
        MIUndo.setOnAction(e -> txaTE.undo());
        MIRedo.setOnAction(e -> txaTE.redo());
        MIFind.setOnAction(e -> {
            new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent Aevent){

                }
            };
        });
        MISelectAll.setOnAction(e ->txaTE.selectAll());
        MIFont.setOnAction(e -> SFont.show());
        MIParSpacing.setOnAction(e -> {
            new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent Aevent){

                }
            };
        });
        MIAbout.setOnAction(e -> SAbout.show());
    }
    private void update(){
        String fontFamily = cbxFont.getValue();
        double size = cbxFSize.getValue();
        lblSample.setFont(Font.font(fontFamily, size));
        txaTE.setFont(Font.font(fontFamily, size));
    }
    private Integer[] getSizes() {
        Integer[] sizes = new Integer[100];

        for (int i = 0; i < 100; i++)
            sizes[i] = i + 1;
        return sizes;
    }
}
