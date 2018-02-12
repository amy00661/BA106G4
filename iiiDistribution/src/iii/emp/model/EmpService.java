package iii.emp.model;

import java.sql.Date;
import java.util.List;

public class EmpService {
	private EmpDAO_interface dao;
	
	public EmpService(){
		dao = new EmpDAO();
	}
	public EmpVO addEmp(String db_id,String emp_pwd,String emp_status,String emp_name
			,String emp_title,String emp_email,Date emp_hireDate,Date emp_leaveDate) {
		EmpVO empVO = new EmpVO();

		empVO.setDb_id(db_id);
		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_status(emp_status);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_title(emp_title);
		empVO.setEmp_email(emp_email);
		empVO.setEmp_hireDate(emp_hireDate);
		empVO.setEmp_leaveDate(emp_leaveDate);
		empVO = dao.insert(empVO);
		return empVO;
	}

	public EmpVO updateEmp(String emp_id,String db_id,String emp_pwd,String emp_status,String emp_name
			,String emp_title,String emp_email,Date emp_hireDate,Date emp_leaveDate) {

		EmpVO empVO = new EmpVO();
		empVO.setEmp_id(emp_id);
		empVO.setDb_id(db_id);
		empVO.setEmp_pwd(emp_pwd);
		empVO.setEmp_status(emp_status);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_title(emp_title);
		empVO.setEmp_email(emp_email);
		empVO.setEmp_hireDate(emp_hireDate);
		empVO.setEmp_leaveDate(emp_leaveDate);
		dao.update(empVO);

		return empVO;
	}

	public EmpVO getOneEmp(String emp_id) {
		return dao.findByPrimaryKey(emp_id);
	}

	public List<EmpVO> getAllEmps() {
		return dao.getAll();
	}
	
}
