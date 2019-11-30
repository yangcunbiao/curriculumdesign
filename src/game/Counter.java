package game;

public class Counter {
    private int playerNum1;
    private int playerNum2;

    public Counter(int playerNum1, int playerNum2){
        this.playerNum1=playerNum1;
        this.playerNum2=playerNum2;
    }

    public int getPlayerNum1() {
        return playerNum1;
    }

    public int getPlayerNum2() {
        return playerNum2;
    }

    public void count(Color[][] chessboard){
        int blackNum=0,whiteNum=0;
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if (chessboard[i][j].isSame(Color.BLACK)){
                    blackNum++;
                }else if(chessboard[i][j].isSame(Color.WHITE)){
                    whiteNum++;
                }
            }
        }
        this.playerNum1=blackNum;
        this.playerNum2=whiteNum;
    }
}
