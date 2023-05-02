package felipe.springframework.bikeservice.Repository;

import felipe.springframework.bikeservice.Model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer> {

    List<Bike> getByUserId(int userId);
}
