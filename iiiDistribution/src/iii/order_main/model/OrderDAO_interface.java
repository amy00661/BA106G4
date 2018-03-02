package iii.order_main.model;

import java.util.List;

public interface OrderDAO_interface {
	public Integer insert(OrderVO orderVO);
	public Integer update(OrderVO orderVO);
	public void delete (String order_id);
	public OrderVO findByPrimaryKey(String order_id);
	public List<OrderVO> getAll();
}
