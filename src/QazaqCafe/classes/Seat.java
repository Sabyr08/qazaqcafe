package QazaqCafe.classes;

import java.io.Serializable;

public class Seat implements Serializable {
    private int num;
    private boolean check;

    public Seat() {
    }

    public Seat(int num, boolean check) {
        this.num = num;
        this.check = check;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
