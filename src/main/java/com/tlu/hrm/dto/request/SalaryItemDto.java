package com.tlu.hrm.dto.request;

import com.tlu.hrm.enums.SalaryCalculationType;
import com.tlu.hrm.enums.SalaryItemType;
import com.tlu.hrm.model.SalaryItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryItemDto {
    private UUID id;
    private String code;
    private String name;
    private SalaryCalculationType calculationType;
    private SalaryItemType type;
    private String description;

    public SalaryItemDto(SalaryItem entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.calculationType = entity.getCalculationType();
            this.type = entity.getType();
            this.description = entity.getDescription();
        }
    }

    public SalaryItem toEntity() {
        SalaryItem entity = new SalaryItem();
        entity.setId(this.id);
        entity.setCode(this.code);
        entity.setName(this.name);
        entity.setCalculationType(this.calculationType);
        entity.setType(this.type);
        entity.setDescription(this.description);
        return entity;
    }
}
