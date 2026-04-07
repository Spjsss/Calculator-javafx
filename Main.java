/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {


    @Override
    public void start(Stage stage) {
        
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(root, 290, 545);
        stage.setTitle("Calculator");
        
        VBox buttonPlane = new VBox(4);
        Label display_row1 = new Label();
        Label display_row2 = new Label();   
        root.getChildren().addAll(display_row1, display_row2, buttonPlane);
        
        AnchorPane.setTopAnchor(display_row1, 20.0);
        AnchorPane.setBottomAnchor(display_row1, 450.0);
        AnchorPane.setLeftAnchor(display_row1, 20.0);
        AnchorPane.setRightAnchor(display_row1, 20.0);
        
        
        AnchorPane.setTopAnchor(display_row2, 80.0);
        AnchorPane.setBottomAnchor(display_row2, 390.0);
        AnchorPane.setLeftAnchor(display_row2, 20.0);
        AnchorPane.setRightAnchor(display_row2, 20.0);
        
        
        AnchorPane.setTopAnchor(buttonPlane, 150.0);
        AnchorPane.setBottomAnchor(buttonPlane, 20.0);
        AnchorPane.setLeftAnchor(buttonPlane, 20.0);
        AnchorPane.setRightAnchor(buttonPlane, 20.0);
        
        display_row2.setStyle(
            "-fx-background-color: black;" +
            "-fx-font-size: 40px;" +
            "-fx-text-fill: white;" +
            "-fx-padding: 10px;" +
            //"-fx-font-weight: bold;" +
            "-fx-alignment: bottom-right;"
        );
        display_row2.setText("0");
        display_row2.setMaxHeight(60);
        display_row2.setMinHeight(60); 
        display_row2.setMaxWidth(Double.MAX_VALUE);
        
        display_row1.setStyle(
            "-fx-background-color: black;" +
            "-fx-font-size: 20px;" +
            "-fx-text-fill: white;" +
            "-fx-padding: 10px;" +
            //"-fx-font-weight: bold;" +
            "-fx-alignment: bottom-right;"
        );
        
        
        buttonPlane.setMinHeight(200);
        buttonPlane.setMaxHeight(200);
        
        
        display_row1.setMinHeight(40); 
        display_row1.setMaxHeight(40);
        display_row1.setMaxWidth(Double.MAX_VALUE);
        MyCalculator calc = new MyCalculator(display_row1, display_row2);
        
        //row 1
        HBox row1 = new HBox(4);
        MyButton btn_clear = new MyButton("AC", "darkgray", calc);
        MyButton btn_sign = new MyButton("±", "darkgray", calc);
        MyButton btn_modulus = new MyButton("%", "darkgray", calc); 
        MyButton btn_division = new MyButton("÷", "orange", calc);
        row1.getChildren().addAll(btn_clear, btn_sign, btn_modulus, btn_division);
       
        
        //row 2
        HBox row2 = new HBox(4);
        MyButton btn_7 = new MyButton("7", "dimgray", calc);
        MyButton btn_8 = new MyButton("8", "dimgray", calc);
        MyButton btn_9 = new MyButton("9", "dimgray", calc);
        MyButton btn_multiplication = new MyButton("X", "orange", calc);
        row2.getChildren().addAll(btn_7, btn_8, btn_9, btn_multiplication);
        
        //row3
        HBox row3 = new HBox(4);
        MyButton btn_4 = new MyButton("4", "dimgray", calc);
        MyButton btn_5 = new MyButton("5", "dimgray", calc);
        MyButton btn_6 = new MyButton("6", "dimgray", calc);
        MyButton btn_substraction = new MyButton("-", "orange", calc);
        row3.getChildren().addAll(btn_4, btn_5, btn_6, btn_substraction);
        
        //row4
        HBox row4 = new HBox(4);
        MyButton btn_1 = new MyButton("1", "dimgray", calc);
        MyButton btn_2 = new MyButton("2", "dimgray", calc);
        MyButton btn_3 = new MyButton("3", "dimgray", calc);
        MyButton btn_addition = new MyButton("+", "orange", calc);
        row4.getChildren().addAll(btn_1, btn_2, btn_3, btn_addition);
        
        //row5
        HBox row5 = new HBox(4);
        MyButton btn_cancel = new MyButton("⌫", "dimgray", calc);
        MyButton btn_0 = new MyButton("0", "dimgray", calc);
        MyButton btn_decimal = new MyButton(".", "dimgray", calc);
        MyButton btn_equal = new MyButton("=", "orange", calc);
        row5.getChildren().addAll(btn_cancel, btn_0, btn_decimal, btn_equal);
        
        //row6
        HBox row6 = new HBox(4);
        MyButton btn_open_bracket = new MyButton("(", "dimgray", calc);
        MyButton btn_close_bracket = new MyButton(")", "dimgray", calc);
        row6.getChildren().addAll(btn_open_bracket, btn_close_bracket);
        

        buttonPlane.getChildren().addAll(row1, row2, row3, row4, row5, row6);
        
        stage.setScene(scene);
        stage.show();
        
    }
    
   
    public static void main(String[] args) {
        launch();
    }
}