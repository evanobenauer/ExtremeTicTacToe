package com.evan.extremetictactoe.board.util;

import com.evan.extremetictactoe.board.Piece;

public interface Board {

    boolean isWon();
    boolean isStaleMate();

    Piece.Team getWinTeam();

    void setWon(Piece.Team winTeam);
}
