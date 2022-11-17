package tfalc.parking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingDTO {

    private String id;
    private String license;
    private String state;
    private String model;
    private String color;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Double bill;
}
