package com.evan.extremetictactoe.board.util;

import com.evan.extremetictactoe.board.Piece;
import com.evan.extremetictactoe.board.TileButton;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FXTranslator {

    /* HIERARCHY REFERENCE
     * Structure:
     * MainBoard
     * SubBoards(9-18)       Highlight Panes (0-8)
     * TilePanes(0-8)
     * TileButtons(0)        Pieces(1)
     */

    /**
     * Pieces
     */
    public static Piece getPiece(Pane tilePane) {
        return (Piece) tilePane.getChildren().get(1);
    }

    public static Piece getPiece(GridPane subBoardPane, int index) {
        return (Piece) getTilePane(subBoardPane, index).getChildren().get(1);
    }

    /**
     * TileButtons
     */
    public static TileButton getTileButton(Pane tilePane) {
        return (TileButton) tilePane.getChildren().get(0);
    }

    public static TileButton getTileButton(GridPane subBoardPane, int index) {
        return (TileButton) getTilePane(subBoardPane, index).getChildren().get(0);
    }

    /**
     * TilePanes
     * Indexes of tile panes are from 0-8
     */
    public static Pane getTilePane(GridPane subBoardPane, int index) {
        return (Pane) subBoardPane.getChildren().get(index);
    }

    public static Pane getTilePane(TileButton button) {
        return (Pane) button.getParent();
    }

    public static Pane getTilePane(Piece piece) {
        return (Pane) piece.getParent();
    }

    /**
     * HighlightPanes
     * Each sub board has 1 associated highlight pane. Indexes are 0-8
     */
    public static Pane getHighlightPane(TileButton button) {
        return (Pane) getMainBoardPane(button).getChildren().get(getMainBoardPane(button).getChildren().indexOf(button.getParent().getParent()) - 9);
    }

    public static Pane getHighlightPane(Pane tilePane) {
        return (Pane) getMainBoardPane(tilePane).getChildren().get(getMainBoardPane(tilePane).getChildren().indexOf(tilePane.getParent()) - 9);
    }

    public static Pane getHighlightPane(GridPane subBoardPane) {
        return (Pane) getMainBoardPane(subBoardPane).getChildren().get(getMainBoardPane(subBoardPane).getChildren().indexOf(subBoardPane) - 9);
    }

    public static Pane getHighlightPane(GridPane mainBoardPane, int index) {
        return (Pane) mainBoardPane.getChildren().get(index);
    }

    /**
     * SubBoards
     * Index of SubBoards are 9-18 (They come after HighlightPanes in the heirarchy, but are on the same level)
     */
    public static GridPane getSubBoardPane(TileButton button) {
        return (GridPane) button.getParent().getParent();
    }

    public static GridPane getSubBoardPane(Pane tilePane) {
        return (GridPane) tilePane.getParent();
    }

    public static GridPane getSubBoardPane(GridPane mainBoardPane, int index) {
        return (GridPane) mainBoardPane.getChildren().get(index);
    }

    /**
     * MainBoards
     */
    public static GridPane getMainBoardPane(TileButton button) {
        return (GridPane)button.getParent().getParent().getParent();
    }

    public static GridPane getMainBoardPane(Pane tilePane) {
        return (GridPane) tilePane.getParent().getParent();
    }

    public static GridPane getMainBoardPane(GridPane subBoardPane) {
        return (GridPane) subBoardPane.getParent();
    }

    public static GridPane getMainBoardPane(Parent root) {
        return ((GridPane) root.getChildrenUnmodifiable().get(0));
    }

}