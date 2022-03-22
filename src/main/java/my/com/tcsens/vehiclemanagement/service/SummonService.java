package my.com.tcsens.vehiclemanagement.service;

import my.com.tcsens.vehiclemanagement.model.Summon;
import my.com.tcsens.vehiclemanagement.repository.SummonRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SummonService {
    private final SummonRepository summonRepository;

    public SummonService(SummonRepository summonRepository) {
        this.summonRepository = summonRepository;
    }

    public List<Summon> getSummonByCarPlateNumber(String carPlateNumber) {
        return summonRepository.getSummonsByVehicleNumber(carPlateNumber)
                .stream()
                .filter(Objects::nonNull)
                .map(this::mapDTO)
                .collect(Collectors.toList());
    }

    private Summon mapDTO(my.com.tcsens.vehiclemanagement.models.tables.pojos.Summon summonProfile) {
        return new Summon().id(summonProfile.getId().longValue())
                .serialNum(summonProfile.getSerialNum())
                .fineAmount(BigDecimal.valueOf(summonProfile.getFineAmt()))
                .location(summonProfile.getLocation_())
                .officerName(summonProfile.getOfficerName());
    }
}
