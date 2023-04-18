package com.evan.extremetictactoe.board.util;

public enum Index {

    UPPER_LEFT(0),
    UPPER_MIDDLE(1),
    UPPER_RIGHT(2),
    MIDDLE_LEFT(3),
    MIDDLE_MIDDLE(4),
    MIDDLE_RIGHT(5),
    BOTTOM_LEFT(6),
    BOTTOM_MIDDLE(7),
    BOTTOM_RIGHT(8),
    NONE(69);

    private final int id;
    Index(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }


    public static Index getIndex(int id) {
        for(Index nov : Index.values()) {
            if (nov.getId() == id) return nov;
        }
        return NONE;
    }
}
