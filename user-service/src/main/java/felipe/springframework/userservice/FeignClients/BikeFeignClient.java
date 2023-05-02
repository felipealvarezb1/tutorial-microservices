package felipe.springframework.userservice.FeignClients;

import felipe.springframework.userservice.Model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "bike-service")
public interface BikeFeignClient {

    @PostMapping("/bike")
    Bike saveBike(@RequestBody Bike bike);

    @GetMapping("bike/searchByUser/{userId}")
    List<Bike> getBikes(@PathVariable("userId") int userId);
}
