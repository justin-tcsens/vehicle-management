package my.com.tcsens.vehiclemanagement.repository;

import lombok.val;
import lombok.var;
import my.com.tcsens.vehiclemanagement.models.Tables;
import my.com.tcsens.vehiclemanagement.models.tables.pojos.Vehicle;
import org.jooq.DSLContext;

import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static my.com.tcsens.vehiclemanagement.models.Tables.VEHICLE;


@Repository
public class VehicleRepository {
    public final DSLContext dsl;

    public VehicleRepository (DSLContext dsl) {
        this.dsl = dsl;
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return dsl.insertInto(VEHICLE)
                .set(VEHICLE.CARPLATE_NUM, vehicle.getCarplateNum())
                .set(VEHICLE.MAKE, vehicle.getMake())
                .set(VEHICLE.MODEL, vehicle.getModel())
                .set(VEHICLE.CHASSIS_NUM, vehicle.getChassisNum())
                .set(VEHICLE.AXLES_NUM, vehicle.getAxlesNum())
                .set(VEHICLE.TYRE_NUM, vehicle.getTyreNum())
                .set(VEHICLE.ROADTAX_EXPIRY, vehicle.getRoadtaxExpiry())
                .set(VEHICLE.MANUFACTURE_YEAR, vehicle.getManufactureYear())
                .returning().fetchOne().into(Vehicle.class);
    }

    public List<Vehicle> getVehicleById(int vehicleId) {
        return dsl.select()
                .from(VEHICLE)
                .where(VEHICLE.ID.eq(vehicleId))
                .fetchInto(Vehicle.class);
    }

    public List<Vehicle> getVehicleEnquiry(String manufactureYear, String carPlateNum, int pageSize, int pageNum) {
        var condition = DSL.noCondition();

        if(StringUtils.hasLength(manufactureYear)) {
            condition = condition.and(VEHICLE.MANUFACTURE_YEAR.eq(manufactureYear));
        }

        if(StringUtils.hasLength(carPlateNum)) {
            condition = condition.and(VEHICLE.CARPLATE_NUM.eq(carPlateNum));
        }

        return dsl.select()
                .from(VEHICLE)
                .where(condition)
                .limit(pageSize)
                .offset(pageNum * pageSize)
                .fetchInto(Vehicle.class);
    }
}
