package com.buaa.service;

import com.buaa.pojo.User;
import com.buaa.view.MainLauncher;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private String name;
    private String userID;
    private String password;

    @FXML
    private Button registerButton;

    @FXML
    private TextField UserName;

    @FXML
    private TextField Password;

    @FXML
    private TextField UserID;

    //需要实现下面方法
    private String getUserID(){
//        registerButton.setOnAction(event -> userID =UserID.getText());
        userID=UserID.getText();
        return userID;
    }

    private String getName() {
//        registerButton.setOnAction(event -> name=UserName.getText());
        name=UserName.getText();
        return name;
        /*需要从文本框获取字段*/
    }

    private String getPassword() {
//        registerButton.setOnAction(event -> password=Password.getText());
        password=Password.getText();
        return password;
        /*需要从文本框获取字段*/
    }

    public void register(ActionEvent actionEvent) throws IOException {
        User user = new User(getName(),getPassword(),getUserID());
        if(!user.isUserIDExisted()){
            logger.info("waiting for you for a long time, {}",name);
            user.register();
            showErrorDialog("注册成功");
        }else{
            logger.info("userID {} has existed!",userID);
            showErrorDialog("user id existed!");
        }
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/loginView.fxml"));
        Platform.runLater(()->{
            Stage stage = MainLauncher.getPrimaryStage();
            stage.setResizable(true);
            stage.setOnCloseRequest((WindowEvent e) -> {
                Platform.exit();
                System.exit(0);
            });
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        });
    }


    public void showErrorDialog(String message) {
        Platform.runLater(()-> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(message);
            alert.showAndWait();
        });
    }



}
