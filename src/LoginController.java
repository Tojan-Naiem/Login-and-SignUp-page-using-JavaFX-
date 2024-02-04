package com.example.loginproject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LoginController {


    @FXML
    private Button signUpButton;

    @FXML
    private TextField userNameLabel;
    @FXML
    private Label warningLabel;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordLabel;

    @FXML
    void LoginButtonClick(ActionEvent event) {
        warningLabel.setText("");
        if(event.getSource()==loginButton)
        {
            if(userNameLabel.getText().equals("")){
                warningLabel.setText("Please Enter Your Email !");
            }
            else if(passwordLabel.getText().equals(""))
            {
                warningLabel.setText("Please Enter Your Password !");
            }
            else {


                File file = new File("Account.txt");

                try {
                    Scanner readFromFile = new Scanner(file);
                    int flag = 0;
                    while (readFromFile.hasNextLine()) {
                        String userName = readFromFile.nextLine();
                        String password = readFromFile.nextLine();
                        if (userNameLabel.getText().equals(userName) && passwordLabel.getText().equals(password)) {
                            flag++;
                            break;
                        }
                        if (userNameLabel.getText().equals(userName) && !passwordLabel.getText().equals(password)) {
                            warningLabel.setText("Your Password in un correct");
                            flag++;
                            break;
                        }
                    }
                    if (flag == 0) warningLabel.setText("Your Account has not found , please sign up ");
                }catch (Exception e){
                    System.out.println("Error in the file");
                    e.printStackTrace();
                }
            }

        }
        else if(event.getSource()==signUpButton){
            Stage currentStage = (Stage) signUpButton.getScene().getWindow();
            currentStage.close();
            Stage stage=new Stage();
            Parent root;
            try {
                root= FXMLLoader.load(getClass().getResource("signUp.fxml"));
                Scene scene = new Scene(root, 778, 545);
                stage.setScene(scene);
                stage.setTitle("Sign up page ");
                stage.show();
            }catch (Exception e){
             e.printStackTrace();
            }



        }

    }

}

