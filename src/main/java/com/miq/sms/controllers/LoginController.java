
package com.miq.sms.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.miq.sms.models.dao.UsersDao;
import com.miq.sms.models.vo.UsersVo;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author danml
 */
public class LoginController implements Initializable {

    private Label label;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private StackPane rootPane;
    @FXML
    private ImageView imgProgress;

    UsersVo usersVo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handleValidation();
        imgProgress.setVisible(false);
    }

    @FXML
    private void login(ActionEvent event) {

        imgProgress.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(3));
        pauseTransition.setOnFinished(ev -> {
            completeLogin();

        });
        pauseTransition.play();
    }

    private void handleValidation() {
        usersVo = new UsersVo();
        RequiredFieldValidator fieldValidator = new RequiredFieldValidator();
        fieldValidator.setMessage("Input required");
        fieldValidator.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtUsername.getValidators().add(fieldValidator);
        txtUsername.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal) -> {
            if (!newVal) {
                txtUsername.validate();
                usersVo.setUserName(txtUsername.getText());
            }
        });
        RequiredFieldValidator fieldValidator2 = new RequiredFieldValidator();
        fieldValidator2.setMessage("Input required");
        fieldValidator2.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtPassword.getValidators().add(fieldValidator2);
        txtPassword.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                txtPassword.validate();
                usersVo.setPassword(txtPassword.getText());
            }
        });

    }

    private void completeLogin() {
        try {
            UsersVo uv = UsersDao.getInstance().getData(usersVo);
            if (uv == null) {
                JOptionPane.showMessageDialog(null, "اسم المستخدم او كلمة المرور غير صحيحة");
                imgProgress.setVisible(false);
//                System.err.println("enter valid user name and password");
            } else {
                DashboardController dashboardController =new DashboardController();
                dashboardController.usersVo=uv;
        btnLogin.getScene().getWindow().hide();
        
            imgProgress.setVisible(false);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Dashboard.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
            }
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        

    }   catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
