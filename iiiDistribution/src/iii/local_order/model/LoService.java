package iii.local_order.model;

import java.sql.Date;
import java.util.List;

import iii.order_main.model.OrderVO;



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
	
	public LoVO updateLo(String local_order_ID , String emp_ID, String order_ID, String local_schedule_ID)
		{

		LoVO LoVO = new LoVO();

		LoVO.setLocal_order_ID(local_order_ID);
		LoVO.setEmp_ID(emp_ID);
		LoVO.setOrder_ID(order_ID);
		LoVO.setLocal_schedule_ID(local_schedule_ID);
		dao.update(LoVO);

		return LoVO;
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

	public List<LoVO> getLOsByDate(Date local_orderDate){
		return dao.findByLoDate(local_orderDate);
	}
	
	public List<OrderVO> getUnShipOrders(String db_id,String item_type,String order_status){
		return dao.getOrderToShip(db_id,item_type, order_status);
	}
	
}
