package br.com.renanmassaroto.expensesmanager.models;

import java.math.BigDecimal;

/**
 * Created by renancardosomassaroto on 11/9/15.
 */
public class WishlistItem {

    private long id;
    private String name;
    private BigDecimal value;
    private TransactionCategory category;

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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public void setCategory(TransactionCategory category) {
        this.category = category;
    }
}
