package com.flota.flotabackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

    private final CarsService carsService;

    @Autowired
    public CarsController(CarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping
    public List<Cars> getAllCars() {
        return carsService.fetchAllCars();
    }

    @PostMapping
    public Cars addCar(@RequestBody Cars car) {
        return carsService.addCar(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cars> updateCar(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        return carsService.updateCar(id, updates)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable int id) {
        boolean isDeleted = carsService.deleteCar(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
