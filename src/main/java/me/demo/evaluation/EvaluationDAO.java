package me.demo.evaluation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EvaluationDAO {

	private Connection conn;	//db�� �����ϴ� ��ü
	private ResultSet rs;
	
	public EvaluationDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/wik2";
			String dbID = "root";
			String dbPassword = "1234";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getDate() {
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ""; //�����ͺ��̽� ����
	}
	public int write(int wik2ID, String userID,int likeWork, int sosoWork, int badWork) {
		String SQL = "INSERT INTO evaluation VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, wik2ID);
			pstmt.setString(2, userID);
			pstmt.setInt(3, likeWork);
			pstmt.setInt(4, sosoWork);
			pstmt.setInt(5, badWork);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //�����ͺ��̽� ����
	}
	public ArrayList<Evaluation> whereList(int wik2ID, String userID){
		String SQL = "SELECT * FROM evaluation WHERE wik2ID = ? AND userID =?"; 
		ArrayList<Evaluation> list = new ArrayList<Evaluation>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, wik2ID);
			pstmt.setString(2, userID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Evaluation eva = new Evaluation();
				eva.setWik2ID(rs.getInt(1));
				eva.setUserID(rs.getString(2));
				eva.setLikeWork(rs.getInt(3));
				eva.setSosoWork(rs.getInt(4));
				eva.setBadWork(rs.getInt(5));
				list.add(eva);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list; //�����ͺ��̽� ����
	}
	
	public ArrayList<Evaluation> getList(int wik2ID){
		String SQL = "SELECT * FROM evaluation WHERE wik2ID = ?"; 
		ArrayList<Evaluation> list = new ArrayList<Evaluation>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, wik2ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Evaluation eva = new Evaluation();
				eva.setWik2ID(rs.getInt(1));
				eva.setUserID(rs.getString(2));
				eva.setLikeWork(rs.getInt(3));
				eva.setSosoWork(rs.getInt(4));
				eva.setBadWork(rs.getInt(5));
				list.add(eva);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list; //�����ͺ��̽� ����
	}
	
	public Evaluation getEvaluation(int wik2ID) {
		String SQL = "SELECT * FROM evaluation WHERE wik2ID = ?";
		ArrayList<Evaluation>list = new ArrayList<Evaluation>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  wik2ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Evaluation eva = new Evaluation();
				eva.setWik2ID(rs.getInt(1));
				eva.setUserID(rs.getString(2));
				eva.setLikeWork(rs.getInt(3));
				eva.setSosoWork(rs.getInt(4));
				eva.setBadWork(rs.getInt(5));
				list.add(eva);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int update(int wik2ID, String userID,int likeWork, int sosoWork, int badWork) {
		String SQL = "UPDATE evaluation SET likeWork=?, sosoWork=?,badWork=? WHERE wik2ID = ? AND userID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, likeWork);
			pstmt.setInt(2, sosoWork);
			pstmt.setInt(3, badWork);
			pstmt.setInt(4, wik2ID);
			pstmt.setString(5, userID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // �����ͺ��̽� ����
	}
	
	public int delete(int wik2ID, String userID) {
		String SQL = "DELETE FROM evaluation WHERE wik2ID = ? AND userID = ?";
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
