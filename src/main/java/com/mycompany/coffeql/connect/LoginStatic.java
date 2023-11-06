/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coffeql.connect;

import com.mycompany.coffeql.repository.Admin;
import com.mycompany.coffeql.repository.User;
import java.util.HashSet;

/**
 *
 * @author MSI BRAVO 15
 */
public class LoginStatic {
    public static HashSet<User> getusers() {
        connectuser Connect = new connectuser();
        return Connect.getusers();
    }
    public static HashSet<Admin> getAdmin(){
        Connectadmin cn = new Connectadmin();
        return  cn.getAdmin();
    }
}
