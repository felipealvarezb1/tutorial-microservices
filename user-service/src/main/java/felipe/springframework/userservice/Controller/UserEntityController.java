package felipe.springframework.userservice.Controller;

import felipe.springframework.userservice.Entity.UserEntity;
import felipe.springframework.userservice.Model.Bike;
import felipe.springframework.userservice.Model.Car;
import felipe.springframework.userservice.Service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserEntityController {

    private final UserEntityService userEntityService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUser(){
        List<UserEntity> userEntityList = userEntityService.getAllUser();
        if (userEntityList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new  ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") int id){
        UserEntity user = userEntityService.getUserById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity userEntity){
        UserEntity user = userEntityService.saveUser(userEntity);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getUserCars(@PathVariable("userId") int userId){
        UserEntity user = userEntityService.getUserById(userId);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Car> cars = userEntityService.getCars(userId);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getUserBikes(@PathVariable("userId") int userId){
        UserEntity user = userEntityService.getUserById(userId);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Bike> bikes = userEntityService.getBikes(userId);
        return new ResponseEntity<>(bikes, HttpStatus.OK);
    }

    @PostMapping("/saveCar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car){
        if (userEntityService.getUserById(userId) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Car newCar = userEntityService.saveCar(userId, car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @PostMapping("/saveBike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike){
        if (userEntityService.getUserById(userId) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Bike newBike = userEntityService.saveBike(userId, bike);
        return new ResponseEntity<>(newBike, HttpStatus.CREATED);
    }

    @GetMapping("/getUserVehicles/{userId}")
    public ResponseEntity<Map<String, Object>> getAllUserVehicles(@PathVariable("userId") int userId){
        Map<String, Object> result = userEntityService.getUserAndVehicles(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
