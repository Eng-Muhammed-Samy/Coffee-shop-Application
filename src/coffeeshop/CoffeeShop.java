
package coffeeshop;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CoffeeShop extends Application {
    // text uesd to depend number of Muffins at the same order
    static TextField txt1 = new TextField();
    // used to select type od Muffins
    static RadioButton blueberry = new RadioButton("blueberry");
    static RadioButton chocolate = new RadioButton("chocolate chip");
    static RadioButton banana = new RadioButton(" banana nut ");
    static RadioButton bran = new RadioButton("bran");
    // used to calculate total and tax cost
    Button calc = new Button("Calculate");
    // used to select type of coffee
    static RadioButton none = new RadioButton("none");
    static RadioButton regular = new RadioButton("Regular coffee");
    static RadioButton decaf = new RadioButton("Decaf coffee");
    static RadioButton capp = new RadioButton("Cappuccino");
    // used to select type of Toppings
    static CheckBox cream = new CheckBox("Cream");
    static CheckBox sugar = new CheckBox("sugar");
    static CheckBox  artificial = new CheckBox(" artificial sweetener");
    static CheckBox cinnamon = new CheckBox("cinnamon");
    static CheckBox caramel = new CheckBox("caramel");
    // price of muffins, adding, coffee
    float costOfmuffins = 2.25f, costOfadding = 0.25f, costOfCoffee = 3.0f;
    // to svae price of muffins , adding , coffee and total cost
    float calcmuffins , calcadding, calcCoffee , suptotal,tax, total = 0.0f;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Order Calculator");// set title 
        primaryStage.setScene(mainScene()); // add main scene
        primaryStage.show(); // stage showing 

    }


    public static void main(String[] args) {
        launch(args);// start application
    }
    
    public Scene mainScene(){
        //used to contain all nodes
        BorderPane root = new BorderPane();
        //set welcom label and add properties from it
        Label lbl = new Label("Welcom to Brandi's Bagel House");
        lbl.setFont(new Font(20));
        lbl.setMinSize(250, 50);
        lbl.setPadding(new Insets(5, 80, 10, 80));
        // used to contain buttons calc, exit
        HBox box = new HBox(); 
        Calcultator c = new Calcultator();// event handdeler of calc button
        calc.setOnAction(c);// add action
        // event action os exit button
        Button exit = new Button("Exit");
        exit.setOnAction((event) -> {
            System.exit(0);
        });
        // add properties to hbox and add elements to it
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(calc, exit);
        //add some properties to action buttons
        calc.setMinSize(100, 50);
        exit.setMinSize(80, 50);
        calc.setFont(new Font(20));
        exit.setFont(new Font(20));
        calc.setPadding(new Insets(0, 0, 0, 10));
        // add all node to main container
        root.setTop(lbl);
        root.setCenter(order());
        root.setBottom(box);
        root.setPadding(new Insets(10));
        // return new scene 
        return new Scene(root, 470, 330);
    }
    
    public HBox order(){
        VBox v1 = new VBox();// container
        ToggleGroup t1 = new ToggleGroup();
         // add radiobuttons to togglegroup
        blueberry.setToggleGroup(t1);
        chocolate.setToggleGroup(t1);
        banana.setToggleGroup(t1);
        bran.setToggleGroup(t1);
        Label lbl1 = new Label("Muffins");// show type of muffins to user
        lbl1.setPadding(new Insets(0, 0, 20, 0));//add padding to label
         // add children to vbox1
        v1.getChildren().addAll(lbl1, blueberry, chocolate, banana, bran, txt1);
        VBox v2 = new VBox();
        Label lbl = new Label("Toppings");// show type of toppings to user
        lbl.setPadding(new Insets(0, 0, 20, 0));//add padding to label
         // add children to vbox2
        v2.getChildren().addAll(lbl, cream, sugar, artificial, cinnamon, caramel);
        VBox v3 = new VBox();
        ToggleGroup t2 = new ToggleGroup();
        // add radiobuttons to togglegroup
        none.setToggleGroup(t2);
        regular.setToggleGroup(t2);
        decaf.setToggleGroup(t2);
        capp.setToggleGroup(t2);
        Label lbl2 = new Label("Coffee");// show type of coffee to user
        lbl2.setPadding(new Insets(0, 0, 20, 0));//add padding to label
        // add children to vbox3
        v3.getChildren().addAll(lbl2, none, regular, decaf, capp);
        // set border to all boxs
        v1.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(1))));
        v2.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(1))));
        v3.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(1))));
       //set width and height to vboxs
        v1.setMinSize(150, 200);
        v1.setMaxSize(150, 200);
        v2.setMinSize(150, 200);
        v2.setMaxSize(150, 200);
        v3.setMinSize(150, 200);
        v3.setMaxSize(150, 200);
        // add padding to vboxs
        v1.setPadding(new Insets(15));
        v2.setPadding(new Insets(15));
        v3.setPadding(new Insets(15));
        // add spacing between elements
        v1.setSpacing(10);
        v2.setSpacing(10);
        v3.setSpacing(10);
        //return all vbox in hbox container
        return new HBox(v1, v2, v3);
    }
    class Calcultator implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            // check all muffins type radiobuttons and calculate price of select type
          if(blueberry.isSelected()||chocolate.isSelected()||banana.isSelected()||
                 bran.isSelected()){
               int num = Integer.parseInt(txt1.getText());
              calcmuffins = num * costOfmuffins ;
          }
           // check all coffee type radiobuttons and calculate price of select type
          if(cream.isSelected()){calcadding += costOfadding;}
          if(sugar.isSelected()){calcadding += costOfadding;}
          if(artificial.isSelected()){calcadding += costOfadding;}
          if(cinnamon.isSelected()){calcadding += costOfadding;}
          if(caramel.isSelected()){calcadding += costOfadding;}
            // check all adding radiobuttons and calculate price of it   
          if(none.isSelected()){calcCoffee = 0.0f;}
          if(regular.isSelected() || decaf.isSelected() || capp.isSelected()){    
              calcCoffee = costOfCoffee;
        } 
          // calculate total cost without tax
          suptotal = calcCoffee + calcadding + calcmuffins ;
          //calculate tax
          tax = suptotal * 0.07f;
          // calculate total cost
          total = suptotal + tax;
          //show the cost to user
          stage();
        }
        
    }
    // message stage
 public void stage(){
     // add 3 labels to show from user subtotal , tax and total
     // and set some properties from each one.
        Label lbl1 = new Label("Subtotal");
        lbl1.setPadding(new Insets(5));
        lbl1.setBorder(new Border(new BorderStroke(Color.DARKBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(0.5))));
        lbl1.setFont(new Font(20));
        lbl1.setMinSize(100, 50);
        Label lbl3 = new Label("Tax");
        lbl3.setPadding(new Insets(5));
        lbl3.setBorder(new Border(new BorderStroke(Color.DARKBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(0.5))));
        lbl3.setFont(new Font(20));
        lbl3.setMinSize(100, 50);
        Label lbl5 = new Label("Total");
        lbl5.setPadding(new Insets(5));
        lbl5.setBorder(new Border(new BorderStroke(Color.DARKBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(0.5))));
        lbl5.setFont(new Font(20));
        lbl5.setMinSize(100, 50);
        // add 3 labels to show all results
     // and set some properties from each one.
        Label lbl2 = new Label("$"+String.valueOf(suptotal));
        lbl2.setPadding(new Insets(5));
        lbl2.setBorder(new Border(new BorderStroke(Color.CRIMSON, BorderStrokeStyle.SOLID, null, new BorderWidths(0.5))));
        lbl2.setFont(new Font(20));
        lbl2.setMinSize(100, 50);
        Label lbl4 = new Label("$"+String.valueOf(tax));
        lbl4.setPadding(new Insets(5));
        lbl4.setBorder(new Border(new BorderStroke(Color.CRIMSON, BorderStrokeStyle.SOLID, null, new BorderWidths(0.5))));
        lbl4.setFont(new Font(20));
        lbl4.setMinSize(100, 50);
        Label lbl6 = new Label("$"+String.valueOf(total));
        lbl6.setPadding(new Insets(5));
        lbl6.setBorder(new Border(new BorderStroke(Color.CRIMSON, BorderStrokeStyle.SOLID, null, new BorderWidths(0.5))));
        lbl6.setFont(new Font(20));
        lbl6.setMinSize(100, 50);
        // add pane to add all labels to it
        GridPane p = new GridPane();
        p.add(lbl1, 0, 0);
        p.add(lbl2, 1, 0);
        p.add(lbl3, 0, 1);
        p.add(lbl4, 1, 1);
        p.add(lbl5, 0, 2);
        p.add(lbl6, 1, 2); 
        // set some propeties to root 
        p.setVgap(10);
        p.setHgap(10);
        p.setAlignment(Pos.CENTER);
        // add new scene
        Scene sc = new Scene(p, 220, 200);
        // add another stage and set it some properties
        Stage ss= new Stage();
        ss.setTitle("Message");
        ss.setScene(sc);
        ss.show();
}
}
