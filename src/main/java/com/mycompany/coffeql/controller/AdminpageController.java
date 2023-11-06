/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.coffeql.controller;

import com.mycompany.coffeql.connect.connectuser;
import static com.mycompany.coffeql.connect.connectuser.conn;
import com.mycompany.coffeql.connect.newUserconnect;
import com.mycompany.coffeql.repository.newUser;
import com.mycompany.coffeql.res.Stringvalue;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI BRAVO 15
 */
public class AdminpageController implements Initializable {

    @FXML
    Button logout;
    @FXML
    private AnchorPane adpg;
    @FXML
    TextField MNV;
    @FXML
    TextField TNV;
    @FXML
    TextField USER;
    @FXML
    TextField PASS;
    @FXML
    TextField SDT;
//    @FXML
//    TextField MCV;
    @FXML
    TextField GT;
    @FXML
    TextField DC;
//    @FXML
//    TextField MCN;
    @FXML
    TextField SDH;
//    @FXML
//    TextField MLLV;
    @FXML
    TextField NS;
    @FXML
    TextField EMAIL;

    @FXML
    Button btnthem;
    @FXML
    Button btnsua;
    @FXML
    Button btnxoa;

    @FXML
    Button btnreset;

    @FXML
    TableView<newUser> tbview;

    @FXML
    ComboBox<?> CBMCV;
    @FXML
    ComboBox<?> CBMLLV;
    @FXML
    ComboBox<?> CBMCN;

    TabPane tabuser;
//    MenuItem QL;
//    @FXML 
//    MenuItem MKT;
//    @FXML 
//    MenuItem NV;

    @FXML

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
        combobox();
        combobox1();
        combobox2();

