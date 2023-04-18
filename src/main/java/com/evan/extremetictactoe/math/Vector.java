package com.evan.extremetictactoe.math;

public class Vector {

    public static final Vector I = new Vector(1,0);
    public static final Vector J = new Vector(0,1);

    private int x,y;
    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector getAdded(Vector vec) {
        int x = getX() + vec.getX();
        int y = getY() + vec.getY();
        return new Vector(x,y);
    }

    public Vector getMultiplied(int mul) {
        int x = getX() * mul;
        int y = getY() * mul;
        return new Vector(x,y);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }


    @Override
    public String toString() {
        return "< " + x + ", " + y + " >";
    }

    @Override
    public boolean equals(Object obj) {
        Vector newVec = ((Vector) obj);
        try {
            return getX() == newVec.getX() && getY() == newVec.getY();
        } catch (Exception e) {
            return false;
        }
    }
}
