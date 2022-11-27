package tfalc.parking.service;

import org.springframework.stereotype.Service;
import tfalc.parking.exception.ParkingNotFoundException;
import tfalc.parking.model.Parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap();

    static {
        var id = getUUID();
        var id1 = getUUID();
        Parking parking = new Parking(id, "KOF-9828", "RJ", "VW GOL", "White");
        Parking parkingBus = new Parking(id1, "KMS-1231", "SP", "Bus", "Yellow");
        parkingMap.put(id, parking);
        parkingMap.put(id1, parkingBus);
    }

    public List<Parking> findAll() {
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);

        if(parking == null){
            throw new ParkingNotFoundException(id);
        }

        return parking;
    }

    public Parking createParking(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    public Parking update(String id, Parking parkingCreate) {
        Parking parkedCar = findById(id);
        parkedCar.setColor(parkingCreate.getColor());
        parkingMap.replace(id, parkedCar);

        return parkedCar;
    }

    public Parking exit(String id) {
        //Atualizar data de saida e valor cobrado
        Parking parkedCar = findById(id);
        parkedCar.setExitDate(LocalDateTime.now());
        var billedTime = parkedCar.getEntryDate().until(parkedCar.getExitDate(), ChronoUnit.MINUTES);
        parkedCar.setBill(1 + (0.5 * billedTime));
        return parkedCar;
    }
}
