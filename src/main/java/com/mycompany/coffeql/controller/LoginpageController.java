/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.coffeql.controller;

//import com.mycompany.coffeql.connect.LoginStatic;
import com.mycompany.coffeql.connect.LoginStatic;
import com.mycompany.coffeql.connect.connectuser;
import com.mycompany.coffeql.repository.Admin;
import com.mycompany.coffeql.repository.User;
import com.mycompany.coffeql.res.Stringvalue;
import java.io.IOException;
import java.net.URL;
import static java.util.Arrays.equals;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author MSI BRAVO 15
 */
public class LoginpageController implements Initializable {

    private static Scene scene;
    @FXML

    TextField user;
    @FXML

    TextField pass;

    @FXML

    Label check;

    @FXML

    Button btnlg;

    @FXML
    private AnchorPane pglg;
    private Stage stage;
    private HashSet<User> users;
    private HashSet<Admin> admins;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        users = LoginStatic.getusers();
        admins = LoginStatic.getAdmin();
        handleLogin();

        // TODO
    }

    @FXML
    public void handleLogin() {
        users = LoginStatic.getusers();
        btnlg.addEventHandler(ActionEvent.ACTION, eh -> {
            String username = user.getText();
            String password = pass.getText();
            System.out.println(username);
            System.out.println(password);
            boolean isAdmin = false;
            for (Admin admin : admins) {
                if (admin.getUsadmin().equals(username) && admin.getPsadmin().equals(password)) {
                    if (admin.getVaitro().equals("admin")) {
                        isAdmin = true;
                        break;
                    }

                }
            }

            for (User user : users) {
                if (user.getUser_name().equals(username) && user.getPassword().equals(password)) {
                    if (isAdmin) {
                        // Load the admin page
                        try {
                            Stage stage = new Stage();
                            Parent root = FXMLLoader.load(getClass().getResource(Stringvalue.ADMIN_PAGE));
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                            Stage loginStage = (Stage) pglg.getScene().getWindow();
                            loginStage.close();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginpageController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (user.getVai_tro().equals("QL")) {
                        // Load the quanly page
                        try {
                            Stage stage = new Stage();
                            Parent root = FXMLLoader.load(getClass().getResource(Stringvalue.QUANLY_PAGE));
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                            Stage loginStage = (Stage) pglg.getScene().getWindow();
                            loginStage.close();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginpageController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (user.getVai_tro().equals("MKT")) {
                        // Load the marketing page
                        try {
                            Stage stage = new Stage();
                            Parent roott = FXMLLoader.load(getClass().getResource(Stringvalue.MARKETING_PAGE));
                            Scene scene = new Scene(roott);
                            stage.setScene(scene);
                            stage.show();
                            Stage loginStage = (Stage) pglg.getScene().getWindow();
                            loginStage.close();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginpageController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (user.getVai_tro().equals("NVBH")) {
                        // Load the marketing page
                        try {
                            Stage stage = new Stage();
                            Parent roott = FXMLLoader.load(getClass().getResource(Stringvalue.NV_PAGE));
                            Scene scene = new Scene(roott);
                            stage.setScene(scene);
                            stage.show();
                            Stage loginStage = (Stage) pglg.getScene().getWindow();
                            loginStage.close();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginpageController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                }
            }

            if (!isAdmin && !users.contains(username)) {
                check.setText("Sai tài khoản hoặc mật khẩu");
            }
        });
    }
}

//    public void login() {
//        btnlg.addEventHandler(ActionEvent.ACTION, eh -> {
//            String username = user.getText();
//            String password = pass.getText();
//            System.out.println(username);
//
//            User user = login(username, password);
//            String usern = user.getUser_name();
//            String vaitro = user.getVai_tro();
//            System.out.println(usern);
//
//            if (user != null) {
//                if (vaitro.equalsIgnoreCase("admin")) {
//                    try {
//                        Stage stage = new Stage();
//                        Parent root = FXMLLoader.load(getClass().getResource(Stringvalue.ADMIN_PAGE));
//                        Scene scene = new Scene(root);
//                        stage.setScene(scene);
//                        stage.show();
//                        Stage loginStage = (Stage) pglg.getScene().getWindow();
//                        loginStage.close();
//                    } catch (IOException ex) {
//                        Logger.getLogger(LoginpageController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }else{
//                    check.setText("ban hong phai ad");
//                }
//
//            } else {
//                check.setText("that bai");
//            }
//        });
//
//    }

