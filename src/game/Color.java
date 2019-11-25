package game;

public enum Color {
    BLACK(1),WHITE(-1),NULL(0);
    private int value;
    private  Color(int value){
        this.value=value;
    }
    public Boolean isSame(Color a){
        if(a.value==this.value)return true;
        return false;
    }
}
