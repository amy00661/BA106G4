package android.emp.model;

import java.util.List;

public interface EmpDAO_interface {
	public EmpVO isEmp(String userId, String password);
	public EmpVO insert(EmpVO empVO);
	public int update(EmpVO empVO);
	public EmpVO findByPrimaryKey(String emp_id);
	public List<EmpVO> getAll();
}
