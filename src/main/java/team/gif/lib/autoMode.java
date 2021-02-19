package team.gif.lib;

public enum autoMode {

    MOBILITY(0),
    FIVE_BALL_TRENCH(0);

    private int value;
    autoMode(int value) {
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
}
