package my.com.tcsens.vehiclemanagement.controller;

import my.com.tcsens.vehiclemanagement.api.VehicleApi;
import my.com.tcsens.vehiclemanagement.model.Vehicle;
import my.com.tcsens.vehiclemanagement.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VehicleController implements VehicleApi {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Override
    public ResponseEntity<Vehicle> createVehicleProfile(Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.createVehicle(vehicle));
    }

    @Override
    public ResponseEntity<Vehicle> getVehicleById(Integer vehicleId) {
        return ResponseEntity.ok(vehicleService.getVehicleById(vehicleId));
    }

    @Override
    public ResponseEntity<List<Vehicle>> getVehicles(Integer pageNo, Integer pageSize, String manufactureYear, String carPlateNumber) {
        return ResponseEntity.ok(vehicleService.getVehicles(manufactureYear, carPlateNumber, pageNo, pageSize));
    }
}
