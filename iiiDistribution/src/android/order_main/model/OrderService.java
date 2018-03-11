package android.order_main.model;

import java.sql.Timestamp;
import java.util.List;

public class OrderService {
	
	private OrderDAO_interface dao;
	
	public OrderService() {
		dao = new OrderDAO();
	}
	
	public OrderVO addOrder(String emp_id, String mem_id, String db_id, String order_status, String payment_type, Double fee, Double extrafee, 
			 				Double item_size, Double item_weight, String item_type, String receiver_name, String receiver_tel, String receiver_cell,
							String receiver_city, String receiver_county, String receiver_address, String receiver_mail, String sender_name, String sender_tel,
							String sender_cell, String sender_city, String sender_county, String sender_address, Timestamp expected_time, String order_note){
		
		OrderVO orderVO = new OrderVO();
		
		orderVO.setEmp_id(emp_id);
		orderVO.setMem_id(mem_id);
		orderVO.setDb_id(db_id);
		orderVO.setOrder_status(order_status);
		orderVO.setPayment_type(payment_type);
		orderVO.setFee(fee);
		orderVO.setExtrafee(extrafee);
		orderVO.setItem_size(item_size);
		orderVO.setItem_weight(item_weight);
		orderVO.setItem_type(item_type);
		orderVO.setReceiver_name(receiver_name);
		orderVO.setReceiver_tel(receiver_tel);
		orderVO.setReceiver_cell(receiver_cell);
		orderVO.setReceiver_city(receiver_city);
		orderVO.setReceiver_county(receiver_county);
		orderVO.setReceiver_address(receiver_address);
		orderVO.setReceiver_mail(receiver_mail);
		orderVO.setSender_name(sender_name);
		orderVO.setSender_tel(sender_tel);
		orderVO.setSender_cell(sender_cell);
		orderVO.setSender_city(sender_city);
		orderVO.setSender_county(sender_county);
		orderVO.setSender_address(sender_address);
		orderVO.setExpected_time(expected_time);
		orderVO.setOrder_note(order_note);
		
		dao.insert(orderVO);
		
		return orderVO;
	}
	
//	public OrderVO updateOrder(String order_id, String emp_id, String mem_id, String db_id, String payment_type, Double fee, Double extrafee, Double item_size, Double item_weight, 
//							   String item_type, String order_status, Timestamp create_time, String receiver_name, String receiver_tel, String receiver_country,
//							   String receiver_city, String receiver_county, String receiver_address, String receiver_mail, String sender_name, String sender_tel,
//							   String sender_mail, Timestamp expected_time, String order_note, Timestamp order_updatetime){
//		OrderVO orderVO = new OrderVO();
//		
//		orderVO.setOrder_id(order_id);
//		orderVO.setEmp_id(emp_id);
//		orderVO.setMem_id(mem_id);
//		orderVO.setDb_id(db_id);
//		orderVO.setPayment_type(payment_type);
//		orderVO.setFee(fee);
//		orderVO.setExtrafee(extrafee);
//		orderVO.setItem_size(item_size);
//		orderVO.setItem_weight(item_weight);
//		orderVO.setItem_type(item_type);
//		orderVO.setOrder_status(order_status);
//		orderVO.setCreate_time(create_time);
//		orderVO.setReceiver_name(receiver_name);
//		orderVO.setReceiver_tel(receiver_tel);
//		orderVO.setReceiver_country(receiver_country);
//		orderVO.setReceiver_city(receiver_city);
//		orderVO.setReceiver_county(receiver_county);
//		orderVO.setReceiver_address(receiver_address);
//		orderVO.setReceiver_mail(receiver_mail);
//		orderVO.setSender_name(sender_name);
//		orderVO.setSender_tel(sender_tel);
//		orderVO.setSender_mail(sender_mail);
//		orderVO.setExpected_time(expected_time);
//		orderVO.setOrder_note(order_note);
//		orderVO.setOrder_updatetime(order_updatetime);
//		
//		dao.update(orderVO);
//		
//		return orderVO;
//	}
	
	public void deleteOrder(String order_id){
		dao.delete(order_id);
	}
	
	public OrderVO getOneOrder(String order_id){
		return dao.findByPrimaryKey(order_id);
	}
	
	public List<OrderVO> getAll(){
		return dao.getAll();
	}
}
