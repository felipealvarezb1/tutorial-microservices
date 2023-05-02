package felipe.springframework.carservice.Service;

import felipe.springframework.carservice.Model.Car;

import java.util.List;

public interface CarService {
    public List<Car> getAllCars();

    public Car getCarById(int id);

    public Car saveCar(Car car);

    public List<Car> getByUserId(int userId);
}
