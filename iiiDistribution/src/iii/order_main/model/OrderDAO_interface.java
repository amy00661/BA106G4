package iii.order_main.model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public interface OrderDAO_interface {
	public String insert(OrderVO orderVO);
	public Integer update(OrderVO orderVO);
	public void delete (String order_id);
	public OrderVO findByPrimaryKey(String order_id);
	public List<OrderVO> getAll();
	public List<OrderVO> getPersonAll(String member_id);
	public List<OrderVO> getByDBAndEmp(String db_id, String emp_id);
	public LinkedHashSet<OrderVO> getByDBAndEmpOrderByTime(String db_id, String emp_id);
	public List<OrderVO> getAllByComposite(Map<String, String[]> map);
}
