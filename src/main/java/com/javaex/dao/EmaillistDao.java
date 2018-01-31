package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaex.vo.EmaillistVo;

@Repository	//�ڵ����� ���������� controller���� �������ְ� ������ �� �� repository���� ã�ƴ޶�� ��.
public class EmaillistDao {

	public void insert(EmaillistVo vo) {
		// 0. import java.sql.*; ctrl + shift + o
		Connection conn = null;
		PreparedStatement pstmt = null; // ������ ����

		try {
			// 1. JDBC ����̹� (Oracle) �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection ������
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// �߿� 3. SQL�� �غ� / ���ε� / ����
			String query = "INSERT INTO Emaillist VALUES(seq_emaillist_no.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getLastName());
			pstmt.setString(2, vo.getFirstName());
			pstmt.setString(3, vo.getEmail());

			int count = pstmt.executeUpdate();

			// �߿� 4.���ó��
			System.out.println(count + "�� ����Ϸ�");

		} catch (ClassNotFoundException e) {
			System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. �ڿ�����
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);

			}

		}

	}

	public List<EmaillistVo> getList() {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmaillistVo> list = new ArrayList<EmaillistVo>();
		try {
			// 1. JDBC ����̹� (Oracle) �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection ������
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL�� �غ� / ���ε� / ����
			String query = "SELECT no, last_name, first_name, email FROM emaillist";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.���ó��
			while (rs.next()) {
				EmaillistVo vo = new EmaillistVo();

				int no = rs.getInt("no");
				String lastName = rs.getString("last_name");
				String firstName = rs.getString("first_name");
				String email = rs.getString("email");

				vo.setNo(no);
				vo.setLastName(lastName);
				vo.setFirstName(firstName);
				vo.setEmail(email);

				list.add(vo);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. �ڿ�����
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return list;
	}
}
