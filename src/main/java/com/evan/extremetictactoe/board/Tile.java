package com.evan.extremetictactoe.board;

import com.evan.extremetictactoe.App;
import com.evan.extremetictactoe.board.util.BoardElement;
import com.evan.extremetictactoe.board.util.Index;
import com.evan.extremetictactoe.board.util.BoardUtil;
import com.evan.extremetictactoe.math.Vector;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Tile implements BoardElement {


    private final Pane tilePane;

    private boolean containsPiece = false;
    private boolean containsButton = false;


    public Tile(Pane tilePane) {
        this.tilePane = tilePane;
    }


    public Piece getPiece() {
        return (Piece)getTilePane().getChildren().get(1);
    }

    public TileButton getButton() {
        return (TileButton)getTilePane().getChildren().get(0);
    }


    public void addButton(TileButton button) {
        if (!containsButton()) {
            getTilePane().getChildren().add(button);
            setContainsButton(true);
        }
    }


    public void setContainsPiece(boolean containsPiece) {
        this.containsPiece = containsPiece;
    }

    public boolean containsPiece() {
        return containsPiece;
    }


    public void setContainsButton(boolean containsButton) {
        this.containsButton = containsButton;
    }

    public boolean containsButton() {
        return containsButton;
    }


    public SubBoard getSubBoard() {
        return App.getGame().getMainBoard().getSubBoard((GridPane) getTilePane().getParent());
    }


    public Pane getTilePane() {
        return tilePane;
    }


    @Override
    public Index getIndex() {
        return Index.getIndex(getSubBoard().getTiles().indexOf(this));
    }

    @Override
    public Vector getLocation() {
        return BoardUtil.getLocationFromIndex(getIndex());
    }

}
