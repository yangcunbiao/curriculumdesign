package game;

public class Judge {
    //TODO:判断能否落子
    public static boolean judgeDrap(int x,int y,Color color,Color[][] chessboard){
        if(isInside(x,y)==false||chessboard[x][y]!=Color.NULL)return false;
        for (int offsetX=-1;offsetX<=1;offsetX++){
            for (int offsetY=-1;offsetY<=1;offsetY++){
                if(offsetX==0&&offsetY==0)continue;
                int indexX=offsetX+x,indexY=offsetY+y;
                if(isInside(indexX,indexY)&&chessboard[indexX][indexY].isSame(color)==false&&chessboard[indexX][indexY]!=Color.NULL){
                    for (int i=indexX+offsetX,j=indexY+offsetY;isInside(i,j);i+=offsetX,j+=offsetY){
                        if(chessboard[i][j]==Color.NULL){
                            break;
                        }else if(chessboard[i][j]==color){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    //TODO:判断是否结束
    public static boolean judgeIsOver(Color[][] chessboard){
        if(!isStalemate(Color.BLACK,chessboard) && !isStalemate(Color.WHITE,chessboard)){
            return true;
        }else {
            return false;
        }
    }
    //TODO:判断是否有子可落
    public static boolean isStalemate(Color color,Color[][] chessboard){
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if(chessboard[i][j]==Color.NULL){
                    if(judgeDrap(i,j,color,chessboard)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    //TODO:判断当前的坐标是否在棋盘内
    public static boolean isInside(int x,int y){
        return (x>=0&&x<8&&y>=0&&y<8);
    }
}
