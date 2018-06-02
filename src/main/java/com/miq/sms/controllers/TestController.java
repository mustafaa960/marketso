package com.miq.sms.controllers;


import com.miq.sms.models.dao.BuyDao;
import com.miq.sms.models.dao.SalesDao;
import com.miq.sms.models.dao.UsersDao;
import com.miq.sms.models.vo.ProductsVo;
import com.miq.sms.models.vo.UsersVo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class TestController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//        ProductsVo productsVo=new  ProductsVo();
//        ProductsDao.getInstance().getDataById(1);
        BuyDao.getInstance().loadAll();
//        UsersVo uv =new UsersVo();
//        int id =UsersDao.getInstance().getLastUserId()+1;
//        uv.setId(id);
//        uv.setUserName("user");
//        uv.setUserFullName("mustafa abdull hadi");
//        uv.setPassword("user");
//        UsersType usersType=UsersType.getUsersTypeById(2);
//        uv.setUsersType(usersType);
//        
//                int is=UsersDao.getInstance().insert(uv);
//        if(is==1){
//            System.err.println("insert success");
//            UsersDao.getInstance().loadAll();
//        }else{
//            System.err.println("insert not success");
//            UsersDao.getInstance().loadAll();
//        }
//        SalesVo sv=new SalesVo();
//        int id=SalesDao.getInstance().getLastSalesId();
//        sv.setId(id+1);
//        sv.setName("جمله");
//        sv.setQty(3);
//        sv.setBuyPrice(100);
//        sv.setDate(Date.valueOf(LocalDate.now()));
//        sv.setCustomerName("mmm");
//        sv.setUserName("mmmm");
//        int is=SalesDao.getInstance().insert(sv);
//        if(is==1){
//            System.err.println("update success");
//            SalesDao.getInstance().loadAll();
//        }else{
//            System.err.println("update not success");
//            SalesDao.getInstance().loadAll();
//        }
////        SalesDao.getInstance().loadAll();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
