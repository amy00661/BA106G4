package iii.bul.model;

import java.util.*;
public interface BulDAO_interface {

	public void insert(BulVO bulVO);
	public void update(BulVO bulVO);
	public void delete(String bulletin_id);
	public BulVO findByPrimaryKey(String bulletin_id);
	public List<BulVO> getALL();

}
