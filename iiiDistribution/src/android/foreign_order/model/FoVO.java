package android.foreign_order.model;
import java.sql.Date;
import java.sql.Timestamp;

public class FoVO implements java.io.Serializable{
	private String foreign_order_id;
	private String emp_id; 
	private String order_id;
	private String foreign_schedule_id;
	private Date fo_date;
	private Timestamp fo_updatetime;
	
	public String getForeign_order_id() {
		return foreign_order_id;
	}
	public void setForeign_order_id(String foreign_order_id) {
		this.foreign_order_id = foreign_order_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getForeign_schedule_id() {
		return foreign_schedule_id;
	}
	public void setForeign_schedule_id(String foreign_schedule_id) {
		this.foreign_schedule_id = foreign_schedule_id;
	}
	public Date getFo_date() {
		return fo_date;
	}
	public void setFo_date(Date fo_date) {
		this.fo_date = fo_date;
	}
	public Timestamp getFo_updatetime() {
		return fo_updatetime;
	}
	public void setFo_updatetime(Timestamp fo_updatetime) {
		this.fo_updatetime = fo_updatetime;
	}
}
