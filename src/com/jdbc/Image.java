package com.jdbc;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

public class Image {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        // creating connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");

        // initializing statement
        Statement statement = connection.createStatement();

        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO image (img) VALUE(?)");


        //Inserting Blob type
        InputStream in = new FileInputStream("C:\\Users\\eyasu\\OneDrive\\Pictures\\profile.JPG");
        pstmt.setBlob(1, in);
        pstmt.execute();


        ImageIcon format = null;
        String fname = null;
        int s = 0;
        byte[] pimage = null;

        String sql = "SELECT img FROM image WHERE id=5";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            byte[] imagedata = rs.getBytes("img");
            format = new ImageIcon(imagedata);
//            Image mm = format.getImage();
        }
        System.out.println(format);
    }
}
