package iii.car.googleMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ForMacJsonFromFileToDb  {
	private static final String URL = "jdbc:oracle:thin:@192.168.56.3:1521:xe";
	private static final String USER = "JSON";
	private static final String PASSWORD = "JSON";
	private static final String SQL = "INSERT INTO JSON (JSON_ID, JSON_NAME) VALUES (JSON_seq.NEXTVAL , ?)";
	
	// fileToDb : 1.fileToDb;
	public void fileToDb(){
		int index = 1;
		while(true){
			Connection conn = null;
			PreparedStatement pstmt = null;
			BufferedReader br = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = conn.prepareStatement(SQL);
				
				for(long i=1 ;i<400000000L; i++){}
				
				String str;
				File file = new File("/Users/myforestory/Desktop/jsonCar/" + index + ".txt");
				System.out.println(file);
				if(file.exists())
					br = new BufferedReader(new FileReader(file));
	            StringBuilder sb = new StringBuilder();
	            while ((str = br.readLine()) != null)
	            	sb.append(str);
	   
				System.out.println(sb.toString());
				pstmt.setString(1, sb.toString());
				pstmt.executeQuery();
				
	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					br.close();
					pstmt.close();
					conn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			index++;
		}
	}
	
	public static void main(String args[]){
		
		ForMacJsonFromFileToDb test = new ForMacJsonFromFileToDb();
		test.fileToDb();
	}
}
