package iii.local_order.model;

import java.sql.Date;
import java.util.*;

import iii.order_main.model.OrderVO;

public interface LoDAO_interface {
	public void insert(LoVO loVO);
	public void update(LoVO loVO);
	public void delete(Date local_orderDate);
	public LoVO findByPrimaryKey(String local_order_id);
	public List<LoVO> getAll();
	public List<LoVO> findByLoDate(Date local_orderDate);
	public List<OrderVO> getOrderToShip(String db_id,String item_type,String order_status);
}
