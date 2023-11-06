/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coffeql.connect;

import static com.mycompany.coffeql.connect.connectuser.conn;
import com.mycompany.coffeql.repository.Admin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;

/**
 *
 * @author MSI BRAVO 15
 */
public class Connectadmin {
    Connection conn = connectuser.getConnection();
    public HashSet<Admin> getAdmin() {
        HashSet<Admin> Admins = new HashSet<>();

        try {
            Statement sm = conn.createStatement();

            
            

            ResultSet rsAdmin = sm.executeQuery("SELECT * FROM admin");
            while (rsAdmin.next()) {
                Admin admin = new Admin();
                admin.setUsadmin(rsAdmin.getString("ten_tai_khoan"));
                admin.setPsadmin(rsAdmin.getString("mat_khau"));
                admin.setVaitro(rsAdmin.getString("vai_tro"));
                Admins.add(admin);
            }

        } catch (Exception e) {
            String err = e.getMessage();
        }
        for (Admin user : Admins) {
            System.out.println("Tên tài khoản: " + user.getUsadmin());
            System.out.println("Mật khẩu: " + user.getPsadmin());
            System.out.println("Vai trò: " + user.getVaitro());
        }
        return Admins;
    }
}
