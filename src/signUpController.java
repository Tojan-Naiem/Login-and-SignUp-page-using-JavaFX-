package com.example.loginproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.io.FileWriter;
import java.io.PrintWriter;

public class signUpController {
    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private Label warningLabel;


    @FXML
    void signUpEvent(ActionEvent event) {
        warningLabel.setText("");
        warningLabel.setTextFill(Color.RED);
        if(event.getSource()==loginButton){
            if(!(firstName.getText().equals("")&&lastName.getText().equals("")&&email.getText().equals("")
                    &&password.getText().equals("")&&confirmPassword.getText().equals(""))){

                if(!(password.getText().equals(confirmPassword.getText()))){
                    warningLabel.setText("Password does not meet requirement");
                }
                else if(!checkEmail(email.getText())){
                    warningLabel.setText("Email is not correct ");
                }
                else {
                    try {
                        PrintWriter pr = new PrintWriter(new FileWriter("Account.txt",true));
                        pr.println(firstName.getText()+" "+lastName.getText());
                        pr.println(password.getText());
                        pr.close();
                    }catch (Exception e){
                        System.out.println("The file does not exist");
                    }
                }

            }
            else
            {
             warningLabel.setText("Please check if there's something empty ");
            }
        }



    }
    public  void passwordFiledEvent(KeyEvent e){
        if(e.getSource()==this.password) {
            if (!checkPassword(password.getText())) {
                warningLabel.setText("Weak Password ");
            } else {
                warningLabel.setTextFill(Color.GREEN);
                warningLabel.setText("Strong Password ");
            }
        }
    }
    public Boolean checkEmail(String email){
        int z=0,count=0,x=0,k=0;
        String domain[]={"com" , "edu"," org" , "net" ,"gov" ,"mil" , "ps", "jo", "us"};
        for(int i=0;i<email.length();i++)
        {

            if(z==0&&((email.charAt(i)>=65&&email.charAt(i)<=90)||(email.charAt(i)>=97&&email.charAt(i)<=122))){
                count++;

            }
            else if(z==0&&(email.charAt(i)=='@'&&count>=1)){
                z++;
                count=0;
            }
            else if((z==0&&(email.charAt(i)=='@'&&count==0)))
            {
                k++;break;
            }
            else if(z==1&&((email.charAt(i)>=65&&email.charAt(i)<=90)||(email.charAt(i)>=97&&email.charAt(i)<=122))){
                count++;
                if(x==0){
                    z++;x=1;
                }
            }
            else if(z==2&&(email.charAt(i)=='.'&&count>=1)){
                z++;
                count=0;
            }
            else if(z==3){

                for(int j=0;j<domain.length;j++)
                {
                    if(email.substring(i).equals(domain[j])){
                        return true;
                    }
                }
            }

        }
        return false;
    }
    public boolean checkPassword(String password)
    {
        int character=0,upper=0,lower=0,number=0,special=0;
        for(int i=0;i<password.length();i++)
        {
            if(password.charAt(i)>=65&&password.charAt(i)<=90){
                character++;
                upper++;
            }
            else if(password.charAt(i)>=97&&password.charAt(i)<=122)
            {
                character++;
                lower++;
            }
            else if(password.charAt(i)>=48&&password.charAt(i)<=57){
                number++;
                character++;
            }
            else{
                special++;
            }
        }
        if(character>=5&&upper>=2&&lower>=2&&number>=2&&special>=2)
            return true;
        return false;

    }


}
