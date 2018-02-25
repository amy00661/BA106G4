package iii.mem.model;
import java.util.*;
public interface MemDAO_interface {
		public void insert(MemVO memVO);
		public void update(MemVO memVO);
		public void delete(String member_id);
		public MemVO findByPrimaryKey(String member_id);
		public MemVO findByMail(String member_mail);
		
		public List<MemVO> getAll();
	
}
