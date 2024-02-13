package com.flota.flotabackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CarsService {

    private final CarsRepository carsRepository;

    @Autowired
    public CarsService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public List<Cars> fetchAllCars() {
        return carsRepository.findAll();
    }

    public Cars addCar(Cars car) {
        return carsRepository.save(car);
    }

    public Optional<Cars> updateCar(int id, Map<String, Object> updates) {
        return carsRepository.findById(id).map(car -> {
            updates.forEach((key, value) -> {
                // Use reflection or switch cases to handle different keys
                switch (key) {
                    case "make":
                        car.setMake((String) value);
                        break;
                    case "model":
                        car.setModel((String) value);
                        break;
                    case "mileage":
                        car.setMileage((Integer) value);
                        break;
                    // Add other fields as necessary
                }
            });
            return carsRepository.save(car);
        });
    }

    public boolean deleteCar(int id) {
        try {
            carsRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
