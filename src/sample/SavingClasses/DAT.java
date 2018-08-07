package sample.SavingClasses;

import java.io.Serializable;

public class DAT implements Serializable {

    private Integer id;
    private Integer amount;
    private String pile;
    private Double lat;
    private Double lng;
    private boolean radio;
    private Double width;
    private Double height;
    private Double newTranslateX;
    private Double newTranslateY;
    public DAT() {

    }

   public DAT( Integer id, Integer amount, String pile, Double lat, Double lng,
               boolean radio, Double width, Double height, Double newTranslateX, Double newTranslateY) {

        setId(id);
        setAmount(amount);
        setPile(pile);
        setLat(lat);
        setLng(lng);
        setNewTranslateX(newTranslateX);
        setNewTranslateY(newTranslateY);
        setRadio(radio);
        setWidth(width);
        setHeight(height);

    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public boolean getRadio() {
        return radio;
    }

    public void setRadio(boolean radio) {
        this.radio = radio;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setPile(String pile) {
        this.pile = pile;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getPile() {
        return pile;
    }


    public Double getLat() {
        return lat;
    }


    public Double getLng() {
        return lng;
    }

    public double getNewTranslateX() {
        return newTranslateX;

    }
    public void setNewTranslateX(double newTranslateX) {
        this.newTranslateX = newTranslateX;
    }

    public double getNewTranslateY() {
        return newTranslateY;
    }
    public void setNewTranslateY(double newTranslateY) {
        this.newTranslateY = newTranslateY;
    }
}