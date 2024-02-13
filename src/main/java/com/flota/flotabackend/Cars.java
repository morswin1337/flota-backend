package com.flota.flotabackend;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cars")
public class Cars {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuid", unique = true)
    private int uuid;
    @Column(name = "make", nullable = false)
    private String make;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "mileage")
    private int mileage;
}

//dodaj walidacje