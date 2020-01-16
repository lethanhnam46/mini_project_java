/*
This is the main menu for you to choose
from visualizing sorting algorithms or Queue or Stack.
 */
package controll;

import towersofhanoi.MainFrame;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.text.*;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.*;

public class Mainmenu extends Application{
    private Sortingvisualize sv = new Sortingvisualize();
    private Stage firststage = new Stage();
    private Stage newStage1 = new Stage();
    private Stage newStage2 = new Stage();
    private BorderPane firstborderPane = new BorderPane();
    private HBox starthb = new HBox(100);
    private Rectangle sb1 = new Rectangle(150,80);
    private Rectangle sb2 = new Rectangle(150,80);
    private Text sortVisualize = new Text("       Sort\nVisualization");
    private Text Hanoi = new Text("Towers of HaNoi \n       Problem");
    private StackPane sp1 = new StackPane();
    private StackPane sp2 = new StackPane();
    private FlowPane bottomPane = new FlowPane();
    private FlowPane topPane = new FlowPane();
    private Label topGuide = new Label("\nChon thuat Toan");
    private Canvas topCanvas = new Canvas(800,65);

    public void start(Stage primaryStage) throws Exception {
        startScene();
        handlesp();
    }

    void startScene(){
        topGuide.getStyleClass().add("topguide");
        topPane.getChildren().addAll(topGuide,topCanvas);
        topPane.setAlignment(Pos.CENTER);
        firstborderPane.setTop(topPane);
        sb1.getStyleClass().add("startselectionbox");
        sb2.getStyleClass().add("startselectionbox");
        sortVisualize.getStyleClass().add("textstyle");
        Hanoi.getStyleClass().add("textstyle");
        starthb.setAlignment(Pos.CENTER);
        sp1.getChildren().addAll(sb1,sortVisualize);
        sp2.getChildren().addAll(sb2,Hanoi);
        starthb.getChildren().addAll(sp1,sp2);
        firstborderPane.setCenter(starthb);
        bottomPane.setAlignment(Pos.BOTTOM_RIGHT);
        firstborderPane.setBottom(bottomPane);
        Scene sc = new Scene(firstborderPane,800,400);
        sc.getStylesheets().add("visualizationstyle.css");
        firststage.setTitle("MainMenu");
        firststage.setResizable(false);
        firststage.setScene(sc);
        firststage.show();
    }

    // draw some simple pictures


    // handle the author label
    // an alert box pops when clicking the label


    void setAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        //Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        alert.show();
    }

    // handle the choice boxes
    // new windows open when clicking the boxes
    void handlesp(){
        sp1.setOnMouseClicked(event -> {
            try {
                sv.start(newStage1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        newStage1.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                sv = new Sortingvisualize();
            }
        });

        sp2.setOnMouseClicked(event -> {
            try {
            	MainFrame hn = new MainFrame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        newStage2.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
