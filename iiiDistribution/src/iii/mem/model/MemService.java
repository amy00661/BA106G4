package iii.mem.model;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;


public class MemService {

	private MemDAO_interface dao;
	
	public MemService(){
		dao = new MemDAO();
	}
	
	public MemVO addMem(String emp_id,String member_mail,String member_psw,String member_name,java.sql.Date member_birth,
			String member_gender,String member_identification,String member_cell,
			String member_phone,String member_addr,String member_status){
			
		
		MemVO memVO = new MemVO();
		
		memVO.setEmp_id(emp_id);
		memVO.setMember_mail(member_mail);
		memVO.setMember_psw(member_psw);
		memVO.setMember_name(member_name);
		memVO.setMember_birth(member_birth);
		memVO.setMember_gender(member_gender);
		memVO.setMember_identification(member_identification);
		memVO.setMember_cell(member_cell);
		memVO.setMember_phone(member_phone);
		memVO.setMember_addr(member_addr);
		memVO.setMember_status(member_status);
		
		
		dao.insert(memVO);
		return memVO;
	}
	
	public MemVO update(String member_id,String emp_id,String member_mail,String member_psw,String member_name,java.sql.Date member_birth,
			String member_gender,String member_identification,String member_cell,
			String member_phone,String member_addr,String member_status,Timestamp member_updatetime){
			
		
		MemVO memVO = new MemVO();
		
		memVO.setMember_id(member_id);
		memVO.setEmp_id(emp_id);
		memVO.setMember_mail(member_mail);
		memVO.setMember_psw(member_psw);
		memVO.setMember_name(member_name);
		memVO.setMember_birth(member_birth);
		memVO.setMember_gender(member_gender);
		memVO.setMember_identification(member_identification);
		memVO.setMember_cell(member_cell);
		memVO.setMember_phone(member_phone);
		memVO.setMember_addr(member_addr);
		memVO.setMember_status(member_status);
		memVO.setMember_updatetime(member_updatetime);

		dao.update(memVO);

		return memVO;
	}
	
	public void deleteMem(String member_id){
		dao.delete(member_id);
	}
	
	public MemVO getOneMem(String member_id){
		
		return dao.findByPrimaryKey(member_id);
	}
	
public MemVO getOneByMemMail(String member_mail){
		
		return dao.findByMail(member_mail);
	}
	
	public List<MemVO> getAll(){
		return dao.getAll();
	}
	
}
