package my.com.tcsens.vehiclemanagement.service;


import lombok.val;
import my.com.tcsens.vehiclemanagement.model.Vehicle;
import my.com.tcsens.vehiclemanagement.models.tables.records.VehicleRecord;
import my.com.tcsens.vehiclemanagement.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle createVehicle(Vehicle vehicleProfile) {
        val vehicle = mapEntity(vehicleProfile);
        return mapDTO(vehicleRepository.createVehicle(vehicle));
    }

    public Vehicle getVehicleById(int vehicleId) {
        return vehicleRepository.getVehicleById(vehicleId)
                .stream()
                .findFirst()
                .map(this::mapDTO)
                .orElseThrow(() -> new IllegalArgumentException("No record found!"));
    }

    public List<Vehicle> getVehicles(String manufactureDate, String carPlateNum, int pageNo, int pageSize) {
        return vehicleRepository.getVehicleEnquiry(manufactureDate, carPlateNum, pageSize, pageNo)
                .stream()
                .filter(Objects::nonNull)
                .map(this::mapDTO)
                .collect(Collectors.toList());
    }

    private my.com.tcsens.vehiclemanagement.models.tables.pojos.Vehicle mapEntity(Vehicle vehicle) {
        return new my.com.tcsens.vehiclemanagement.models.tables.pojos.Vehicle()
                .setCarplateNum(vehicle.getCarPlatNumber())
                .setMake(vehicle.getCarMake())
                .setModel(vehicle.getCarModel())
                .setChassisNum(vehicle.getChassisNumber())
                .setAxlesNum(vehicle.getAxlesCount())
                .setTyreNum(vehicle.getTyreCount())
                .setRoadtaxExpiry(vehicle.getRoadTaxExpiryDate())
                .setManufactureYear(vehicle.getManufactureDate());
    }

    private Vehicle mapDTO(my.com.tcsens.vehiclemanagement.models.tables.pojos.Vehicle vehicleProfile) {
        return new Vehicle()
                .id(vehicleProfile.getId().longValue())
                .carPlatNumber(vehicleProfile.getCarplateNum())
                .carMake(vehicleProfile.getMake())
                .carModel(vehicleProfile.getModel())
                .chassisNumber(vehicleProfile.getChassisNum())
                .axlesCount(vehicleProfile.getAxlesNum())
                .tyreCount(vehicleProfile.getTyreNum())
                .roadTaxExpiryDate(vehicleProfile.getRoadtaxExpiry())
                .manufactureDate(vehicleProfile.getManufactureYear());
    }
}
