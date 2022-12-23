package com.buaa.service;

import com.buaa.pojo.*;
//import com.buaa.util.Client;
import com.buaa.util.Listener;
import com.buaa.view.MainLauncher;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private String userID;
    private String name;
    private String password;
    @FXML
    private Button RegisterButton;

    @FXML
    private TextField UserID;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField Password;


    public static ChatController con;

    private static LoginController instance;

    public LoginController() {
        instance = this;
    }

    public static LoginController getInstance() {
        return instance;
    }

    //需要实现下面方法
    private String getUserID() {//获取昵称
//        LoginButton.setOnAction(event -> userID =UserID.getText());
        userID = UserID.getText();
        return userID;
        /*需要从文本框获取字段*/
    }

    private String getPassword() {//获取密码
//        LoginButton.setOnAction(event -> password=Password.getText());
        password = Password.getText();
        return password;
        /*需要从文本框获取字段*/
    }


    public void register(ActionEvent actionEvent) throws IOException {//点击注册按钮进入注册界面
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/registerView.fxml"));
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

    public void login(ActionEvent actionEvent) throws IOException {
        User user = new User(getUserID(),getPassword());
//        logger.info("{} {}",userID,password);
        if(user.isUserIDAndPasswordExisted()){//如果账号密码匹配
            logger.info("welcome, {} {}",user.getUserID(),user.getName());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/chatView.fxml"));
            Parent root = fxmlLoader.load();
            con = fxmlLoader.<ChatController>getController();
            con.setUsernameLabel(userID);
            Listener listener = new Listener(user.getName(),con);
            Thread thread = new Thread(listener);
            thread.start();
            Platform.runLater(()->{
                Stage stage = MainLauncher.getPrimaryStage();
                stage.setTitle("chat");
                stage.setResizable(true);
                stage.setOnCloseRequest((WindowEvent e) -> {
                    Platform.exit();
                    System.exit(0);
                });
                stage.setScene(new Scene(root));
                stage.centerOnScreen();
            });
            /*待实现的操作*/
        }
        else {
            logger.info("user not existed");
            showErrorDialog("userID wrong or password wrong!");
        }
    }



    public void showErrorDialog(String message) {
        Platform.runLater(()-> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(message);
            alert.showAndWait();
        });
    }
//    public void UserName(ActionEvent actionEvent) {
//    }
//
//    public void Password(ActionEvent actionEvent) {
//    }
}
