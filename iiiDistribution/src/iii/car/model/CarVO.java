package iii.car.model;

import java.sql.Timestamp;
import java.util.Date;

public class CarVO {
	 private String car_id;
	 private String db_id;
	 private String emp_id;
	 private String car_plate;
	 private String car_driver;
	 private String car_status;
	 private String car_brand;
	 private String car_color;
	 private Double car_pdv;
	 private Double car_load;
	 private Timestamp car_updatetime;
	 private String car_note;
	 
     public String getCar_id(){
    	 return car_id;
     }
     public void setCar_id(String car_id){
    	 this.car_id = car_id;
     }  
     public String getDb_id(){
    	 return db_id;
     }
     public void setDb_id(String db_id){
    	 this.db_id = db_id;
     }
     public String getEmp_id(){
    	 return emp_id;
     }
     public void setEmp_id(String emp_id){
    	 this.emp_id = emp_id;
     }
     public String getCar_plate() {
    	 return car_plate;
     }
     public void setCar_plate(String car_plate) {
    	 this.car_plate = car_plate;
     }
     public String getCar_driver() {
    	 return car_driver;
     }
     public void setCar_driver(String car_driver) {
    	 this.car_driver = car_driver;
     }
	public String getCar_status() {
		 return car_status;
	 }
	 public void setCar_status(String car_status) {
		 this.car_status = car_status;
	 }
	 public String getCar_brand() {
		 return car_brand;
	 }
	 public void setCar_brand(String car_brand) {
		 this.car_brand = car_brand;
	 }
	 public String getCar_color() {
		 	return car_color;
	 }
	 public void setCar_color(String car_color) {
		 this.car_color = car_color;
	 }
	 public Double getCar_pdv() {
		 return car_pdv;
	 }
	 public void setCar_pdv(Double car_pdv) {
		 this.car_pdv = car_pdv;
	 }
	 public Double getCar_load() {
		 return car_load;
	 }
	 public void setCar_load(Double car_load) {
		 this.car_load = car_load;
	 }
	 public String getCar_note() {
		 return car_note;
	 }
	 public void setCar_note(String car_note) {
		 this.car_note = car_note;
	 }
	 public Timestamp getCar_updatetime() {
		return car_updatetime;
	}
	public void setCar_updatetime(Timestamp car_updatetime) {
		this.car_updatetime = car_updatetime;
	}
}