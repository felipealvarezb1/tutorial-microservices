package felipe.springframework.bikeservice.Service;

import felipe.springframework.bikeservice.Model.Bike;
import felipe.springframework.bikeservice.Repository.BikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BikerServiceImpl implements BikeService{

    private final BikeRepository bikeRepository;


    @Override
    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    @Override
    public Bike getBikeById(int id) {
        return bikeRepository.findById(id).orElse(null);
    }

    @Override
    public Bike saveBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    @Override
    public List<Bike> getByUserId(int userId) {
        return bikeRepository.getByUserId(userId);
    }
}
