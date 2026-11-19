package com.tlu.hrm.controller;

import com.tlu.hrm.dto.response.ApiResponse;
import com.tlu.hrm.model.Period;
import com.tlu.hrm.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.tlu.hrm.dto.request.PeriodDto;
import com.tlu.hrm.dto.search.PeriodSearchRequest;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.UUID;

import static com.tlu.hrm.enums.RoleType.Constants.*;

@RestController
@RequestMapping("/api/periods")
@CrossOrigin(origins = "*")
public class PeriodController {

    @Autowired
    private PeriodService periodService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('" + ROLE_ADMIN + "', '" + HR_MANAGER + "', '" + HR_COMPENSATION_BENEFIT + "')")
    public ResponseEntity<ApiResponse<PeriodDto>> createPeriod(@RequestBody PeriodDto dto) {
        Period result = periodService.createPeriod(dto);
        return ResponseEntity.ok(ApiResponse.success("Tạo kỳ tính lương mới thành công", new PeriodDto(result)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + ROLE_ADMIN + "', '" + HR_MANAGER + "', '" + HR_COMPENSATION_BENEFIT + "')")
    public ResponseEntity<ApiResponse<PeriodDto>> updatePeriod(@PathVariable UUID id, @RequestBody PeriodDto dto) {
        Period result = periodService.updatePeriod(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật kỳ tính lương thành công", new PeriodDto(result)));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('" + ROLE_ADMIN + "', '" + HR_MANAGER + "', '" + HR_COMPENSATION_BENEFIT + "', '" + HR_EMPLOYEE + "')")
    public ResponseEntity<ApiResponse<List<PeriodDto>>> getAllPeriods() {
        List<Period> result = periodService.getAllPeriods();
        List<PeriodDto> dtoList = result != null ? result.stream().map(PeriodDto::new).collect(java.util.stream.Collectors.toList()) : java.util.Collections.emptyList();
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách kỳ lương thành công", dtoList));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + ROLE_ADMIN + "', '" + HR_MANAGER + "', '" + HR_COMPENSATION_BENEFIT + "')")
    public ResponseEntity<ApiResponse<Void>> deletePeriod(@PathVariable UUID id) {
        periodService.deletePeriod(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa kỳ lương thành công", null));
    }

    @PostMapping("/paging")
    @PreAuthorize("hasAnyAuthority('" + ROLE_ADMIN + "', '" + HR_MANAGER + "', '" + HR_COMPENSATION_BENEFIT + "', '" + HR_EMPLOYEE + "')")
    public ResponseEntity<ApiResponse<Page<PeriodDto>>> getPeriods(@RequestBody PeriodSearchRequest request) {
        Page<Period> result = periodService.getPeriods(request);
        Page<PeriodDto> dtoPage = result != null ? result.map(PeriodDto::new) : Page.empty();
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách kỳ lương thành công", dtoPage));
    }
}
