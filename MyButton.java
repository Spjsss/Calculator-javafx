/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator;

import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
public class MyButton extends Button{
    public MyButton(String text, String color, MyCalculator calc){
        super(text);
        this.setShape(new Circle(30));     // radius = 30
        this.setMinSize(60, 60);           // size = 60x60
        this.setMaxSize(60, 60);

        this.setStyle(
        ("-fx-background-color: " + color + "; " + //fill color
        "-fx-background-radius: 50em; " +   // round corners
        "-fx-text-fill: white; " +          // text color
        "-fx-font-size: 21px; ")            // font size
       // "-fx-font-weight: bold;"            // bold text
        );
        
        this.setOnAction(event ->{
            calc.input(text);
        });
        
    }
}
