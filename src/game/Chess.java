package game;

public class Chess {
    //黑棋为1，白棋为2
    private int color;
    public Chess(int color){
        this.color = color;
    }
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
