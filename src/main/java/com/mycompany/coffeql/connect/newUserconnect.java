/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coffeql.connect;

import com.mycompany.coffeql.repository.newUser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MSI BRAVO 15
 */
public class newUserconnect {

    public ObservableList<newUser> findall() {
        Connection conn = connectuser.getConnection();
        ObservableList<newUser> ls = FXCollections.observableArrayList();
        try {
            Statement sm = conn.createStatement();
            ResultSet rs = sm.executeQuery("select * from nhan_vien");
            while (rs.next()) {
                newUser item = new newUser();
                item.setManv(rs.getString("ma_nhan_vien"));
                item.setMallv(rs.getString("ma_lich_lam_viec"));
                item.setTennv(rs.getString("ten_nhan_vien"));
                item.setUser(rs.getString("ten_tai_khoan"));
                item.setPass(rs.getString("mat_khau"));
                item.setMacv(rs.getString("ma_chuc_vu"));
                item.setGioitinh(rs.getString("gioi_tinh"));
                item.setNgaysinh(rs.getDate("ngay_sinh"));
                item.setDiachi(rs.getString("dia_chi"));
                item.setSdt(rs.getString("so_dien_thoai"));
                item.setEmail(rs.getString("email"));
                item.setMacn(rs.getString("ma_chi_nhanh"));
                item.setSldh(rs.getString("sl_don_hang"));
                ls.add(item);
            }
            
        } catch (Exception e) {
            String err = e.getMessage();

        }
        return ls;

    }
}
