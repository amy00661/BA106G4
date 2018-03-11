package iii.pro.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PictureToDatabase {

	
	private static final String INSERT_PIC=
			"UPDATE PROMOTION set promotion_picture=? where promotion_id=?";
	
	
	public static void main(String[] args)throws Exception{
		
		 String driver = "oracle.jdbc.driver.OracleDriver";
		 String url = "jdbc:oracle:thin:@localhost:1521:XE";
		 String userid = "GROUP4";
		 String passwd = "GROUP4";
		 Connection con = null;
		 PreparedStatement pstmt = null;
		
		 
		 for(int i=1;i<=6;i++){
		 
			try{
				String str="PMT00"+i;
				
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(INSERT_PIC);
				java.io.FileInputStream in = new java.io.FileInputStream("C:\\Users\\Java\\Desktop\\02.jpg");
				byte[] data = new byte[in.available()];
				in.read(data);
				in.close();
				
				
				
				pstmt.setBytes(1,data);
				pstmt.setString(2,str);
				pstmt.executeUpdate();
				
				System.out.println("第"+i+"張");
				
				}catch(ClassNotFoundException e){
					throw new RuntimeException("Couldn's load database"+e.getMessage());
				}catch(SQLException se){
					se.getMessage();
				}finally{
					if(pstmt!=null){
						try{
							pstmt.close();
						}catch(SQLException e){
							e.printStackTrace();
						}
					}
					if(con!=null){
						try{
							con.close();
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
		  }
		}
	}
		
		
	
