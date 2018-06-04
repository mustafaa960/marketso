
package com.miq.sms.controllers;

import com.jfoenix.controls.JFXRippler;
import com.miq.sms.models.dao.ProductsDao;
import com.miq.sms.models.vo.ProductsVo;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author AL SAFIR
 */
public class OverviewController implements Initializable {

    @FXML
    private HBox groupHolder;
    @FXML
    private Group groupRegistered;
    @FXML
    private Group groupTarget;
    @FXML
    private Group groupGents;
    @FXML
    private Group groupLadies;
    @FXML
    private Label labMostWanted;
    @FXML
    private Label labMostWantedNum;
    @FXML
    private StackPane stackPaneExpireNotify;
    @FXML
    private AnchorPane anchPaneQtyNotify;
    @FXML
    private StackPane stackPaneMostWanted;
    @FXML
    private Button btnAddDepartment1;
    @FXML
    private Label lblTotalQty;
    @FXML
    private TableView<ProductsVo> tableProductsQty;
    @FXML
    private TableColumn<ProductsVo, String> colProductsName;
    @FXML
    private TableColumn<ProductsVo, Integer> colQty;
    @FXML
    private TableView<ProductsVo> tableExpProducts;
    @FXML
    private TableColumn<ProductsVo, String> colExpProductsName;
    @FXML
    private TableColumn<ProductsVo, Date> colExpDate;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setRipples();
        fillProductsExp();
        fillProductsQty();
    }    

    private void setRipples() {
        JFXRippler rippler1 = new JFXRippler(groupRegistered);
        JFXRippler rippler2 = new JFXRippler(groupGents);
        JFXRippler rippler3 = new JFXRippler(groupLadies);
        JFXRippler rippler4 = new JFXRippler(groupTarget);
        rippler1.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler2.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler3.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler4.setMaskType(JFXRippler.RipplerMask.RECT);

        rippler1.setRipplerFill(Paint.valueOf("#1564C0"));
        rippler2.setRipplerFill(Paint.valueOf("#00AACF"));
        rippler3.setRipplerFill(Paint.valueOf("#00B3A0"));
        rippler4.setRipplerFill(Paint.valueOf("#F87951"));

        groupHolder.getChildren().addAll(rippler1, rippler2, rippler3, rippler4);
    }
    public void fillProductsExp(){
        try {
            tableExpProducts.getItems().clear();
            ObservableList<ProductsVo> products = ProductsDao.getInstance().loadAll();
            ObservableList<ProductsVo> productsExp = FXCollections.observableArrayList();
            products.forEach((t) -> {
                if(((Date)t.getExp_date()).before(Date.valueOf(LocalDate.now().plusMonths(2)))){
                    productsExp.add(t);
                }
            });
            colExpProductsName.setCellValueFactory(c -> c.getValue().NameProperty());
            colExpDate.setCellValueFactory(c -> c.getValue().Exp_dateProperty());
            tableExpProducts.setItems(productsExp);
            lblTotalQty.setText(String.valueOf(products.size()));
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("خطأ");
            alert.setHeaderText(ex.getMessage());
            alert.setContentText(ex.toString());
            alert.showAndWait();
        }
    }
    public void fillProductsQty(){
        try {
            tableProductsQty.getItems().clear();
            ObservableList<ProductsVo> products = ProductsDao.getInstance().loadAll();
            ObservableList<ProductsVo> productsEndQty = FXCollections.observableArrayList();
            products.forEach((t) -> {
                if(t.getQty()<=t.getQtyMin()){
                    productsEndQty.add(t);
                }
            });
            colProductsName.setCellValueFactory(c -> c.getValue().NameProperty());
            colQty.setCellValueFactory(c -> c.getValue().QtyProperty().asObject());
            tableProductsQty.setItems(productsEndQty);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("خطأ");
            alert.setHeaderText(ex.getMessage());
            alert.setContentText(ex.toString());
            alert.showAndWait();
        }
    }
}
