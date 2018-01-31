package iii.authority.model;

import java.util.Set;

public class AuthorityService {
	private AuthorityDAO_interface dao;

	public AuthorityService() {
		dao = new AuthorityDAO();
	}
	
	public int addAuthority(String emp_id,Set<String> menuList) {
		int insertRow = 0;
		insertRow = dao.insert(emp_id, menuList);
		return insertRow;
	}

	public int deleteEmpAuth(String empid){
		int deleteRow = 0;
		deleteRow = dao.delete(empid);
		return deleteRow;
	}
	
	public Set<AuthorityVO> getAuthsByEmpid(String empid){
		return dao.getAuthsByEmpid(empid);
	}
}
