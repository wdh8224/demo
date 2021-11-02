package me.demo.wik2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BmkDAO {

    private Connection conn;    //db�� �����ϴ� ��ü
    private ResultSet rs;

    public BmkDAO() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/wik2";
            String dbID = "root";
            String dbPassword = "1234";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Wik2> getList(String userID, int pageNumber) {
        String SQL = "SELECT * FROM wik2 WHERE wik2ID = (select wik2ID from bmk where userID = ?) ORDER BY wik2ID DESC LIMIT 10";
        ArrayList<Wik2> list = new ArrayList<Wik2>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, userID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Wik2 wik2 = new Wik2();
                wik2.setBoardID(rs.getInt(1));
                wik2.setWik2ID(rs.getInt(2));
                wik2.setWik2Title(rs.getString(3));
                wik2.setUserID(rs.getString(4));
                wik2.setWik2Date(rs.getString(5));
                wik2.setWik2Content(rs.getString(6));
                wik2.setMap(rs.getString(7));
                wik2.setWik2Available(rs.getInt(8));
                list.add(wik2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; //�����ͺ��̽� ����
    }


    public int write(String userID, int wik2ID) {
        String SQL = "INSERT INTO bmk VALUES(?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, wik2ID);
            pstmt.setString(2, userID);
            pstmt.executeUpdate();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; //�����ͺ��̽� ����
    }

    public ArrayList<Bmk> getBmk(String userID, int wik2ID) {
        String SQL = "SELECT * FROM bmk WHERE userID = ? AND wik2ID = ?";
        ArrayList<Bmk> list = new ArrayList<Bmk>(); //�������� Bmk �ؾߵǴµ�...?
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, userID);
            pstmt.setInt(2, wik2ID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Bmk bmk = new Bmk();
                bmk.setWik2ID(rs.getInt(1));
                bmk.setUserID(rs.getString(2));
                list.add(bmk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int delete(String userID, int wik2ID) {
        String SQL = "DELETE FROM bmk WHERE wik2ID = ? AND userID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, wik2ID);
            pstmt.setString(2, userID);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // �����ͺ��̽� ����
    }
}