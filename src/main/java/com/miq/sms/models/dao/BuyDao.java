package com.miq.sms.models.dao;

import com.miq.sms.models.vo.BuyVo;
import com.miq.sms.models.vo.ProductsVo;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

/**
 *
 * @author Mustfa
 */
public class BuyDao extends Dao implements DaoList<BuyVo> {

    private static BuyDao buyDao;

    private BuyDao() {

    }

    public static BuyDao getInstance() {
        if (buyDao == null) {
            buyDao = new BuyDao();
        }
        return buyDao;

    }

    @Override
    public ObservableList<BuyVo> loadAll() throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductsVo productsVo = null;
        BuyVo buyVo = null;
        ObservableList<BuyVo> buy = FXCollections.observableArrayList();
        try {
            con = getConnection();
            String sql = "SELECT products.id, products.name, products.qty, products.barcode,"
                    + " products.buy_price, products.sale_price_odd, products.sale_price_even, products.max_discount,"
                    + " products.exp_date, products.store_date, products.notes,"
                    + " buy_bill.id, buy_bill.qty, buy_bill.buy_price, buy_bill.date, buy_bill.exp_date,"
                    + " buy_bill.user_name, buy_bill.notes, buy_bill.product_id"
                    + "  FROM products INNER JOIN buy_bill ON products.id = buy_bill.product_id";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                //object FROM products
                productsVo = new ProductsVo();
                productsVo.setId(rs.getInt("id"));
                productsVo.setName(rs.getString("name"));
                productsVo.setQty(rs.getInt("qty"));
                productsVo.setBarcode(rs.getString("barcode"));
                productsVo.setBuyPrice(rs.getFloat("buy_price"));
                productsVo.setSalePriceOdd(rs.getFloat("sale_price_odd"));
                productsVo.setSalePriceEven(rs.getFloat("sale_price_even"));
                productsVo.setMaxDiscount(rs.getInt("max_discount"));
                productsVo.setExp_date(rs.getDate("exp_date"));
                productsVo.setStore_date(rs.getDate("store_date"));
                productsVo.setNotes(rs.getString("notes"));
                //object from sales
                buyVo = new BuyVo();
                buyVo.setId(rs.getInt("buy_bill.id"));
                buyVo.setQty(rs.getInt("buy_bill.qty"));
                buyVo.setDate(rs.getDate("buy_bill.date"));
                buyVo.setExpDate(rs.getDate("buy_bill.exp_date"));
                buyVo.setBuyPrice(rs.getFloat("buy_bill.buy_price"));
                buyVo.setUserName(rs.getString("buy_bill.user_name"));
                buyVo.setNotes(rs.getString("buy_bill.notes"));
                buyVo.setProductsVo(productsVo);
                buy.add(buyVo);
//                System.err.println(buyVo.getId());
//                System.err.println(buyVo.getBuyPrice());
////                System.err.println(buyVo.getDiscount());
//                System.err.println(" ...........");
//                System.err.println(buyVo.getProductsVo().getId());
//                System.err.println(buyVo.getProductsVo().getBuyPrice());
//                System.err.println(buyVo.getProductsVo().getMaxDiscount());
//                System.err.println(" _____________________");

            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("خطأ");
            alert.setHeaderText(ex.getMessage());
            alert.setContentText(ex.toString());
            alert.showAndWait();
//            e.printStackTrace();
        } finally {
            ps.close();
            rs.close();
            CloseConnection(con);
        }
        return buy;
    }

    @Override
    public int insert(BuyVo bv) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        int isInsert = 0;
        try {
            con = getConnection();
//            con.setAutoCommit(false);
//            String sqlProduct = "INSERT INTO products ( id, name, qty, barcode, buy_price, sale_price_odd, sale_price_even,"
//                    + "max_discount, exp_date, store_date,notes ) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
//            ps = con.prepareStatement(sqlProduct);
//            ps.setInt(1, bv.getProductsVo().getId());
//            ps.setString(2, bv.getProductsVo().getName());
//            ps.setInt(3, bv.getProductsVo().getQty());
//            ps.setString(4, bv.getProductsVo().getBarcode());
//            ps.setFloat(5, bv.getProductsVo().getBuyPrice());
//            ps.setFloat(6, bv.getProductsVo().getSalePriceOdd());
//            ps.setFloat(7, bv.getProductsVo().getSalePriceEven());
//            ps.setInt(8, bv.getProductsVo().getMaxDiscount());
//            ps.setDate(9, (Date) bv.getProductsVo().getExp_date());
//            ps.setDate(10, (Date) bv.getProductsVo().getStore_date());
//            ps.setString(11, bv.getProductsVo().getNotes());
//            ps.executeUpdate();
            String sql = "INSERT INTO buy_bill ( buy_bill.id, buy_bill.qty, buy_bill.buy_price, buy_bill.date,buy_bill.exp_date,"
                    + " buy_bill.user_name, buy_bill.notes, buy_bill.product_id) VALUES(?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, bv.getId());
            ps.setInt(2, bv.getQty());
            ps.setFloat(3, bv.getBuyPrice());
            ps.setDate(4, (Date) bv.getDate());
            ps.setDate(5, (Date) bv.getExpDate());
//            ps.setString(6, bv.getUserName());
            ps.setString(6, bv.getUser().getUserName());
            ps.setString(7, bv.getNotes());
            ps.setInt(8, bv.getProductsVo().getId());
            ps.executeUpdate();
//            con.commit();
            isInsert = 1;
        } catch (SQLException ex) {
//            con.rollback();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("خطأ");
            alert.setHeaderText(ex.getMessage());
            alert.setContentText(ex.toString());
            alert.showAndWait();
        } finally {
            ps.close();
            CloseConnection(con);
        }
        return isInsert;
    }

    @Override
    public int update(BuyVo bv) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        int isUpdate = 0;
        try {
            con = getConnection();
//            con.setAutoCommit(false);
//            String SqlProducts = "UPDATE products SET name=?, qty=?, barcode=?, buy_price=?, sale_price_odd=?,"
//                    + " sale_price_even=?,max_discount=?, exp_date=?, store_date=?,notes=?  WHERE id=? ";
//            ps = con.prepareStatement(SqlProducts);
//            ps.setString(1, bv.getProductsVo().getName());
//            ps.setInt(2, bv.getProductsVo().getQty());
//            ps.setString(3, bv.getProductsVo().getBarcode());
//            ps.setFloat(4, bv.getProductsVo().getBuyPrice());
//            ps.setFloat(5, bv.getProductsVo().getSalePriceOdd());
//            ps.setFloat(6, bv.getProductsVo().getSalePriceEven());
//            ps.setInt(7, bv.getProductsVo().getMaxDiscount());
//            ps.setDate(8, (Date) bv.getProductsVo().getExp_date());
//            ps.setDate(9, (Date) bv.getProductsVo().getStore_date());
//            ps.setString(10, bv.getProductsVo().getNotes());
//            ps.setInt(11, bv.getProductsVo().getId());
//            ps.executeUpdate();
            String sql = "UPDATE buy_bill SET  buy_bill.qty=?, buy_bill.buy_price=?, buy_bill.date=?,buy_bill.exp_date=?,"
                    + " buy_bill.user_name=?, buy_bill.notes=?, buy_bill.product_id=? WHERE buy_bill.id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, bv.getQty());
            ps.setFloat(2, bv.getBuyPrice());
            ps.setDate(3, (Date) bv.getDate());
            ps.setDate(4, (Date) bv.getExpDate());
            ps.setString(5, bv.getUserName());
//            ps.setString(5, bv.getUser().getUserName());
            ps.setString(6, bv.getNotes());
            ps.setInt(7, bv.getProductsVo().getId());
            ps.setInt(8, bv.getId());
            ps.executeUpdate();
//            con.commit();
            isUpdate = 1;
        } catch (SQLException ex) {
//            con.rollback();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("خطأ");
            alert.setHeaderText(ex.getMessage());
            alert.setContentText(ex.toString());
            alert.showAndWait();
        } finally {
            ps.close();
            CloseConnection(con);
        }
        return isUpdate;
    }

    @Override
    public int delete(BuyVo bv) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        int isDelete = 0;
        try {
            con = getConnection();
            String Sql = "DELETE FROM buy_bill WHERE id=?";
            ps = con.prepareStatement(Sql);
            ps.setInt(1, bv.getId());
            ps.executeUpdate();
            isDelete = 1;

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("خطأ");
            alert.setHeaderText(ex.getMessage());
            alert.setContentText(ex.toString());
            alert.showAndWait();
        } finally {
            ps.close();
            CloseConnection(con);
        }
        return isDelete;
    }

    @Override
    public BuyVo getData(BuyVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BuyVo getDataById(int id) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductsVo productsVo = null;
        BuyVo buyVo = null;
        try {
            con = getConnection();
            String sql = "SELECT products.id, products.name, products.qty, products.barcode,"
                    + " products.buy_price, products.sale_price_odd, products.sale_price_even, products.max_discount,"
                    + " products.exp_date, products.store_date, products.notes,"
                    + " buy_bill.id, buy_bill.qty, buy_bill.buy_price, buy_bill.date, buy_bill.exp_date,"
                    + " buy_bill.user_name, buy_bill.notes, buy_bill.product_id"
                    + "  FROM products INNER JOIN buy_bill ON products.id = buy_bill.product_id WHERE buy_bill.id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                //object FROM products
                productsVo = new ProductsVo();
                productsVo.setId(rs.getInt("id"));
                productsVo.setName(rs.getString("name"));
                productsVo.setQty(rs.getInt("qty"));
                productsVo.setBarcode(rs.getString("barcode"));
                productsVo.setBuyPrice(rs.getFloat("buy_price"));
                productsVo.setSalePriceOdd(rs.getFloat("sale_price_odd"));
                productsVo.setSalePriceEven(rs.getFloat("sale_price_even"));
                productsVo.setMaxDiscount(rs.getInt("max_discount"));
                productsVo.setExp_date(rs.getDate("exp_date"));
                productsVo.setStore_date(rs.getDate("store_date"));
                productsVo.setNotes(rs.getString("notes"));
                //object from sales
                buyVo = new BuyVo();
                buyVo.setId(rs.getInt("buy_bill.id"));
                buyVo.setQty(rs.getInt("buy_bill.qty"));
                buyVo.setDate(rs.getDate("buy_bill.date"));
                buyVo.setExpDate(rs.getDate("buy_bill.exp_date"));
                buyVo.setBuyPrice(rs.getFloat("buy_bill.buy_price"));
                buyVo.setUserName(rs.getString("buy_bill.user_name"));
                buyVo.setNotes(rs.getString("buy_bill.notes"));
                buyVo.setProductsVo(productsVo);
                System.err.println(buyVo.getId());
                System.err.println(" ...........");
                System.err.println(buyVo.getProductsVo().getId());
                System.err.println(buyVo.getProductsVo().getMaxDiscount());

            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("خطأ");
            alert.setHeaderText(ex.getMessage());
            alert.setContentText(ex.toString());
            alert.showAndWait();
//            e.printStackTrace();
        } finally {
            ps.close();
            rs.close();
            CloseConnection(con);
        }
        return buyVo;
    }

    @Override
    public BuyVo getDataByName(String name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getLastBuyId() {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;
        int id = 0;
        try {
            con = getConnection();
            String sql = "SELECT `id` FROM `buy_bill` ORDER BY `id` DESC LIMIT 1";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("خطأ");
            alert.setHeaderText(ex.getMessage());
            alert.setContentText(ex.toString());
            alert.showAndWait();
//JOptionPane.showMessageDialog(null, ex.getMessage());   
        }
        return id;
    }

}
