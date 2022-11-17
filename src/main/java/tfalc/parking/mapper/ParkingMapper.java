package tfalc.parking.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import tfalc.parking.dto.ParkingDTO;
import tfalc.parking.model.Parking;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static Parking toParking(ParkingDTO createParked) {
        return MODEL_MAPPER.map(createParked, Parking.class);
    }

    public ParkingDTO toParkingDTO(Parking parking){
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }

    public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
        return parkingList.stream().map(this::toParkingDTO).collect(Collectors.toList());
    }
}
