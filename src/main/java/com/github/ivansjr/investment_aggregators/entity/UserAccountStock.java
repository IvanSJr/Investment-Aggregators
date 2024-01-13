package com.github.ivansjr.investment_aggregators.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts_stocks")
public class UserAccountStock {

    @EmbeddedId
    private UserAccountStockId id;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    @SuppressWarnings("JpaAttributeTypeInspection")
    private UserAccount userAccount;

    @Column(name = "quantity")
    private Integer quantity;

    public UserAccountStock() {
    }

    public UserAccountStock(UserAccountStockId id, Stock stock, UserAccount userAccount, Integer quantity) {
        this.id = id;
        this.stock = stock;
        this.userAccount = userAccount;
        this.quantity = quantity;
    }

    public UserAccountStockId getId() {
        return id;
    }

    public void setId(UserAccountStockId id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
