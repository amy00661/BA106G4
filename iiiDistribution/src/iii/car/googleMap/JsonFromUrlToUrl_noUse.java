package iii.car.googleMap;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonFromUrlToUrl_noUse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TEXT_SEARCH_URL = "https://data.tycg.gov.tw/api/v1/rest/datastore/bf55b21a-2b7c-4ede-8048-f75420344aed?format=json?limit=500";

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			PrintWriter out = res.getWriter();
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

			/******************** JSON ********************/

			JSONObject jObj = new JSONObject(sb.toString());
			JSONObject jObj2 = jObj.getJSONObject("result");
			JSONArray jArray = jObj2.getJSONArray("records");
//			for (int i = 0; i < jArray.length(); i++) {
//				Double speed = 0.0;
//				JSONObject data = jArray.getJSONObject(i);
//				String spe = data.getString("Speed");
//				if(!spe.equals(""))
//					speed = Double.valueOf(spe);
//				if(speed>0){
//				String busID = data.getString("BusID");
//				String routeId = data.getString("RouteID");
//				
//				
//				String latitude = data.getString("Latitude");
//				Double lat = 0.0; 
//				if(!latitude.equals(""))
//					lat = Double.valueOf(latitude);
//
//				String longitude = data.getString("Longitude");
//				Double lng = 0.0;
//				if(!longitude.equals(""))
//					lng = Double.valueOf(longitude);
//					System.out.println("BusID = " + busID);
//					System.out.println("Speed = " + speed);
//					System.out.println("Latitude = " + lat);
//					System.out.println(lat.getClass());
//					System.out.println("Longitude = " + lng);
//					System.out.println(lng.getClass());
//					System.out.println("RouteID = " + routeId);
//					System.out.println("====================================");
//				}
//			}
			out.print(jArray);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