        logout();
        btnthem.setOnAction(this::onBtnThemClick);
        btnsua.setOnAction(this::update);
        reset();

    }

    public void show() {
        newUserconnect nus = new newUserconnect();
        ObservableList<newUser> ls = nus.findall();
        ObservableList<TableColumn<newUser, ?>> colsn = tbview.getColumns();
        colsn.get(0).setCellValueFactory(new PropertyValueFactory("manv"));
        colsn.get(1).setCellValueFactory(new PropertyValueFactory("mallv"));
        colsn.get(2).setCellValueFactory(new PropertyValueFactory("tennv"));
        colsn.get(3).setCellValueFactory(new PropertyValueFactory("user"));
        colsn.get(4).setCellValueFactory(new PropertyValueFactory("pass"));
        colsn.get(5).setCellValueFactory(new PropertyValueFactory("macv"));
        colsn.get(6).setCellValueFactory(new PropertyValueFactory("gioitinh"));
        colsn.get(7).setCellValueFactory(new PropertyValueFactory("ngaysinh"));
        colsn.get(8).setCellValueFactory(new PropertyValueFactory("diachi"));
        colsn.get(9).setCellValueFactory(new PropertyValueFactory("sdt"));
        colsn.get(10).setCellValueFactory(new PropertyValueFactory("email"));
        colsn.get(11).setCellValueFactory(new PropertyValueFactory("macn"));
        colsn.get(12).setCellValueFactory(new PropertyValueFactory("sldh"));
        tbview.setItems(ls);

    }

    public void combobox() {
        Connection conn = connectuser.getConnection();
        ObservableList ls1 = FXCollections.observableArrayList();

        try {
            Statement sm = conn.createStatement();
            ResultSet rs = sm.executeQuery("select * from lich_lam_viec");
            while (rs.next()) {

                String item = rs.getString("ma_lich_lam_viec");

                ls1.add(item);
            }
            CBMLLV.setItems(ls1);

        } catch (Exception e) {
            String err = e.getMessage();

        }
    }

    public void combobox1() {
        Connection conn = connectuser.getConnection();
        ObservableList ls1 = FXCollections.observableArrayList();

        try {
            Statement sm = conn.createStatement();
            ResultSet rs = sm.executeQuery("select * from chuc_vu");
            while (rs.next()) {

                String item = rs.getString("ma_chuc_vu");

                ls1.add(item);
            }
            CBMCV.setItems(ls1);

        } catch (Exception e) {
            String err = e.getMessage();

        }
    }

    public void combobox2() {
        Connection conn = connectuser.getConnection();
        ObservableList ls2 = FXCollections.observableArrayList();

        try {
            Statement sm = conn.createStatement();
            ResultSet rs = sm.executeQuery("select * from chi_nhanh");
            while (rs.next()) {

                String item = rs.getString("ma_chi_nhanh");

                ls2.add(item);
            }
            CBMCN.setItems(ls2);

        } catch (Exception e) {
            String err = e.getMessage();

        }
    }

    public void onBtnThemClick(ActionEvent event) {
        String Slect = CBMLLV.getSelectionModel().getSelectedItem().toString();
        String Slect1 = CBMCV.getSelectionModel().getSelectedItem().toString();
        String Slect2 = CBMCN.getSelectionModel().getSelectedItem().toString();
        String sql = "INSERT INTO nhan_vien (ma_nhan_vien, ma_lich_lam_viec, ten_nhan_vien, ten_tai_khoan, mat_khau, ma_chuc_vu, gioi_tinh, ngay_sinh, dia_chi, so_dien_thoai, email, ma_chi_nhanh, sl_don_hang)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thêm nhân viên");
        alert.setHeaderText("Bạn có chắc chắn muốn thêm thông tin nhân viên này?");
        alert.setContentText("Nhấn OK để xác nhận hoặc nhấn Cancel để hủy bỏ.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                Connection conn = connectuser.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, MNV.getText());
                stmt.setString(2, Slect);
                stmt.setString(3, TNV.getText());
                stmt.setString(4, USER.getText());
                stmt.setString(5, PASS.getText());
                stmt.setString(6, Slect1);
                stmt.setString(7, GT.getText());
                stmt.setString(8, NS.getText());
                stmt.setString(9, DC.getText());
                stmt.setString(10, SDT.getText());
                stmt.setString(11, EMAIL.getText());
                stmt.setString(12, Slect2);
                stmt.setString(13, SDH.getText());

                stmt.executeUpdate();
                show();

                System.out.println("Thêm nhân viên thành công!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void update(ActionEvent event) {
//        String Slect = CBMLLV.getPromptText();
//        String Slect1 =CBMCV.getPromptText();
//        String Slect2 = CBMCN.getPromptText();

        String Slect11 = CBMLLV.getSelectionModel().getSelectedItem().toString();
        String Slect12 = CBMCV.getSelectionModel().getSelectedItem().toString();
        String Slect13 = CBMCN.getSelectionModel().getSelectedItem().toString();

        String sql1 = "UPDATE nhan_vien SET ma_lich_lam_viec = ?, ten_nhan_vien = ?, ten_tai_khoan = ?, mat_khau = ?, ma_chuc_vu = ?, gioi_tinh = ?, ngay_sinh = ?, dia_chi = ?, so_dien_thoai = ?, email = ?, ma_chi_nhanh = ?, sl_don_hang = ? WHERE ma_nhan_vien = ?";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cập nhật nhân viên");
        alert.setHeaderText("Bạn có chắc chắn muốn cập nhật thông tin nhân viên này?");
        alert.setContentText("Nhấn OK để xác nhận hoặc nhấn Cancel để hủy bỏ.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {

                PreparedStatement stmt = conn.prepareStatement(sql1);

                stmt.setString(1, Slect11);
                stmt.setString(2, TNV.getText());
                stmt.setString(3, USER.getText());
                stmt.setString(4, PASS.getText());
                stmt.setString(5, Slect12);
                stmt.setString(6, GT.getText());
                stmt.setString(7, NS.getText());
                stmt.setString(8, DC.getText());
                stmt.setString(9, SDT.getText());
                stmt.setString(10, EMAIL.getText());
                stmt.setString(11, Slect13);
                stmt.setString(12, SDH.getText());
                stmt.setString(13, MNV.getText());

                stmt.executeUpdate();

                show();

                System.out.println("Sua thanh cong");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void clickshow(MouseEvent event) {
        newUser ur = tbview.getSelectionModel().getSelectedItem();

        MNV.setText(ur.getManv());
        //CBMLLV.setPromptText("Chon Lich Lam");
        //CBMLLV.setPromptText(ur.getMallv());
        //CBMLLV.setPromptText(ur.getMallv());
        TNV.setText(ur.getTennv());
        USER.setText(ur.getUser());
        PASS.setText(ur.getPass());
//        CBMCV.setPromptText(ur.getMacv());
//        MCV.setText(ur.getMacv());
        GT.setText(ur.getGioitinh());
        DC.setText(ur.getDiachi());
        NS.setText(new SimpleDateFormat("yyyy-MM-dd").format(ur.getNgaysinh()));
        SDT.setText(ur.getSdt());
        EMAIL.setText(ur.getEmail());
//        CBMCN.setPromptText(ur.getMacn());
//        MCN.setText(ur.getMacn());
        SDH.setText(ur.getSldh());
        CBMLLV.setValue(null);
        CBMCV.setValue(null);
        CBMCN.setValue(null);
//        CBMLLV.setPromptText("Chon Lich Lam");
//        CBMCV.setPromptText("Chon Chuc Vu");
//        CBMCN.setPromptText("Chon Chi Chanh");

    }

    public void logout() {
        logout.addEventHandler(ActionEvent.ACTION, eh -> {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource(Stringvalue.LOGIN_PAGE));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                Stage loginStage = (Stage) adpg.getScene().getWindow();
                loginStage.close();
            } catch (IOException ex) {
                Logger.getLogger(LoginpageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void reset() {
        btnreset.addEventHandler(ActionEvent.ACTION, eh -> {
            MNV.setText("");
            TNV.setText("");
            USER.setText("");
            PASS.setText("");
            SDT.setText("");
            GT.setText("");
            DC.setText("");
            NS.setText("");
            EMAIL.setText("");
            SDH.setText("");
            CBMLLV.setValue(null);
            CBMCV.setValue(null);
            CBMCN.setValue(null);
//        CBMLLV.setPromptText("Chon Lich Lam");
//        CBMCV.setPromptText("Chon Chuc Vu");
//        CBMCN.setPromptText("Chon Chi Chanh");
//        CBMLLV.setPromptText("Hãy chọn");
//    CBMCV.setPromptText("Hãy chọn");
//    CBMCN.setPromptText("Hãy chọn");
        });

    }

}
