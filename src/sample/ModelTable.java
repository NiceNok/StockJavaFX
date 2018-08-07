package sample;

public class ModelTable {
    Integer id;
    String pile;
    Integer amount;
    Double lng;
    Double lat;

    public ModelTable(Integer id, String pile, Integer amount, Double lng, Double lat) {
        this.id = id;
        this.pile = pile;
        this.amount = amount;
        this.lng = lng;
        this.lat = lat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPile() {
        return pile;
    }

    public void setPile(String pile) {
        this.pile = pile;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
