/*
This is a class to show how Queue works.
Click "Enter" and "Exit" to see elements
enter and exit the queue in a FIFO way.
 */
package datastruct;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class Queuevisualize extends Application{
    private Queue<Integer> queue = new LinkedList<Integer>();

    void enterqueue(Queue<Integer> q, Integer i)
    {
        q.add(i);
    }

    void exitqueue(Queue<Integer> q)
    {
        q.poll();
    }

    private BorderPane borderPane = new BorderPane();
    private FlowPane centerPane = new FlowPane();
    private FlowPane bottomPane = new FlowPane();
    private Button enter = makeButton("Enter");
    private Button exit = makeButton("Exit");
    private MenuBar menuBar = new MenuBar();
    private Menu helpMenu = new Menu("Help");
    private MenuItem helpItem = new MenuItem("?Help");
    private MenuItem authorItem = new MenuItem("Author");

    @Override
    public void start(Stage primaryStage) throws Exception {
        enter.setOnMouseClicked(this::handleEnterqueue);
        exit.setOnMouseClicked(this::handleExitqueue);
        setTopView();
        setCenterView();
        setBottomView();

        Scene scene = new Scene(borderPane, 800, 400);
        scene.getStylesheets().add("visualizationstyle.css");
        primaryStage.setTitle("QueueVisualization");
        primaryStage.getIcons().add(new Image("file:pic/smile.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void handleEnterqueue(MouseEvent event)
    {
        int number;
        if (queue.size() == 12){
            return;
        }
        while (true)
        {
            Random num = new Random();
            int randomnum = num.nextInt(20);
            if (!queue.contains(randomnum))
            {
                number = randomnum;
                this.enterqueue(queue,randomnum);
                break;
            }
        }

        if (centerPane.getChildren().size() > 0)
        {
            StackPane stackLast = (StackPane) centerPane.getChildren().get(centerPane.getChildren().size() - 1);
            ((Rectangle) stackLast.getChildren().get(0)).setFill(Color.FIREBRICK);
            ((Rectangle) stackLast.getChildren().get(0)).setStroke(Color.LIME);
        }

        addNode(number);


        if (centerPane.getChildren().size() * 51 > (500 - centerPane.getPadding().getLeft()) ||
                centerPane.getChildren().size() == 1)
        {
            centerPane.setPadding(new Insets(25, 45, 25, 45));
        }
    }

    void handleExitqueue(MouseEvent event)
    {
        if (queue.size() == 0){
            return;
        }
        this.exitqueue(queue);

        removeNode();


        if (centerPane.getChildren().size() > 0)
        {
            StackPane stackLast = (StackPane) centerPane.getChildren().get(centerPane.getChildren().size() - 1);
            ((Rectangle) stackLast.getChildren().get(0)).setFill(Color.FIREBRICK);
            ((Rectangle) stackLast.getChildren().get(0)).setStroke(Color.LIME);
        }
    }

    void addNode(int number)
    {
        Rectangle rect = new Rectangle();
        rect.setFill(Color.FIREBRICK);
        rect.setStroke(Color.LIME);
        rect.setWidth(50);
        rect.setHeight(50);

        Text text = new Text();
        text.setText(Integer.toString(number));
        text.getStyleClass().add("textstyle");

        StackPane element = new StackPane();
        element.getChildren().addAll(rect, text);
        centerPane.getChildren().addAll(element);

        FadeTransition ft = new FadeTransition(Duration.millis(200),
                centerPane.getChildren().get(centerPane.getChildren().size()-1));
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    void removeNode()
    {
        FadeTransition ft = new FadeTransition(Duration.millis(500), centerPane.getChildren().get(0));
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setOnFinished(e -> centerPane.getChildren().remove(0));
        ft.play();
    }

    // handle the menubar
    void setTopView(){
        helpItem.setOnAction(actionEvent -> {
            setAlert("About", "Click 'Enter' and 'Exit' to learn how queue works", "help");
        });
        authorItem.setOnAction(actionEvent -> {
            setAlert("AuthorInformation:", "by Hui Yan", "information");
        });
        helpMenu.getItems().addAll(helpItem,new SeparatorMenuItem(), authorItem);
        menuBar.getMenus().addAll(helpMenu);
        borderPane.setTop(menuBar);
    }

    void setCenterView()
    {
        centerPane.setAlignment(Pos.CENTER_LEFT);
        centerPane.setPadding(new Insets(25, 45, 25, 45));
        borderPane.setCenter(centerPane);
    }

    void setBottomView()
    {
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(25, 25, 25, 25));
        bottomPane.setHgap(100);
        enter.getStyleClass().add("button");
        exit.getStyleClass().add("button");
        bottomPane.getChildren().addAll(enter, exit);
        borderPane.setBottom(bottomPane);
    }

    void setAlert(String title, String content, String picture) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        Image smile = new Image("file:pic/" + picture +".png",20,20,false,false);
        ImageView iv = new ImageView(smile);
        alert.setGraphic(iv);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:pic/" + picture + ".png"));
        alert.show();
    }

    private Button makeButton(String str){
        Button button = new Button(str);
        return button;
    }

    private void claim(boolean b){
        if(!b) throw new Error("Queue Test"+testNumber+"fails");
        testNumber++;
    }
    private int testNumber = 1;

    // testing 1-7
    private void run(){
        Queue<Integer> testqueue = new LinkedList<Integer>();
        enterqueue(testqueue,5);
        claim(testqueue.peek() == 5);
        enterqueue(testqueue,10);
        claim(testqueue.peek() == 5);
        enterqueue(testqueue,12);
        claim(testqueue.peek() == 5);
        enterqueue(testqueue,17);
        claim(testqueue.peek() == 5);
        exitqueue(testqueue);
        claim(testqueue.peek() == 10);
        exitqueue(testqueue);
        claim(testqueue.peek() == 12);
        claim(testqueue.size() == 2);
        System.out.println("\nAll Queue Tests Passed!");
    }

    public static void main(String[] args){
        Queuevisualize mqv = new Queuevisualize();
        mqv.run();
    }
}