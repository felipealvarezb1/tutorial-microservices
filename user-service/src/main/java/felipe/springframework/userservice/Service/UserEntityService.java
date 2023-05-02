package felipe.springframework.userservice.Service;


import felipe.springframework.userservice.Entity.UserEntity;
import felipe.springframework.userservice.Model.Bike;
import felipe.springframework.userservice.Model.Car;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface UserEntityService {

    public List<UserEntity> getAllUser();

    public UserEntity getUserById(int id);

    public UserEntity saveUser(UserEntity userEntity);

    public List<Car> getCars(int userId);

    public List<Bike> getBikes(int userId);

    public Car saveCar(int userId, Car car);

    public Bike saveBike(int userId, Bike bike);

    public Map<String, Object> getUserAndVehicles(int userId);

}
