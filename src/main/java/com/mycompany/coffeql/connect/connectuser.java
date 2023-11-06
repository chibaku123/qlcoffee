/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coffeql.connect;

import com.mycompany.coffeql.repository.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;

/**
 *
 * @author MSI BRAVO 15
 */
public class connectuser {

    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyCoffee;encrypt=true;trustServerCertificate=true;";
    public static Connection conn;

    public connectuser() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, "sa", "1234");
            System.out.println("ket noi thanh cong");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return conn;
    }

    public HashSet<User> getusers() {
        HashSet<User> users = new HashSet<>();

        try {
            Statement sm = conn.createStatement();

            ResultSet rs = sm.executeQuery("SELECT * FROM nhan_vien");
            while (rs.next()) {
                User user = new User();
                user.setUser_name(rs.getString("ten_tai_khoan"));
                user.setPassword(rs.getString("mat_khau"));
                user.setVai_tro(rs.getString("ma_chuc_vu"));
                users.add(user);
            }

            ResultSet rsAdmin = sm.executeQuery("SELECT * FROM admin");
            while (rsAdmin.next()) {
                User user = new User();
                user.setUser_name(rsAdmin.getString("ten_tai_khoan"));
                user.setPassword(rsAdmin.getString("mat_khau"));
                user.setVai_tro(rsAdmin.getString("vai_tro"));
                users.add(user);
            }

        } catch (Exception e) {
            String err = e.getMessage();
        }
        for (User user : users) {
            System.out.println("Tên tài khoản: " + user.getUser_name());
            System.out.println("Mật khẩu: " + user.getPassword());
            System.out.println("Vai trò: " + user.getVai_tro());
        }
        return users;
    }

//    public User login(String username, String password) {
//        User user = null;
//        try{
//            Statement sm = conn.createStatement();
//
//            ResultSet rs = sm.executeQuery("SELECT * FROM nhan_vien WHERE ten_tai_khoan = '" + username + "' AND mat_khau = '" + password + "'");
//            if (rs.next()) {
//                user = new User();
//                user.setUser_name(rs.getString("ten_tai_khoan"));
//                user.setPassword(rs.getString("mat_khau"));
//                user.setChuc_vu(rs.getString("ma_chuc_vu"));
//
//                // Get data from the admin table
//                
//            }
//            ResultSet rsAdmin = sm.executeQuery("SELECT * FROM admin WHERE ten_tai_khoan = '" + username + "' AND mat_khau = '" + password + "'");
//                if (rsAdmin.next()) {
//                    user = new User();
//                    // Assign data from the admin table to the user
//                    user.setUser_name(rsAdmin.getString("ten_tai_khoan"));
//                    user.setPassword(rsAdmin.getString("mat_khau"));
//                    user.setVai_tro(rsAdmin.getString("chuc_vu"));
//
//                    System.out.println(user);
//                }
//        } catch (Exception e) {
//            String err = e.getMessage();
//        }
//        return user;
//    }
}
