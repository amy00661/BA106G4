package iii.local_order.model;
import java.sql.Date;
import java.sql.Timestamp;

public class LoVO implements java.io.Serializable{
	private String local_order_ID;
	private String emp_ID; 
	private String order_ID; 
	private String local_schedule_ID;
	private Date local_orderDate;
	private Timestamp lo_updatetime;
	
	
	public String getLocal_order_ID() {
		return local_order_ID;
	}
	public void setLocal_order_ID(String local_order_ID) {
		this.local_order_ID = local_order_ID;
	}
	public String getEmp_ID() {
		return emp_ID;
	}
	public void setEmp_ID(String emp_ID) {
		this.emp_ID = emp_ID;
	}
	public String getOrder_ID() {
		return order_ID;
	}
	public void setOrder_ID(String order_ID) {
		this.order_ID = order_ID;
	}
	public String getLocal_schedule_ID() {
		return local_schedule_ID;
	}
	public void setLocal_schedule_ID(String local_schedule_ID) {
		this.local_schedule_ID = local_schedule_ID;
	}
	public Date getLocal_orderDate() {
		return local_orderDate;
	}
	public void setLocal_orderDate(Date local_orderDate) {
		this.local_orderDate = local_orderDate;
	}
	public Timestamp getLo_updatetime() {
		return lo_updatetime;
	}
	public void setLo_updatetime(Timestamp lo_updatetime) {
		this.lo_updatetime = lo_updatetime;
	}

}


