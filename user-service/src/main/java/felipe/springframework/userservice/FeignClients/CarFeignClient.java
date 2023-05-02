package felipe.springframework.userservice.FeignClients;

import felipe.springframework.userservice.Model.Bike;
import felipe.springframework.userservice.Model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service")
public interface CarFeignClient {

    @PostMapping("/car")
    Car saveCar(@RequestBody Car car);

    @GetMapping("car/searchByUser/{userId}")
    List<Car> getCars(@PathVariable("userId") int userId);
}
