package android.mem.model;

import java.util.*;

public interface MemDAO_interface {

	public MemVO isMember(String userId, String password);
//	boolean isUserIdExist(String userId);

	public void insert(MemVO memVO);

	public Integer update(MemVO memVO);

	public void delete(String member_id);

	public MemVO findByPrimaryKey(String member_id);

	public MemVO findByMail(String member_mail);

	public List<MemVO> getAll();

//	public Boolean pwdIsCorrect(String mem_id, String mem_pwd);

}
