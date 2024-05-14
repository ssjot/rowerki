package pl.rowerki.domain.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rowerki.domain.entity.Vehicle;
import pl.rowerki.domain.repository.VehicleRepository;
import pl.rowerki.domain.service.VehicleService;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
