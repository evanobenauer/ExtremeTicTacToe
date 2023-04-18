package com.evan.extremetictactoe.controllers;

import com.evan.extremetictactoe.App;
import com.evan.extremetictactoe.Game;
import com.evan.extremetictactoe.board.MainBoard;
import com.evan.extremetictactoe.board.Piece;
import com.evan.extremetictactoe.board.SubBoard;
import com.evan.extremetictactoe.board.Tile;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Actions {

    public static void setTurnIndicator(Piece.Team team) {
        App.getGame().getTurnIndicatorImage().setTeam(team);
        App.getGame().getTurnIndicatorImage().setX(4);
        App.getGame().getTurnIndicatorImage().setY(22);

        App.getGame().getTurnIndicatorText().setText("Turn: " + (team == Piece.Team.X ? Game.player1Name.get() : Game.player2Name.get()));
        App.getGame().getTurnIndicatorText().setX(4);
        App.getGame().getTurnIndicatorText().setY(20);
        App.getGame().getTurnIndicatorText().setFill(Game.darkMode.get() ? new Color(1,1,1,1) : new Color(0,0,0,1));
        App.getGame().getTurnIndicatorText().setFont(new Font(20));
    }

    public static void setBoardHighlights(SubBoard targetSubBoard) {
        //Reset Highlights
        for (SubBoard subBoard : App.getGame().getMainBoard().getSubBoards())
            subBoard.getHighlightPane().getChildren().clear();

        //Set Highlights
        if (!targetSubBoard.isWon() && !targetSubBoard.isStaleMate()) {
            targetSubBoard.setHighlighted(true);
        } else {
            for (SubBoard subBoard : App.getGame().getMainBoard().getSubBoards()) {
                if (!subBoard.isWon() && !subBoard.isStaleMate()) {
                    subBoard.setHighlighted(true);
                }
            }
        }
    }

    public static void placeNewPiece(Tile tile, Piece.Team team) {
        try {
            Piece piece = new Piece(tile, team);
            if (!tile.containsPiece()) {
                piece.place();
                App.getGame().nextTurn();
            }
        } catch (Exception e) {
            //Can't place the piece
        }
    }

    public static void setSubBoardWonGraphic(SubBoard subBoard, Piece.Team winTeam) {
        Pane anchor = new Pane();
        subBoard.getSubBoardPane().getChildren().add(anchor);

        Piece bigPiece = new Piece(winTeam);
        bigPiece.setFitHeight(111);
        bigPiece.setFitWidth(111);
        bigPiece.setOpacity(.75f);

        float width = 3;
        Line winLine = new Line();
        winLine.setStroke(Color.RED);
        winLine.setStrokeWidth(width);
        Tile startTile = subBoard.getWinTiles()[0];
        Tile endTile = subBoard.getWinTiles()[1];
        double offsetX = -width / 2;
        double offsetY = -width / 2;
        winLine.setStartX(startTile.getTilePane().getLayoutX() + offsetX);
        winLine.setStartY(startTile.getTilePane().getLayoutY() + offsetY);
        winLine.setEndX(endTile.getTilePane().getLayoutX() + offsetX);
        winLine.setEndY(endTile.getTilePane().getLayoutY() + offsetY);

        Rectangle highlight = new Rectangle();
        highlight.setFill(new Color(.4,.4,.4,.75));
        highlight.setWidth(111);
        highlight.setHeight(111);

        anchor.getChildren().add(highlight);
        anchor.getChildren().add(winLine);
        anchor.getChildren().add(bigPiece);
    }

    public static void setMainBoardWonGraphics(MainBoard mainBoard, Piece.Team winTeam) {
        Pane anchor = new Pane();
        mainBoard.getMainBoardPane().getChildren().add(anchor);
        Text winText = new Text((winTeam == Piece.Team.X ? Game.player1Name.get() : Game.player2Name.get()) + " Wins!");
        winText.setFont(new Font(40));
        winText.setX(mainBoard.getMainBoardPane().getWidth() / 2 - winText.prefWidth(20) / 2);
        winText.setY(-22);
        if (Game.darkMode.get()) winText.setFill(new Color(1,1,1,1));
        anchor.getChildren().add(winText);

        float width = 6;
        Line winLine = new Line();
        winLine.setStroke(Color.RED);
        winLine.setStrokeWidth(width);
        SubBoard startTile = mainBoard.getWinBoards()[0];
        SubBoard endTile = mainBoard.getWinBoards()[1];
        double offsetX = -(0*width / 2) + startTile.getSubBoardPane().getWidth()/2;
        double offsetY = -(0*width / 2) + startTile.getSubBoardPane().getHeight()/2;
        winLine.setStartX(startTile.getSubBoardPane().getLayoutX() + offsetX);
        winLine.setStartY(startTile.getSubBoardPane().getLayoutY() + offsetY);
        winLine.setEndX(endTile.getSubBoardPane().getLayoutX() + offsetX);
        winLine.setEndY(endTile.getSubBoardPane().getLayoutY() + offsetY);

        anchor.getChildren().add(winLine);

        for (SubBoard board : mainBoard.getSubBoards()) {
            for (Tile tile : board.getTiles()) {
                Actions.placeNewPiece(tile,winTeam);
            }
        }

    }
}
