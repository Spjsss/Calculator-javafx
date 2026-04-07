/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator;
import javafx.scene.control.Label;
import java.util.ArrayList;

public class MyCalculator {
    StringBuilder input ;
    Label display_row1;
    Label display_row2;
    boolean isDecimalMode;
    int close_bracket_count;
    ArrayList<Double> operands;
 
    public MyCalculator(Label row1, Label row2){
        display_row1 = row1;
        display_row2 = row2;
        
        input = new StringBuilder();
        input.append("0");
        
        operands = new ArrayList<>();
        
        isDecimalMode = false;
        
        close_bracket_count = 0;
    }
    
    public void input(String str){

        switch (str) {
            case "AC" -> {
                display_row1.setText("");
                
                input.delete(0, input.length());
                input.append("0");
            }
            case "⌫" -> {
                input.deleteCharAt(input.length() - 1);
            }
            case "=" -> {
                if(isOperator(input.toString().charAt(input.length() - 1))){
                    break;
                }
                
                for(int i = 0; i < close_bracket_count; i++){
                    input.append(")");
                }
                display_row1.setText(input.toString());
                
                String output;
                Double ans = calc(input.toString(), operands);
                operands.clear();
                
                if(ans - Math.round(ans) == 0){
                    output = Long.toString((Math.round(ans)));
                }else{
                    output = Double.toString(ans);
                }
                
                input.delete(0, input.length());
                input.append(output);
            }
            case "±" -> {
                negate();
            }
            case "." -> {
                if(!isDecimalMode){
                    isDecimalMode = true;
                    if(isOperator(input.charAt(input.length() - 1))){
                        input.append("0");
                    }
                    input.append(str);
                }
                
            } 
            case "(" -> {
                close_bracket_count++;
                if(input.toString().equals("0")){
                    input.deleteCharAt(0);
                }else if(input.charAt(input.length() - 1) == ')' || Character.isDigit(input.charAt(input.length() - 1) )){
                    input.append("X");
                }
                input.append(str);
            }
            case ")" -> {
                if(input.charAt(input.length() - 1) == '('){
                    break;
                }else if(close_bracket_count <= 0){
                    break;
                }
                close_bracket_count--;
                input.append(str);
            }
            default -> {

                if(input.toString().equals("0")){
                    if(Character.isDigit(str.charAt(0)) || str.equals("-") || str.equals("(")){           
                        input.deleteCharAt(0);
                    }
                    
                }else if(Character.isDigit(str.charAt(0))){
                    if(input.charAt(input.length() - 1) == ')'){
                        input.append("X");
                    }else if(input.charAt(input.length() - 1) == '0'){
                        if(isOperator(input.charAt(input.length() - 2))){
                            input.deleteCharAt(input.length() - 1); 
                        }
                    }
                    
                }else{
                    isDecimalMode = false;
                    if(isOperator(input.charAt(input.length() - 1))){
                        if(isOperator(str.charAt(0), new char[]{'+', 'X', '÷', '%'})){
                            input.deleteCharAt(input.length() - 1);

                        }else if(str.equals("-") && isOperator(input.charAt(input.length() - 1), new char[]{'+', '-'})){
                            input.deleteCharAt(input.length() - 1);

                        } 
                    }else if (input.charAt(input.length() - 1) == '(' && isOperator(str.charAt(0), new char[]{'+', 'X', '÷', '%'})){
                        break;
                    }
                }
                
                input.append(str);
                
            }
        }
        display_row2.setText(input.toString());
    }
    
    public boolean isOperator(char ch){
        return ch == '+' || ch == '-' || ch == 'X' || ch == '÷' || ch == '%';
    }
    
    public boolean isOperator(char ch, char[] operators){
        for(int i = 0; i < operators.length; i++){
            if(ch == operators[i]){
                return true;
            }
        }
        return false;
    }
    
    public double calc(String eq, ArrayList<Double> operands){
        double num = 0;
        boolean isNegative = false, isDecimal = false;
        double decimal_digit = 0;
        char prev_operator = 'n', operator = 'n';
        
        for(int i = 0; i < eq.length(); i++){
            
            if(Character.isDigit(eq.charAt(i))){
                if(isDecimal){
                    num = num + Double.parseDouble(Character.toString(eq.charAt(i))) * Math.pow(10, -++decimal_digit);
                }else{
                    num = num * 10 + Double.parseDouble(Character.toString(eq.charAt(i)));
                }
                
            }else if(eq.charAt(i) == '.'){
                isDecimal = true;
                
            }else if(eq.charAt(i) == '(') {
                if(eq.charAt(i + 1) == ')'){
                    prev_operator = 'n';
                }else{
                    num = calc(eq.substring(i + 1, pos_closingBracket(eq)), new ArrayList<Double>());
                }
                
                i = pos_closingBracket(eq);  
                
            }else{ 
                operator = eq.charAt(i);
                
                if(i > 0){
                    if((eq.charAt(i - 1) == 'X' || eq.charAt(i - 1) == '÷' || eq.charAt(i - 1) == '%') && operator == '-'){
                        isNegative = true;
                        continue;
                    }
                }
               
                if(isNegative){
                    num = -num;
                }
                operands.addLast(num);                
                isNegative = false;
                isDecimal = false;
                num = 0;
                decimal_digit = 0;
                
                if(operator == '-'){
                    isNegative = true;
                }

                if(prev_operator == 'X' || prev_operator == '÷' || prev_operator == '%'){
                    mul_div(prev_operator, operands);
                }
                
               prev_operator = operator;
            }
        }
        if(isNegative){
            num = -num;
        }
        operands.addLast(num);
        if(prev_operator == 'X' || prev_operator == '÷' || prev_operator == '%'){
            mul_div(prev_operator, operands);
        }
        
        while(operands.size() != 1){
            operands.addLast(operands.removeLast() + operands.removeLast());
        }
        
        return operands.getLast();
    }
    
    public int pos_closingBracket(String eq){
        int i;
        for(i = eq.length() - 1; i >= 0; i-- ){
            if(eq.charAt(i) == ')'){
                break;
            }
        }
        return i;
    }
    public void mul_div(Character operator, ArrayList<Double> operands){
        double num2 = operands.removeLast(); 
        double num1 = operands.removeLast();
    
        switch (operator) {
            case 'X' -> operands.addLast(num1 * num2);
            case '÷' -> operands.addLast(num1 / num2);
            case '%' -> operands.addLast(num1 % num2);
            default -> {
            }
        }
    }
    
    public void negate(){
        if(input.toString().equals("0")){
            return;
        }
        
        int i;
        for(i = input.length() - 1; i >= 0; i--){
            if(isOperator(input.charAt(i))){
                break;
            }
        }
        
        if(i >= 0){
            if(input.substring(i + 1, input.length()).equals("0")){
                return;
            }
            
            if(input.charAt(i) == '-'){
                if(i >= 1){
                    if(input.charAt(i - 1) == '('){
                        input.deleteCharAt(i);
                        input.deleteCharAt(i - 1);
                        if(input.charAt(input.length() - 1) == ')'){
                            input.deleteCharAt(input.length() - 1);
                        }
                    }else{
                        input.deleteCharAt(i);
                        input.insert(i, '+');
                    }
                    return;
                }
                input.deleteCharAt(i);  
                return;
            }
        }
        
        input.insert(i + 1, "(-");
        input.append(")");
    }
}



