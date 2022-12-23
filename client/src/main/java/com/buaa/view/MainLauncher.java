package com.buaa.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainLauncher extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage=stage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/loginView.fxml"));
//        FXMLLoader fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("views/loginView.fxml"));
        //使用上面方式加载会出现问题，可能是因为fxml使用了在线背景的原因
        primaryStage.setTitle("login");

        Scene scene = new Scene(root);
        scene.setRoot(root);
//        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    public static void main(String[] args) {
        launch(args);
    }


    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
