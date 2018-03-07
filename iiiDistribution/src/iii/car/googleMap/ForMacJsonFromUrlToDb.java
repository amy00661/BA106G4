package iii.car.googleMap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ForMacJsonFromUrlToDb  {
	private static final String TEXT_SEARCH_URL = "https://data.tycg.gov.tw/api/v1/rest/datastore/bf55b21a-2b7c-4ede-8048-f75420344aed?format=json?limit=500";
	private static final String URL = "jdbc:oracle:thin:@192.168.56.3:1521:xe";
	private static final String USER = "JSON";
	private static final String PASSWORD = "JSON";
	private static final String SQL = "INSERT INTO JSON (JSON_ID, JSON_NAME) VALUES (JSON_seq.NEXTVAL , ?)";
	
	// jsonMethod : 1.�qTEXT_SEARCH_URL���줽����T(jsonArray); 2.�HString�s�Jjson_name���(clob);
	public void jsonMethod(){
		int index = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL);
			
			URL url = new URL(TEXT_SEARCH_URL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(10000);
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setUseCaches(false);

			URLConnection urlConn = HttpsUtil.getURLConnection(TEXT_SEARCH_URL);
			InputStream is =urlConn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String str;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}

			br.close();
			isr.close();
			is.close();

			con.disconnect();

			System.out.println(sb.toString());
			pstmt.setString(1, sb.toString());
			pstmt.executeQuery();
			System.out.println("NO."+ index++ + " jsonData get success!!" );
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// testDelayTask : 1.�]�w�Ƶ{���϶��ε����ɶ�
	private void testDelayTask() {
		Timer timer = new Timer();
		
		System.out.println("Period 1 min.");
		System.out.println("Start Time: " + new Date());
		
		timer.schedule(new MyTask(), 0, 60000);
		
		try {
			Thread.sleep(864000000);
		} catch (InterruptedException ie) {
			
		}
		timer.cancel();
		System.out.println("End Time: " + new Date());
	}
	
	// MyTask : 1.�Ƶ{�����椺�e
	public class MyTask extends TimerTask {

		@Override
		public void run() {
			System.out.println("����ɶ����G " + new Date());
			jsonMethod();
		}
	}
	
	public static void main(String args[]){
		
		ForMacJsonFromUrlToDb test = new ForMacJsonFromUrlToDb();
		test.testDelayTask();
		
	}
		
}
