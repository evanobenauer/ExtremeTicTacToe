package com.evan.extremetictactoe.board;

import com.evan.extremetictactoe.controllers.Actions;
import com.evan.extremetictactoe.App;
import com.evan.extremetictactoe.board.util.BoardElement;
import com.evan.extremetictactoe.board.util.Index;
import com.evan.extremetictactoe.board.util.BoardUtil;
import com.evan.extremetictactoe.math.Math;
import com.evan.extremetictactoe.math.Vector;
import javafx.scene.control.Button;

public class TileButton extends Button implements BoardElement {

    private final Tile tile;

    public TileButton(Tile tile) {
        this.tile = tile;
        setPrefWidth(37);
        setPrefHeight(37);
        setOpacity(0);
    }

    @Override
    public void fire() {
        //If the board is not highlighted or there is a piece on the tile, do not complete the action
        if (!getSubBoard().isHighlighted() || getTile().containsPiece()) {
            if (App.getGame().getTurnNumber() >= 1) return;
        }

        Actions.setTurnIndicator(!Math.isEven(App.getGame().getTurnNumber()) ? Piece.Team.X : Piece.Team.O);
        Actions.placeNewPiece(getTile(),Math.isEven(App.getGame().getTurnNumber()) ? Piece.Team.X : Piece.Team.O);

        if (getSubBoard().isWon())
            Actions.setSubBoardWonGraphic(getSubBoard(),getSubBoard().getWinTeam());


        if (App.getGame().getMainBoard().isWon())
            Actions.setMainBoardWonGraphics(App.getGame().getMainBoard(),App.getGame().getMainBoard().getWinTeam());

        //Finds the next board that has the same location on the main board as the selected tile on the sub board
        Actions.setBoardHighlights(App.getGame().getMainBoard().getSubBoard(getTile().getIndex()));

    }


    public Tile getTile() {
        return tile;
    }

    public SubBoard getSubBoard() {
        return getTile().getSubBoard();
    }


    @Override
    public Index getIndex() {
        int i = getSubBoard().getSubBoardPane().getChildren().indexOf(getTile().getTilePane());
        return Index.getIndex(i);
    }

    @Override
    public Vector getLocation() {
        return BoardUtil.getLocationFromIndex(getIndex());
    }

}