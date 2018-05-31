package com.miq.sms.models.vo;

import java.sql.Date;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Mustafa
 */
//class Items value object
public class BuyVo {

    //initialize
    private IntegerProperty id;
    private IntegerProperty qty;
//    private StringProperty barcode;
    private FloatProperty buyPrice;
//    private IntegerProperty discount;
    private SimpleObjectProperty<Date> date;
    private SimpleObjectProperty<Date> expDate;
    private StringProperty notes;
    private StringProperty userName;
    //initilaze object from Items
    private ProductsVo productsVo;
        //initilaze object from user
    private UsersVo user;

    public UsersVo getUser() {
        return user;
    }

    public void setUser(UsersVo user) {
        this.user = user;
    }


    //constructor
    public BuyVo() {
        this.id = new SimpleIntegerProperty();
        this.qty = new SimpleIntegerProperty();
//        this.barcode = new SimpleStringProperty();
        this.buyPrice = new SimpleFloatProperty();
//        this.discount = new SimpleIntegerProperty();
        this.date = new SimpleObjectProperty<>();
        this.expDate = new SimpleObjectProperty<>();
        this.notes = new SimpleStringProperty();
        this.userName = new SimpleStringProperty();
    }

    //getter and setter and property methods
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty IdProperty() {
        return id;
    }
    
    public IntegerProperty QtyProperty() {
        return qty;
    }

    public int getQty() {
        return qty.get();
    }

    public void setQty(int qty) {
        this.qty.set(qty);
    }

//    public StringProperty BarcodeProperty() {
//        return barcode;
//    }
//
//    public String getBarcode() {
//        return barcode.get();
//    }
//
//    public void setBarcode(String barcode) {
//        this.barcode.set(barcode);
//    }

    public FloatProperty BuyPriceProperty() {
        return buyPrice;
    }

    public float getBuyPrice() {
        return buyPrice.get();
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice.set(buyPrice);
    }
    
//    public int getDiscount() {
//        return discount.get();
//    }
//
//    public void setDiscount(int discount) {
//        this.discount.set(discount);
//    }
//
//    public IntegerProperty DiscountProperty() {
//        return discount;
//    }

    public Object getDate() {
        return date.get();
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public SimpleObjectProperty<Date> DateProperty() {
        return date;
    }
    public Object getExpDate() {
        return expDate.get();
    }

    public void setExpDate(Date expDate) {
        this.expDate.set(expDate);
    }

    public SimpleObjectProperty<Date> ExpDateProperty() {
        return expDate;
    }

    public StringProperty NotesProperty() {
        return notes;
    }

    public String getNotes() {
        return notes.get();
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }


    public StringProperty UserNameProperty() {
        return userName;
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public ProductsVo getProductsVo() {
        return productsVo;
    }

    public void setProductsVo(ProductsVo productsVo) {
        this.productsVo = productsVo;
    }

}
