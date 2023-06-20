package com.evan.extremetictactoe;

import com.evan.extremetictactoe.setting.SettingManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static final Game game = new Game();
    public static Game getGame() {
        return game;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        SettingManager.getDefaultManager().loadAll();
        AnchorPane root = FXMLLoader.load(getClass().getResource("controllers/MainMenu.fxml"));

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Extreme Tic-Tac-Toe");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        stage.show();
    }

}