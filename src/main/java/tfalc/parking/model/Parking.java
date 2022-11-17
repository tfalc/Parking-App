package tfalc.parking.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Parking {

    private String id;
    private String license;
    private String state;
    private String model;
    private String color;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Double bill;
    
}
