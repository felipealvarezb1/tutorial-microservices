package felipe.springframework.userservice.FeignClients;

import felipe.springframework.userservice.Model.Bike;
import felipe.springframework.userservice.Model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service", url = "http://localhost:8002/car")
public interface CarFeignClient {

    @PostMapping
    Car saveCar(@RequestBody Car car);

    @GetMapping("/searchByUser/{userId}")
    List<Car> getCars(@PathVariable("userId") int userId);
}
