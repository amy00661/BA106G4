package iii.car.googleMap;

import java.io.*;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForMacJsonFromDbToFile {
	private static final String URL = "jdbc:oracle:thin:@192.168.56.3:1521:xe";
	private static final String USER = "JSON";
	private static final String PASSWORD = "JSON";
	private static final String SQL = "INSERT INTO JSON (JSON_ID, JSON_NAME) VALUES (JSON_seq.NEXTVAL , ?)";
	
	public static void main(String args[]){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PrintWriter pw = null;
		int count = 1;
		  while(count<=3000){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(SQL);
				
				for(long i=1 ;i<200000000L; i++){}
				
				pstmt.setInt(1, count);
				rs = pstmt.executeQuery();
				rs.next();
				Clob clob = rs.getClob(1);
				String sb = readString(clob);
				
				File Floder = new File("c:\\javawork\\json\\");
				if(!Floder.exists()) {
					Floder.mkdirs();
				}
				File file = new File(Floder, count + ".txt");
				pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
				pw.println(sb);
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					pw.close();
					pstmt.close();
					rs.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println(count + ". success");
			count++;
		}
	}
	
	public static String readString(Clob clob) throws IOException, SQLException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(clob.getCharacterStream());
		String str;
		while((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		return sb.toString();
	}
}
