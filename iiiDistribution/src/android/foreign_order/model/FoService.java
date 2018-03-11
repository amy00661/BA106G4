package android.foreign_order.model;

import java.sql.Date;
import java.util.List;

import android.order_main.model.OrderVO;



public class FoService {
	
	private FoDAO_interface dao;
	
	public FoService(){
		dao = new FoDAO();
	}
	
public FoVO addFo(String emp_ID, String order_ID, String foreign_schedule_ID){
		
		FoVO foVO = new FoVO();
		foVO.setEmp_id(emp_ID);
		foVO.setOrder_id(order_ID);
		foVO.setForeign_schedule_id(foreign_schedule_ID);
		dao.insert(foVO);
		
		return foVO;
	}
	
	public int update_on(FoVO foVO,String[] orderArray) {
		return dao.update_on(foVO,orderArray);
	}
	
	public int update_off(FoVO foVO){
		return dao.update_off(foVO);
	}

	public void deleteFo(String foreign_order_ID) {
		dao.delete(foreign_order_ID);
	}

	public FoVO getOneFo(String foreign_order_ID) {
		return dao.findByPrimaryKey(foreign_order_ID);
	}

	public List<FoVO> getAll() {
		return dao.getAll();
	}
	
	public List<FoVO> get_FOs_Bind_FS() {
		return dao.get_FOs_Bind_FS();
	}
	
	public List<OrderVO> findCarsFOs(String db_id, Date fo_date,String foreign_schedule_ID) {
		return dao.findCarsFOs(db_id, fo_date, foreign_schedule_ID);
		
	}

	public List<OrderVO> getUnShipOrders(String db_id, String item_type) {
		return dao.getOrderToShip(db_id,item_type);
	}
}
