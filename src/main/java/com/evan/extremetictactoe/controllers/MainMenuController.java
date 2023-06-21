package com.evan.extremetictactoe.controllers;

import com.ejo.glowlib.setting.SettingManager;
import com.evan.extremetictactoe.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends Controller {


    @FXML
    AnchorPane background;
    @FXML
    Text title;
    @FXML
    Button playButton;
    @FXML
    TextField player1Name;
    @FXML
    Button player1Avatar;
    @FXML
    ImageView player1AvatarImage;
    @FXML
    TextField player2Name;
    @FXML
    Button player2Avatar;
    @FXML
    ImageView player2AvatarImage;
    @FXML
    RadioButton lightMode;
    @FXML
    RadioButton darkMode;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane gameScene = background;

        player1Name.setText(Game.player1Name.get());
        player2Name.setText(Game.player2Name.get());
        if (Game.player1Avatar.get() != null) {
            player1AvatarImage.setImage(Game.player1Avatar.get());
        } else {
            try {
                player1AvatarImage.setImage(new Image(Game.player1AvatarPath.get()));
            } catch (Exception e) {
                //
            }
        }
        if (Game.player2Avatar.get() != null) {
            player2AvatarImage.setImage(Game.player2Avatar.get());
        } else {
            try {
                player2AvatarImage.setImage(new Image(Game.player2AvatarPath.get()));
            } catch (Exception e) {
                //
            }
        }

        if (Game.darkMode.get()) {
            darkMode.setSelected(true);
            gameScene.setBackground(new Background(new BackgroundFill(new Color(.2, .2, .2, 1), null, null)));
            ((Text) gameScene.getChildren().get(0)).setFill(new Color(1, 1, 1, 1));
        }
    }


    //Sets the stage to the game menu
    public void onPlayButton(ActionEvent e) throws IOException {
        AnchorPane gameScene = FXMLLoader.load(getClass().getResource("GameMenu.fxml")); //Anchor Pane
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).setScene(new Scene(gameScene));
        SettingManager.getDefaultManager().saveAll();
    }

    public void onPlayer1Name(KeyEvent e) {
        Game.player1Name.set(player1Name.getText());
    }

    public void onPlayer2Name(KeyEvent e) {
        Game.player2Name.set(player2Name.getText());
    }

    public void onPlayer1Avatar(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog((((Node) e.getSource()).getScene().getWindow()));
        String imagePath = selectedFile.getPath();
        player1AvatarImage.setImage(new Image(imagePath));
        Game.player1AvatarPath.set(imagePath);
        Game.player1Avatar.set(new Image(imagePath));
    }

    public void onPlayer2Avatar(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog((((Node) e.getSource()).getScene().getWindow()));
        String imagePath = selectedFile.getPath();
        player2AvatarImage.setImage(new Image(imagePath));
        Game.player2AvatarPath.set(imagePath);
        Game.player2Avatar.set(new Image(imagePath));
    }


    public void setTheme(ActionEvent e) {
        AnchorPane gameScene = (AnchorPane) ((Node) e.getSource()).getScene().getRoot();
        if (lightMode.isSelected()) {
            Game.darkMode.set(false);
            gameScene.setBackground(new Background(new BackgroundFill(new Color(1, 1, 1, 1), null, null)));
            title.setFill(new Color(0, 0, 0, 1));
        }

        if (darkMode.isSelected()) {
            Game.darkMode.set(true);
            gameScene.setBackground(new Background(new BackgroundFill(new Color(.2, .2, .2, 1), null, null)));
            title.setFill(new Color(1, 1, 1, 1));
        }
    }
}