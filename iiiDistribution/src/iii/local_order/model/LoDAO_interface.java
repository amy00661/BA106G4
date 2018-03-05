package iii.local_order.model;

import java.sql.Date;
import java.util.*;

import iii.order_main.model.OrderVO;

public interface LoDAO_interface {
	public void insert(LoVO loVO);
	public int update_off(LoVO loVO);
	public int update_on(LoVO loVO, String[] orderArray);
	public void delete(Date local_orderDate);
	public LoVO findByPrimaryKey(String local_order_id);
	public List<LoVO> getAll();
	public List<LoVO> get_LOs_Bind_LS(String db_id);
	public List<OrderVO> findCarsLOs(String db_id,Date local_orderDate,String local_schedule_ID);
	public List<OrderVO> getOrderToShip(String db_id,String item_type);
}
