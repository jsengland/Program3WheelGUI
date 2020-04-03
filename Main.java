package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.event.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.Optional;
import java.util.Random;

/*
 *Program3: Wheel GUI
 *@author: Jason England
 * @version: 4/2/20
 */
public class Main extends Application {
    String nameOfWheel = "Wheel";
    TextField wheelName = new TextField(nameOfWheel);
    MyList wheelList = new MyList();
    MyList wheelListCopy = new MyList();
    Random rand = new Random();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Wheel of Power");

        //Making List window - Found most of this section online at https://examples.javacodegeeks.com/desktop-java/javafx/listview-javafx/javafx-listview-example/
        ObservableList<String> wheelItemsList = FXCollections.<String>observableArrayList();
        // Create the ListView for the seasons
        ListView<String> viewItems = new ListView<>(wheelItemsList);
        // Set the Orientation of the ListView
        viewItems.setOrientation(Orientation.VERTICAL);
        // Set the Size of the ListView
        viewItems.setPrefSize(120, 100);


        //buttons
        Button addBtn = new Button();
        addBtn.setText("Add");
        addBtn.setMaxWidth(80);
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("ADD");
                dialog.setContentText("Please enter a name to add: ");

                Optional<String> result = dialog.showAndWait();
                if(result.isPresent()){
                    wheelList.addToEnd(result.get());
                    wheelListCopy.addToEnd(result.get());
                    wheelItemsList.add(result.get());
                }
            }
        });


        Button nameBtn = new Button();
        nameBtn.setText("Name");
        nameBtn.setMaxWidth(80);
        nameBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog1 = new TextInputDialog();
                dialog1.setTitle("NAME");
                dialog1.setContentText("Please enter a new name for the wheel: ");

                Optional<String> result1 = dialog1.showAndWait();
                if(result1.isPresent()){
                    nameOfWheel = result1.get();
                    wheelName.setText(nameOfWheel);
                }
            }
        });


        Button spinBtn = new Button();
        spinBtn.setText("Spin");
        spinBtn.setMaxWidth(80);
        spinBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int num;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if(wheelList.numItems() == 1){
                    num = 1;
                }
                else {
                    num = rand.nextInt(wheelList.numItems() - 1) + 1;
                }
                System.out.println(num);

                alert.setTitle("Spin Wheel Result");
                alert.setHeaderText("You spun the wheel and this is what you got!");
                alert.setContentText(wheelList.get(num));
                alert.showAndWait();
                wheelList.remove(num);
                wheelItemsList.remove(num-1);
            }
        });


        Button reloadBtn = new Button();
        reloadBtn.setText("Reload");
        reloadBtn.setMaxWidth(80);
        reloadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                wheelList = new MyList(wheelListCopy);
                wheelItemsList.clear();
                for(int i = 1; i <= wheelList.numItems(); i++){
                    wheelItemsList.add(wheelList.get(i));
                }
            }
        });


        Button reverseBtn = new Button();
        reverseBtn.setText("Reverse");
        reverseBtn.setMaxWidth(80);
        reverseBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                wheelList.reverse();
                wheelItemsList.clear();
                for(int i = 1; i <= wheelList.numItems(); i++){
                    wheelItemsList.add(wheelList.get(i));
                }
            }
        });


        Button clearBtn = new Button();
        clearBtn.setText("Clear");
        clearBtn.setMaxWidth(80);
        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                wheelList.clear();
                wheelListCopy.clear();
                wheelItemsList.clear();
            }
        });


        Button reportBtn = new Button();
        reportBtn.setText("Report");
        reportBtn.setMaxWidth(80);
        reportBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Report");
                alert.setHeaderText(null);
                String items = "items: ";
                for(int i = 1; i <= wheelList.numItems(); i++) {
                    items+= (wheelList.get(i) + " ");
                }
                alert.setContentText("num items: " + wheelList.numItems() + "\nfirst item: " + wheelList.getFirst() + "\nlast item: " + wheelList.getLast() + "\n" + items);
                alert.showAndWait();
            }
        });


        Button exitBtn = new Button();
        exitBtn.setText("EXIT");
        exitBtn.setMaxWidth(80);
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });


        //VBoxes for each column of buttons
        VBox col1 = new VBox(addBtn, reloadBtn, clearBtn, reportBtn);
        col1.setSpacing(20);
        col1.setAlignment(Pos.BASELINE_CENTER);

        VBox col2 = new VBox(nameBtn, spinBtn, reverseBtn, exitBtn);
        col2.setSpacing(20);
        col2.setAlignment(Pos.BASELINE_CENTER);


        //VBox of the wheel column
        VBox wheel = new VBox();
        wheel.setSpacing(5);
        //TextField wheelName = new TextField(nameOfWheel);
        wheelName.setEditable(false);
        wheel.getChildren().add(wheelName);
        wheel.getChildren().add(viewItems);


        //blank VBox for spacing
        VBox blankCol = new VBox();


        //HBox of everything
        HBox hBox = new HBox(wheel, blankCol, col1, col2);
        hBox.setSpacing(20);


        //setting epic image as background of window
        Image kingKrieger = new Image("sample/krieger.png");
        ImageView iv = new ImageView(kingKrieger);
        //iv.setImage(kingKrieger);
        iv.setFitWidth(350);
        iv.setFitHeight(200);


        //printing the window with everything on it
        StackPane root = new StackPane();
        root.getChildren().add(iv);
        root.getChildren().add(hBox);
        primaryStage.setScene(new Scene(root, 350, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
