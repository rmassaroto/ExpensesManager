package br.com.renanmassaroto.expensesmanager.models;

/**
 * Created by renancardosomassaroto on 11/9/15.
 */
public class AccountType {

    private long id;
    private String name;
    private String colorHex;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }
}
