package com.evan.extremetictactoe.board;

import com.evan.extremetictactoe.App;
import com.evan.extremetictactoe.board.util.*;
import com.evan.extremetictactoe.math.Vector;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SubBoard implements Board, BoardElement {

    /**
     * Each SubBoard has a corresponding highlight pane attached to the same cell of the MainBoard
     */

    private final GridPane subBoardPane;

    private final ArrayList<Tile> tiles = new ArrayList<>();

    //private boolean isBoardWon = false;
    private Piece.Team winTeam;
    private Tile firstTileWin;
    private Tile lastTileWin;


    public SubBoard(GridPane subBoardPane) {
        this.subBoardPane = subBoardPane;
    }


    //Win Checking Algorithm
    public boolean isWon() {
        //if (this.isBoardWon) return true; //Just so we don't have to loop through this every time we want to check the board's status
        Vector[] directions = { //Goes in order of the unit circle
                Vector.I, //x+
                Vector.J.getAdded(Vector.I),//++
                Vector.J,//y+
                Vector.J.getAdded(Vector.I.getMultiplied(-1)),//y+x-
                Vector.I.getMultiplied(-1),//x-
                Vector.J.getAdded(Vector.I).getMultiplied(-1),//--
                Vector.J.getMultiplied(-1),//y-
                Vector.J.getAdded(Vector.I.getMultiplied(-1)).getMultiplied(-1),//x+y-
        };
        int maxMul = 3;
        for (Tile tile : getTiles()) { //Loops through all the panes in the grid
            for (Vector dir : directions) { //Loops through all the directions around the pane
                for (int i = 1; i < maxMul; i++) { //Loops through all the multiples of that direction
                    try {
                        Vector dirVec = dir.getMultiplied(i);
                        Tile nextTile = BoardUtil.getTileFromLocation(this,tile.getLocation().getAdded(dirVec));

                        //If a tile is empty or a piece is not of the same team, stop counting in that direction
                        if (!tile.getPiece().getTeam().equals(nextTile.getPiece().getTeam())) break;

                        //If the break is passed, there must be another one in a row.
                        //If this loops twice, then there are 3 in a row
                        if (i == 2) {
                            setWon(tile.getPiece().getTeam());
                            setWinTiles(tile,nextTile);
                            return true;
                        }
                    } catch (NullPointerException e) { //Stops counting forward if off of the board, moves to next direction loop
                        break;
                    } catch (IndexOutOfBoundsException e) { //Stops counting forward if there is no piece at the location
                        break;
                    }
                }
            }
        }
        return false;
    }

    public boolean isStaleMate() {
        for (Tile tile : getTiles()) {
            if (tile.getTilePane().getChildren().size() < 2) return false;
        }
        return true;
    }


    public void setWon(Piece.Team winTeam) {
        this.winTeam = winTeam;
        //this.isBoardWon = true;
    }

    public Piece.Team getWinTeam() {
        return winTeam;
    }


    public void setWinTiles(Tile firstTile, Tile lastTile) {
        this.firstTileWin = firstTile;
        this.lastTileWin = lastTile;
    }

    public Tile[] getWinTiles() {
        return new Tile[]{firstTileWin, lastTileWin};
    }


    public void setHighlighted(boolean highlighted) {
        Rectangle highlightRect = new Rectangle(150, 150);
        highlightRect.setFill(new Color(170f / 255, 200f / 255, 255f / 255, 1));
        if (highlighted) {
            getHighlightPane().getChildren().add(highlightRect);
        } else {
            getHighlightPane().getChildren().remove(highlightRect);
        }
    }

    public boolean isHighlighted() {
        return getHighlightPane().getChildren().size() >= 1;
    }


    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Tile getTile(Index index) {
        return tiles.get(index.getId());
    }

    public Tile getTile(Pane tilePane) {
        for (int i = 0; i < 9; i++) {
            if (getSubBoardPane().getChildren().get(i).equals(tilePane))
                return getTile(Index.getIndex(i));
        }
        return null;
    }


    public Pane getHighlightPane() {
        return FXTranslator.getHighlightPane(getSubBoardPane());
    }

    public GridPane getSubBoardPane() {
        return subBoardPane;
    }


    @Override
    public Index getIndex() {
        return Index.getIndex(App.getGame().getMainBoard().getMainBoardPane().getChildren().indexOf(getSubBoardPane())-9);
    }

    @Override
    public Vector getLocation() {
        return BoardUtil.getLocationFromIndex(getIndex());
    }

}
