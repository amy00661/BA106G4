package iii.emp.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class EmpVO implements Serializable{
		private String emp_id;
		private String db_id;
		private String emp_pwd;
		private String emp_status;
		private String emp_name;
		private String emp_title;
		private String emp_email;
		private Date emp_hireDate;
		private Date emp_leaveDate;
		private Timestamp emp_updateTime;
		
		public String getEmp_id() {
			return emp_id;
		}

		public void setEmp_id(String emp_id) {
			this.emp_id = emp_id;
		}

		public String getDb_id() {
			return db_id;
		}

		public void setDb_id(String db_id) {
			this.db_id = db_id;
		}

		public String getEmp_pwd() {
			return emp_pwd;
		}

		public void setEmp_pwd(String emp_pwd) {
			this.emp_pwd = emp_pwd;
		}

		public String getEmp_status() {
			return emp_status;
		}

		public void setEmp_status(String emp_status) {
			this.emp_status = emp_status;
		}

		public String getEmp_name() {
			return emp_name;
		}

		public void setEmp_name(String emp_name) {
			this.emp_name = emp_name;
		}

		public String getEmp_title() {
			return emp_title;
		}

		public void setEmp_title(String emp_title) {
			this.emp_title = emp_title;
		}

		public String getEmp_email() {
			return emp_email;
		}

		public void setEmp_email(String emp_email) {
			this.emp_email = emp_email;
		}

		public Date getEmp_hireDate() {
			return emp_hireDate;
		}

		public void setEmp_hireDate(Date emp_hireDate) {
			this.emp_hireDate = emp_hireDate;
		}

		public Date getEmp_leaveDate() {
			return emp_leaveDate;
		}

		public void setEmp_leaveDate(Date emp_leaveDate) {
			this.emp_leaveDate = emp_leaveDate;
		}

		public Timestamp getEmp_updateTime() {
			return emp_updateTime;
		}

		public void setEmp_updateTime(Timestamp emp_updateTime) {
			this.emp_updateTime = emp_updateTime;
		}

		@Override
		public String toString() {			
			return "員工編號:"+emp_id+"\t貨運中心:"+db_id+"\t狀態:"+emp_status+"\t員工姓名:"+emp_name+"\t職稱:"
		+emp_title+"\t信箱:"+emp_email+"\t到職日期"+emp_hireDate+"\t離職日期:"+emp_leaveDate+"\t更新時間:"+emp_updateTime;
		}
}
