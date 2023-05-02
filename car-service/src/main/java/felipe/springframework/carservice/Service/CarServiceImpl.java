package felipe.springframework.carservice.Service;

import felipe.springframework.carservice.Model.Car;
import felipe.springframework.carservice.Repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(int id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> getByUserId(int userId) {
        return carRepository.findByUserId(userId);
    }
}
