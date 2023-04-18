package com.evan.extremetictactoe.board.util;

import com.evan.extremetictactoe.board.MainBoard;
import com.evan.extremetictactoe.board.SubBoard;
import com.evan.extremetictactoe.board.Tile;
import com.evan.extremetictactoe.math.Vector;

public class BoardUtil {

    public static Tile getTileFromLocation(SubBoard board, Vector location) {
        Index index = Index.NONE;
        if (location.equals(new Vector(1, 1))) index = Index.UPPER_LEFT;
        if (location.equals(new Vector(2, 1))) index = Index.UPPER_MIDDLE;
        if (location.equals(new Vector(3, 1))) index = Index.UPPER_RIGHT;
        if (location.equals(new Vector(1, 2))) index = Index.MIDDLE_LEFT;
        if (location.equals(new Vector(2, 2))) index = Index.MIDDLE_MIDDLE;
        if (location.equals(new Vector(3, 2))) index = Index.MIDDLE_RIGHT;
        if (location.equals(new Vector(1, 3))) index = Index.BOTTOM_LEFT;
        if (location.equals(new Vector(2, 3))) index = Index.BOTTOM_MIDDLE;
        if (location.equals(new Vector(3, 3))) index = Index.BOTTOM_RIGHT;
        try {
            return board.getTile(index);
        } catch (Exception e) {
            return null;
        }
    }

    public static SubBoard getSubBoardFromLocation(MainBoard board, Vector location) {
        Index index = Index.NONE;
        if (location.equals(new Vector(1, 1))) index = Index.UPPER_LEFT;
        if (location.equals(new Vector(2, 1))) index = Index.UPPER_MIDDLE;
        if (location.equals(new Vector(3, 1))) index = Index.UPPER_RIGHT;
        if (location.equals(new Vector(1, 2))) index = Index.MIDDLE_LEFT;
        if (location.equals(new Vector(2, 2))) index = Index.MIDDLE_MIDDLE;
        if (location.equals(new Vector(3, 2))) index = Index.MIDDLE_RIGHT;
        if (location.equals(new Vector(1, 3))) index = Index.BOTTOM_LEFT;
        if (location.equals(new Vector(2, 3))) index = Index.BOTTOM_MIDDLE;
        if (location.equals(new Vector(3, 3))) index = Index.BOTTOM_RIGHT;
        try {
            return board.getSubBoard(index);
        } catch (Exception e) {
            return null;
        }
    }

    public static Vector getLocationFromIndex(Index index) {
        Vector vector;
        switch (index) {
            case UPPER_LEFT -> vector = new Vector(1, 1);
            case UPPER_MIDDLE -> vector = new Vector(2, 1);
            case UPPER_RIGHT -> vector = new Vector(3, 1);
            case MIDDLE_LEFT -> vector = new Vector(1, 2);
            case MIDDLE_MIDDLE -> vector = new Vector(2, 2);
            case MIDDLE_RIGHT -> vector = new Vector(3, 2);
            case BOTTOM_LEFT -> vector = new Vector(1, 3);
            case BOTTOM_MIDDLE -> vector = new Vector(2, 3);
            case BOTTOM_RIGHT -> vector = new Vector(3, 3);
            default -> {
                return null;
            }
        }
        return vector;
    }

}
