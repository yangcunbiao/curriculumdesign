package game;

public class Judge {
    //TODO：判断能否落子
    private static boolean judgeDrap(int x,int y,Color color,Color[][] chessBoard){
        int drapFlag = 0;
        if(chessBoard[x][y] != Color.NULL) {
            //TODO：判断左
            if(chessBoard[x][y-1] != color && drapFlag !=0){
                for(int i=y-2;i>=0;i--){
                    if(chessBoard[x][i] == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
            //TODO：判断右
            if(chessBoard[x][y+1] != color && drapFlag !=0){
                for(int i=y+2;i<8;i++){
                    if(chessBoard[x][i] == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
            //TODO：判断上
            if(chessBoard[x-1][y] != color && drapFlag !=0){
                for(int i=x-2;i>=0;i--){
                    if(chessBoard[i][y] == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
            //TODO：判断下
            if(chessBoard[x+1][y] != color && drapFlag !=0){
                for(int i=x+2;i<8;i++){
                    if(chessBoard[i][y] == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
            //TODO：判断左上
            if(chessBoard[x-1][y-1]!= color && drapFlag !=0){
                for(int i=2;x-i>=0&&y-i>=0;i++){
                    if(chessBoard[x-i][y-i] == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
            //TODO：判断右上
            if(chessBoard[x-1][y+1] != color && drapFlag !=0){
                for(int i=2;x-i>=0&&y+i<8;i++){
                    if(chessBoard[x-i][y+i] == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
            //TODO：判断左下
            if(chessBoard[x+1][y-1] != color && drapFlag !=0){
                for(int i=2;x+i<8&&y-i>=0;i++){
                    if(chessBoard[x+i][y-i] == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
            //TODO：判断右下
            if(chessBoard[x+1][y+1] != color && drapFlag !=0){
                for(int i=2;x+i<8&&y+i<8;i++){
                    if(chessBoard[x+i][y+i] == color){
                        drapFlag = 1;
                        break;
                    }
                }
            }
        }
        if(drapFlag == 1){
            return true;
        } else{
            return false;
        }
    }
    //TODO：判断输赢
    private static void judgeIsOver(){

    }
}
