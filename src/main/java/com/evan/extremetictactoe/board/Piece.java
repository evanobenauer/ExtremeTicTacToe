package com.evan.extremetictactoe.board;

import com.evan.extremetictactoe.Game;
import com.evan.extremetictactoe.board.util.BoardElement;
import com.evan.extremetictactoe.board.util.Index;
import com.evan.extremetictactoe.board.util.BoardUtil;
import com.evan.extremetictactoe.math.Vector;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Piece extends ImageView implements BoardElement {

    public enum Team {
        X,
        O
    }

    private Tile tile;

    private Team team;


    public Piece (Team team) {
        this(null,team);
    }

    public Piece(Tile tile, Team team) {
        this.tile = tile;
        setTeam(team);
        setFitHeight(37);
        setFitWidth(37);
    }


    public void place() {
        getTile().getTilePane().getChildren().add(this);
        getTile().setContainsPiece(true);
    }

    public void place(Tile tile) {
        this.tile = tile;
        tile.getTilePane().getChildren().add(this);
        tile.setContainsPiece(true);
    }


    public void setTeam(Team team) {
        this.team = team;
        Image image = (team == Team.X) ? Game.player1Avatar.get() : Game.player2Avatar.get();
        setImage(image);
    }

    public Team getTeam() {
        return team;
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
