package android.order_main.model;

import java.util.List;

public interface OrderDAO_interface {
	public Integer insert(OrderVO orderVO);
	public Integer update(OrderVO orderVO);
	public void delete (String order_id);
	public OrderVO findByPrimaryKey(String order_id);
	public List<OrderVO> getAll();
	
	
	List<OrderVO> findByMem(String mem_id);
	List<OrderVO> findByEmp(String emp_id);
	Integer UpdateStatusDbByPrimaryKey(String order_status, String db_id, String order_id);
	Integer insertOneWithOrderIDLo(String emp_id, String order_id);
	Integer insertOneWithOrderIDFo(String emp_id, String order_id);
}
