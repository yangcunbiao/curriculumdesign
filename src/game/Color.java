package game;

public enum Color {
    BLACK(1),WHITE(-1),NULL(0),BLACKTOWHITE(2),WHITETOBLACK(3);
    private int value;
    Color(int value){
        this.value=value;
    }
    public Boolean isSame(Color a){
        if(a.value==this.value)return true;
        return false;
    }
    public static Color endFlip(Color color){
        if(color==BLACK||color==WHITETOBLACK){
            return BLACK;
        }else if(color==WHITE||color==BLACKTOWHITE){
            return WHITE;
        }else{
            return NULL;
        }
    }
}
