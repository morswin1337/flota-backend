package com.flota.flotabackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarsController {

    @Autowired
    private CarsRepository carsRepository;

    @GetMapping("/cars")
    //sprawdz ta linijke
    public List<Cars> fetchCars(){
        return carsRepository.findAll();
    }

    @PostMapping("/cars")
    public Cars addCar(@RequestBody Cars car) {
        return carsRepository.save(car);
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Cars> updateCar(@PathVariable("id") int id, @RequestBody Map<String, Object> updates) {
        try {
            Optional<Cars> carData = carsRepository.findById(id);

            if (carData.isPresent()) {
                Cars existingCar = carData.get();

                // Sprawdzamy, czy dane pole istnieje w aktualizacjach i jeśli tak, aktualizujemy wartość
                if (updates.containsKey("make")) {
                    existingCar.setMake((String) updates.get("make"));
                }

                if (updates.containsKey("model")) {
                    existingCar.setModel((String) updates.get("model")); // Poprawna metoda dla pola 'model'
                }

                if (updates.containsKey("mileage")) {
                    existingCar.setMileage((Integer) updates.get("mileage")); // Poprawna metoda dla pola 'mileage'
                }
                return new ResponseEntity<>(carsRepository.save(existingCar), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable("id") int id){
        try{
            carsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
