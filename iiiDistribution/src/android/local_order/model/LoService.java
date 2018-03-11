package android.local_order.model;

import java.sql.Date;
import java.util.List;

import android.order_main.model.OrderVO;



public class LoService {
	
	private LoDAO_interface dao;
	
	public LoService(){
		dao = new LoDAO();
	}
	
	public LoVO addLo(String emp_ID, String order_ID, String local_schedule_ID) {
		
		LoVO LoVO = new LoVO();
		
		LoVO.setEmp_ID(emp_ID);
		LoVO.setOrder_ID(order_ID);
		LoVO.setLocal_schedule_ID(local_schedule_ID);
		dao.insert(LoVO);
		
		return LoVO;
	}
	
	public int updateLo_off(LoVO loVO)
	{
		return dao.update_off(loVO);
	}
	
	public int update_on(LoVO loVO,String[] orderArray) {
		return dao.update_on(loVO,orderArray);
	}
	public void deleteLo(Date local_orderDate) {
		dao.delete(local_orderDate);
	}

	public LoVO getOneLo(String local_order_ID) {
		return dao.findByPrimaryKey(local_order_ID);
	}

	public List<LoVO> getAll() {
		return dao.getAll();
	}
	
	public List<LoVO> get_LOs_Bind_LS(String db_id){
		return dao.get_LOs_Bind_LS(db_id);
	}
	
	public List<OrderVO> findCarsLOs(String db_id,Date local_orderDate,String local_schedule_ID){
		return dao.findCarsLOs(db_id,local_orderDate,local_schedule_ID);
	}
	
	public List<OrderVO> getUnShipOrders(String db_id,String item_type){
		return dao.getOrderToShip(db_id,item_type);
	}
	
}
