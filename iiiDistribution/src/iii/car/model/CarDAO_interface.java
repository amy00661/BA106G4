package iii.car.model;
import java.util.List;

public interface CarDAO_interface {
	public int insert(CarVO carVO);
	public void update(CarVO carVO);
	public void delete(String car_id);
	public CarVO findByPrimaryKey(String car_id);
	public List<CarVO> getAll();
}
