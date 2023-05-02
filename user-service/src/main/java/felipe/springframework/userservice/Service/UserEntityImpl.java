package felipe.springframework.userservice.Service;

import felipe.springframework.userservice.Entity.UserEntity;
import felipe.springframework.userservice.FeignClients.BikeFeignClient;
import felipe.springframework.userservice.FeignClients.CarFeignClient;
import felipe.springframework.userservice.Model.Bike;
import felipe.springframework.userservice.Model.Car;
import felipe.springframework.userservice.Repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserEntityImpl implements UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final RestTemplate restTemplate;
    private final CarFeignClient carFeignClient;
    private final BikeFeignClient bikeFeignClient;

    @Override
    public List<UserEntity> getAllUser() {
        return userEntityRepository.findAll();
    }

    @Override
    public UserEntity getUserById(int id) {
        return userEntityRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        UserEntity user = userEntityRepository.save(userEntity);
        return user;
    }

    @Override
    public List<Car> getCars(int userId) {
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/searchByUser/" + userId, List.class);
        return cars;
    }

    @Override
    public List<Bike> getBikes(int userId) {
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/searchByUser/" + userId, List.class);
        return bikes;
    }

    @Override
    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        Car newCar = carFeignClient.saveCar(car);
        return newCar;
    }

    @Override
    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);
        Bike newBike = bikeFeignClient.saveBike(bike);
        return newBike;
    }

    @Override
    public Map<String, Object> getUserAndVehicles(int userId) {
        Map<String, Object> result = new HashMap<>();
        UserEntity user = userEntityRepository.findById(userId).orElse(null);
        if (user == null){
            result.put("Message", "user not exists");
            return result;
        }
        result.put("User", user);
        List<Car> cars = carFeignClient.getCars(userId);
        if (cars.isEmpty()){
            result.put("Cars", "the user doesn't have cars");
        } else {
            result.put("Cars", cars);
        }

        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if (bikes.isEmpty()){
            result.put("Bikes", "the user doesn't have bikes");
        } else {
            result.put("Bikes", bikes);
        }
        return result;
    }

}
