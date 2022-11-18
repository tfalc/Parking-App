package tfalc.parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfalc.parking.dto.ParkingCreateDTO;
import tfalc.parking.dto.ParkingDTO;
import tfalc.parking.mapper.ParkingMapper;
import tfalc.parking.model.Parking;
import tfalc.parking.service.ParkingService;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping("/parked")
    @ApiOperation(value = "Find all Parked")
    public ResponseEntity<List<ParkingDTO>> findAll(){
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/parked/{id}")
    @ApiOperation(value = "Find parked by ID")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
        Parking parkingById = parkingService.findById(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parkingById);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/createParked")
    @ApiOperation(value = "Create a new parked car")
    public ResponseEntity<ParkingDTO> createParked(@RequestBody ParkingCreateDTO createParked){

        var parkingCreate = ParkingMapper.toParkingCreate(createParked);

        Parking parking = parkingService.createParking(parkingCreate);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/parked/{id}")
    @ApiOperation(value = "Delete parked by ID")
    public ResponseEntity deleteById(@PathVariable String id){
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update parked car")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO updateParked){
        var parkingCreate = ParkingMapper.toParkingCreate(updateParked);

        var parking = parkingService.update(id, parkingCreate);
        var result = parkingMapper.toParkingDTO(parking);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update parked car")
    public ResponseEntity<ParkingDTO> exit(@PathVariable String id, @RequestBody ParkingCreateDTO updateParked){
        var parkingCreate = ParkingMapper.toParkingCreate(updateParked);

        var parking = parkingService.exit(id);
        var result = parkingMapper.toParkingDTO(parking);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
