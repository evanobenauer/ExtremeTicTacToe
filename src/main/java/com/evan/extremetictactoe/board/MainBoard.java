package com.evan.extremetictactoe.board;

import com.evan.extremetictactoe.board.util.*;
import com.evan.extremetictactoe.math.Vector;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class MainBoard implements Board {

    private GridPane mainBoardPane;

    private final ArrayList<SubBoard> subBoards = new ArrayList<>();

    //private boolean isBoardWon = false;
    private Piece.Team winTeam;
    private SubBoard firstBoardWin;
    private SubBoard lastBoardWin;


    public MainBoard() {
        this(null);
    }

    public MainBoard(GridPane mainBoardPane) {
        this.mainBoardPane = mainBoardPane;
    }


    public void setScene(Parent scene) {
        this.mainBoardPane = FXTranslator.getMainBoardPane(scene);
    }


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
        for (SubBoard subBoard : getSubBoards()) { //Loops through all the panes in the grid
            for (Vector dir : directions) { //Loops through all the directions around the pane
                for (int i = 1; i < maxMul; i++) { //Loops through all the multiples of that direction
                    try {
                        Vector dirVec = dir.getMultiplied(i);
                        SubBoard nextBoard = BoardUtil.getSubBoardFromLocation(this, subBoard.getLocation().getAdded(dirVec));

                        //If the board has a win team that is not the same in this direction, it will move to the next direction
                        if (!subBoard.getWinTeam().equals(nextBoard.getWinTeam())) break;

                        //If the break is passed, there must be another one in a row.
                        //If this loops twice, then there are 3 in a row
                        if (i == 2) {
                            setWon(subBoard.getWinTeam());
                            setWinBoards(subBoard,nextBoard);
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

    public boolean isStaleMate() { //TODO This is broken, lets find the proper child size and fix it
        for (SubBoard subBoard : getSubBoards()) {
            if (subBoard.getSubBoardPane().getChildren().size() < 2) return false; //Broke
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


    public void setWinBoards(SubBoard firstBoard, SubBoard lastBoard) {
        this.firstBoardWin = firstBoard;
        this.lastBoardWin = lastBoard;
    }

    public SubBoard[] getWinBoards() {
        return new SubBoard[]{firstBoardWin, lastBoardWin};
    }


    public ArrayList<SubBoard> getSubBoards() {
        return subBoards;
    }

    public SubBoard getSubBoard(Index index) {
        return subBoards.get(index.getId());
    }

    public SubBoard getSubBoard(GridPane subBoardPane) {
        for (int i = 9; i < 18; i++) {
            if (getMainBoardPane().getChildren().get(i).equals(subBoardPane))
                return getSubBoard(Index.getIndex(i-9));
        }
        return null;
    }


    public GridPane getMainBoardPane() {
        return mainBoardPane;
    }

}
