package me.demo.wik2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Wik2DAO {

	private Connection conn;
	private ResultSet rs;
	
	public Wik2DAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/wik2";
			String dbID = "root";
			String dbPassword = "root";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";	//�����ͺ��̽� ����
	}
	
	public int getNext() {
		String SQL = "SELECT wik2ID FROM WIK2 ORDER BY wik2ID DESC";
		try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getInt(1) + 1;
			}
			return 1;	// ù ��° �Խù��� ���
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	//�����ͺ��̽� ����
	}
	
	public int getCount(int boardID) {
		String SQL = "SELECT COUNT(*) FROM WIK2 WHERE boardID = ?";
		try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, boardID);
				rs = pstmt.executeQuery();
				if (rs.next()) {	
					return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}	
	
	public int write(int boardID, String wik2Title, String userID, String wik2Content, String map) {
		String SQL = "INSERT INTO WIK2 VALUES (?, ?, ?, ?, ?, ?)";
		try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, boardID);
				pstmt.setInt(2,  getNext());
				pstmt.setString(3,  wik2Title);
				pstmt.setString(4,  userID);
				pstmt.setString(5,  getDate());
				pstmt.setString(6,  wik2Content);
				pstmt.setString(7, map);
				pstmt.setInt(8, 1);
				return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	public ArrayList<Wik2> getList(int pageNumber) {
		String SQL = "SELECT * FROM WIK2 WHERE boardID = ? AND wik2ID < ? AND wik2Available = 1 ORDER BY wik2ID DESC LIMIT 10";
		ArrayList<Wik2> list = new ArrayList<Wik2>();
		try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
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
		return list;	// �����ͺ��̽� ����
	}
	
	public ArrayList<Wik2> searchList(int boardID, int pageNumber, String search){
		String SQL = "SELECT * FROM WIK2 WHERE boardID = ? AND wik2ID < ? AND (wik2Title like ? OR wik2Content like ? OR map like ?) AND wik2Available = 1 ORDER BY wik2ID DESC LIMIT 10"; 
		ArrayList<Wik2> list = new ArrayList<Wik2>();
		try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, boardID);
				pstmt.setInt(2,  getNext() - (pageNumber - 1) * 10);
				pstmt.setString(3, "%"+search+"%");
				pstmt.setString(4, "%"+search+"%");
				pstmt.setString(5, "%"+search+"%");
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
		return list;	// �����ͺ��̽� ����
	}
	
	public boolean nextPage(int boardID, int pageNumber) {
		String SQL = "SELECT * FROM WIK2 WHERE wik2ID < ? AND wik2Available = 1";
		try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, boardID);
				pstmt.setInt(2, getNext() - (pageNumber - 1) * 10);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;	// �����ͺ��̽� ����
	}
	
	public boolean searchNextPage (int boardID, int pageNumber, String search) {
		String SQL = "SELECT * FROM WIK2 WHERE boardID = ? AND wik2ID < ? AND (wik2Title like ? OR wik2Content like ?) AND wik2Available = 1 ORDER BY wik2ID DESC LIMIT 10"; 
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardID);
			pstmt.setInt(2,  getNext() - (pageNumber - 1) * 10);
			pstmt.setString(3, "%"+search+"%");
			pstmt.setString(4, "%"+search+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;	// �����ͺ��̽� ����
	}
	
	public Wik2 getWik2(int wik2ID) {
		String SQL = "SELECT * FROM WIK2 WHERE wik2ID = ?";
		try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, wik2ID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					Wik2 wik2 = new Wik2();
					wik2.setBoardID(rs.getInt(1));
					wik2.setWik2ID(rs.getInt(2));
					wik2.setWik2Title(rs.getString(3));
					wik2.setUserID(rs.getString(4));
					wik2.setWik2Date(rs.getString(5));
					wik2.setWik2Content(rs.getString(6));
					wik2.setMap(rs.getString(7));
					wik2.setWik2Available(rs.getInt(8));
					return wik2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 		
	}
	
	public int update(int wik2ID, String wik2Title, String wik2Content, String map) {
		String SQL = "UPDATE WIK2 SET wik2Title = ?, wik2Content = ?, WHERE wik2ID = ?";
		try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1,  wik2Title);
				pstmt.setString(2,  wik2Content);
				pstmt.setString(3, map);
				pstmt.setInt(4, wik2ID);
				return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	// �����ͺ��̽� ����
	}
	
	public int delete(int wik2ID) {
		String SQL = "UPDATE WIK2 SET wik2Available = 0 WHERE wik2ID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, wik2ID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	// �����ͺ��̽� ����
	}
	
	public Wik2 getwik2(int wik2ID) {	//�ϳ��� �� ������ �ҷ����� �Լ�
		String SQL="SELECT * from BBS where bbsID = ?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, wik2ID);	//����ǥ
			rs=pstmt.executeQuery();	//select
			if(rs.next()) {	//����� �ִٸ�
				Wik2 wik2 = new Wik2();
				wik2.setWik2ID(rs.getInt(1));	//ù ��° ��� ��
				wik2.setWik2Title(rs.getString(2));
				wik2.setUserID(rs.getString(3));
				wik2.setWik2Date(rs.getString(4));
				wik2.setWik2Content(rs.getString(5));
				wik2.setWik2Available(rs.getInt(6));
				int wik2Count=rs.getInt(7);
				wik2.setWik2Count(wik2Count);
				wik2Count++;
				countUpdate(wik2Count,wik2ID);
				return wik2;	//6���� �׸��� wik2�ν��Ͻ��� �־� ��ȯ�Ѵ�.
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int countUpdate(int wik2Count, int wik2ID) {
		String SQL = "update wik2 set wik2Count = ? where wik2ID = ?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, wik2Count);	// ����ǥ�� ����
			pstmt.setInt(2, wik2ID);
			return pstmt.executeUpdate();	// insert,delete,update			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;	// �����ͺ��̽� ����
	}
}

















