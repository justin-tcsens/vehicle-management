package my.com.tcsens.vehiclemanagement.repository;

import my.com.tcsens.vehiclemanagement.models.Tables;
import my.com.tcsens.vehiclemanagement.models.tables.pojos.Summon;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static my.com.tcsens.vehiclemanagement.models.Tables.VEHICLE;
import static my.com.tcsens.vehiclemanagement.models.tables.Summon.SUMMON;

@Repository
public class SummonRepository {
    private final DSLContext dsl;

    public SummonRepository (DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<Summon> getSummonsByVehicleNumber(String carPlateNumber) {
        return dsl.select(SUMMON.asterisk()).from(SUMMON)
                .innerJoin(VEHICLE).on(VEHICLE.ID.eq(SUMMON.VEHICLE_ID))
                .where(VEHICLE.CARPLATE_NUM.eq(carPlateNumber))
                .fetchInto(Summon.class);
    }
}
