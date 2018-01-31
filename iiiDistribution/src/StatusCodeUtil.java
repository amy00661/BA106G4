import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StatusCodeUtil")
public class StatusCodeUtil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static HashMap<String,String> DB = new HashMap<String, String>();
	public static HashMap<String,String> arriveTime = new HashMap<String, String>();
	public static HashMap<String,String> empTitle = new HashMap<String, String>();
	public static HashMap<String,String> opinionType = new HashMap<String, String>();
	public static HashMap<String,String> opinionStatus = new HashMap<String, String>();
	public static HashMap<String,String> empStatus = new HashMap<String, String>();
	public static HashMap<String,String> memStatus = new HashMap<String, String>();
	public static HashMap<String,String> orderType = new HashMap<String, String>();
	public static HashMap<String,String> vehStatus = new HashMap<String, String>();
	
    @Override
	public void init() throws ServletException {
    	//    	貨運中心狀態碼
    	DB.put("DB01", "總公司");
    	DB.put("DB02", "基隆市集貨站");
    	DB.put("DB03", "台北市集貨站");
    	DB.put("DB04", "新北市集貨站");
    	DB.put("DB05", "桃園市集貨站");
    	DB.put("DB06", "新竹市集貨站");
    	DB.put("DB07", "新竹縣集貨站");
    	DB.put("DB08", "苗栗縣集貨站");
    	DB.put("DB09", "台中市集貨站");
    	DB.put("DB10", "彰化縣集貨站");
    	DB.put("DB11", "南投縣集貨站");
    	DB.put("DB12", "雲林縣集貨站");
    	DB.put("DB13", "嘉義市集貨站");
    	DB.put("DB14", "嘉義縣集貨站");
    	DB.put("DB15", "台南市集貨站");
    	DB.put("DB16", "高雄市集貨站");
    	DB.put("DB17", "屏東縣集貨站");
    	DB.put("DB18", "台東縣集貨站");
    	DB.put("DB19", "花蓮縣集貨站");
    	DB.put("DB20", "宜蘭縣集貨站");
    	DB.put("DB21", "澎湖縣集貨站");
    	DB.put("DB22", "金門縣集貨站");
    	DB.put("DB23", "連江縣集貨站");
    	
    	//    	配送時段
    	arriveTime.put("01", "不指定");
    	arriveTime.put("02", "13時以前");
    	arriveTime.put("03", "14時到18時");
    	
    	//    	職稱
    	empTitle.put("head_office_manager", "總公司經理");
    	empTitle.put("branch_manager", "地區經理");
    	empTitle.put("customer_service", "客服人員");
    	empTitle.put("driver", "司機");
    	empTitle.put("IT", "資訊人員");
    	empTitle.put("warehouse_staff", "倉管人員");
    	
    	//		意見類型
    	opinionType.put("100", "包裹查詢");
    	opinionType.put("200", "意見反應");
    	opinionType.put("300", "表達感謝");
    	opinionType.put("400", "其他");
    		
    	//    	意見狀態    		
    	opinionStatus.put("00", "未處理");
    	opinionStatus.put("01", "處理中");
    	opinionStatus.put("02", "已解決");
    		
    	//    	員工狀態    		
    	empStatus.put("01", "在職");
    	empStatus.put("00", "離職");
    	
    	//    	會員狀態   		
    	memStatus.put("01", "啟用");	
    	memStatus.put("00", "停用");
    		
    	//    	訂單運輸類型
    	orderType.put("G", "常溫");
    	orderType.put("T", "低溫");
    	orderType.put("M", "醫藥");
    	
    	//    	車輛狀態
    	vehStatus.put("00", "未出車");
    	vehStatus.put("01", "已出車");
    	
		super.init();
	}

	public StatusCodeUtil() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
