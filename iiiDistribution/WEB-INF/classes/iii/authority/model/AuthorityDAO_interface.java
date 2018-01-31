package iii.authority.model;

import java.util.Set;


public interface AuthorityDAO_interface {
		public int insert(String emp_id,Set<String> menuList);
		public int delete(String empid);
		//查詢某員工的權限(一對多)(回傳 Set)
	      public Set<AuthorityVO> getAuthsByEmpid(String empid);
}
