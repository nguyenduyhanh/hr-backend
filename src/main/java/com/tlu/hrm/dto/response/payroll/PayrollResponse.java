package com.tlu.hrm.dto.response.payroll;

import com.tlu.hrm.dto.request.PeriodDto;
import com.tlu.hrm.enums.PayrollStatus;
import com.tlu.hrm.model.Payroll;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayrollResponse {
    private UUID id;
    private String code;
    private String name;
    private PeriodDto period;
    private PayrollStatus status;
    private String description;

    public static PayrollResponse fromEntity(Payroll entity) {
        if (entity == null) {
            return null;
        }
        return PayrollResponse.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .period(entity.getPeriod() != null ? new PeriodDto(entity.getPeriod()) : null)
                .status(entity.getStatus())
                .description(entity.getDescription())
                .build();
    }
}
