package felipe.springframework.bikeservice.Service;

import felipe.springframework.bikeservice.Model.Bike;

import java.util.List;

public interface BikeService {

    public List<Bike> getAllBikes();

    public Bike getBikeById(int id);

    public Bike saveBike(Bike bike);

    public List<Bike> getByUserId(int userId);
}
