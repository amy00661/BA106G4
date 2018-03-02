package iii.fee_transition.model;

import java.util.*;

public interface TraDAO_interface {
	public void insert(TraVO traVO);
	public void update(TraVO traVO);
	public void delete(String transition_id);
	public TraVO findByPrimaryKey(String transition_id);
	public List<TraVO> getAll();
	
}
