package iii.foreign_order.model;

import java.sql.Date;
import java.util.*;
import iii.order_main.model.OrderVO;


public interface FoDAO_interface {
	public void insert(FoVO foVO);
	public int update_off(FoVO foVO);
	public int update_on(FoVO foVO, String[] orderArray);
	public void delete(String foVO);
	public FoVO findByPrimaryKey(String foreign_order_id);
	public List<FoVO> getAll();
	public List<FoVO> get_FOs_Bind_FS();
	public List<OrderVO> findCarsFOs(String db_id,Date fo_date,String foreign_schedule_id); 
	public List<OrderVO> getOrderToShip(String db_id, String item_type);

}
