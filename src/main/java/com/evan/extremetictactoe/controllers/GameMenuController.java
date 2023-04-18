package com.evan.extremetictactoe.controllers;

import com.evan.extremetictactoe.App;
import com.evan.extremetictactoe.Game;
import com.evan.extremetictactoe.board.SubBoard;
import com.evan.extremetictactoe.board.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameMenuController extends Controller {

    @FXML
    Button undoButton; //TODO: Add an undo feature
    @FXML
    Button resetButton;
    @FXML
    Button mainMenuButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane gameScene = (AnchorPane) resetButton.getParent();
        App.getGame().initialize(gameScene);
        if (Game.darkMode.get()) {
            gameScene.setBackground(new Background(new BackgroundFill(new Color(.2,.2,.2,1),null,null)));
            App.getGame().getMainBoard().getMainBoardPane().setGridLinesVisible(false);
            for (SubBoard subBoard : App.getGame().getMainBoard().getSubBoards()) {
                subBoard.getSubBoardPane().setStyle("-fx-border-color : white");
                subBoard.getSubBoardPane().setGridLinesVisible(false);
                for (Tile tile : subBoard.getTiles()) {
                    tile.getTilePane().setStyle("-fx-border-color : white; -fx-border-width : 2");
                }
            }
        }
    }

    //Sets the stage to a new game menu
    public void reset(ActionEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to reset? \n All progress will be lost");
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.CANCEL)) return;

        App.getGame().getMainBoard().getSubBoards().clear();
        App.getGame().resetTurnNumber();
        AnchorPane gameScene = FXMLLoader.load(getClass().getResource("GameMenu.fxml")); //Anchor Pane
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).setScene(new Scene(gameScene));
    }

    public void goToMainMenu(ActionEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to go back to the main menu? \n All progress will be lost");
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.CANCEL)) return;

        App.getGame().getMainBoard().getSubBoards().clear();
        App.getGame().resetTurnNumber();
        AnchorPane gameScene = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).setScene(new Scene(gameScene));
    }

    public void undo(ActionEvent e) {
        //TODO: Add an undo feature
    }
}