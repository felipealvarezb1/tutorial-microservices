package felipe.springframework.bikeservice.Controller;

import felipe.springframework.bikeservice.Model.Bike;
import felipe.springframework.bikeservice.Service.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
@RequiredArgsConstructor
public class BikeController {

    private final BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAllBikes(){
        List<Bike> bikeList = bikeService.getAllBikes();
        if (bikeList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new  ResponseEntity<>(bikeList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable("id") int id){
        Bike bike = bikeService.getBikeById(id);
        if (bike == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bike, HttpStatus.OK);
    }

    @GetMapping("/searchByUser/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId){
        List<Bike> bikeList = bikeService.getByUserId(userId);
        return new  ResponseEntity<>(bikeList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Bike> saveBike(@RequestBody Bike bike){
        Bike newBike = bikeService.saveBike(bike);
        return new ResponseEntity<>(newBike, HttpStatus.CREATED);
    }
}
