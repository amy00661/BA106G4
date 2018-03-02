package iii.car.model;

import java.sql.Timestamp;
import java.util.List;

public class CarService {

	private CarDAO_interface dao;
	
	public CarService() {
		dao = new CarDAO();
	}
	
	public CarVO addCar(String db_id, String emp_id, String car_plate, String car_driver, String car_status, 
			String car_brand, String car_color, Double car_pdv, Double car_load, String car_note){
		
		CarVO carVO = new CarVO();
		
		carVO.setDb_id(db_id);
		carVO.setEmp_id(emp_id);
		carVO.setCar_plate(car_plate);
		carVO.setCar_driver(car_driver);
		carVO.setCar_status(car_status);
		carVO.setCar_brand(car_brand);
		carVO.setCar_color(car_color);
		carVO.setCar_pdv(car_pdv);
		carVO.setCar_load(car_load);
		carVO.setCar_note(car_note);
		
		dao.insert(carVO);
		
		return carVO;
	}
	
	public CarVO updateCar(String car_id, String db_id, String emp_id, String car_plate, String car_driver, String car_status, 
			String car_brand, String car_color, Double car_pdv, Double car_load, Timestamp car_updatetime, String car_note){
		
		CarVO carVO = new CarVO();
		
		carVO.setCar_id(car_id);
		carVO.setDb_id(db_id);
		carVO.setEmp_id(emp_id);
		carVO.setCar_plate(car_plate);
		System.out.println(car_plate);
		carVO.setCar_driver(car_driver);
		carVO.setCar_status(car_status);
		carVO.setCar_color(car_color);
		carVO.setCar_brand(car_brand);
		carVO.setCar_pdv(car_pdv);
		carVO.setCar_load(car_load);
		carVO.setCar_updatetime(car_updatetime);
		carVO.setCar_note(car_note);
		
		dao.update(carVO);
		System.out.println("success");
		
		return carVO;
	}
	
	public void deleteCar(String car_id){
		dao.delete(car_id);
	}
	
	public CarVO getOneCar(String car_id){
		return dao.findByPrimaryKey(car_id);
	}
	
	public List<CarVO> getAll(){
		return dao.getAll();
	}
}