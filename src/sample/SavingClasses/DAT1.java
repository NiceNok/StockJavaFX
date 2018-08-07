package sample.SavingClasses;

import java.io.Serializable;

public class DAT1 implements Serializable {
    private Double newTranslateX;
    private Double newTranslateY;
    private String str;
    public DAT1() {

    }



    public DAT1(Double newTranslateY, Double newTranslateX, String str){
        setNewTranslateX(newTranslateX);
        setNewTranslateY(newTranslateY);
        setStr(str);
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Double getNewTranslateX() {
        return newTranslateX;
    }

    public void setNewTranslateX(Double newTranslateX) {
        this.newTranslateX = newTranslateX;
    }

    public Double getNewTranslateY() {
        return newTranslateY;
    }

    public void setNewTranslateY(Double newTranslateY) {
        this.newTranslateY = newTranslateY;
    }
}
