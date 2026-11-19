package com.tlu.hrm.controller;

import com.tlu.hrm.dto.request.SalaryItemDto;
import com.tlu.hrm.dto.response.ApiResponse;
import com.tlu.hrm.model.SalaryItem;
import com.tlu.hrm.service.SalaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.tlu.hrm.enums.RoleType.Constants.*;

@RestController
@RequestMapping("/api/salary-items")
@CrossOrigin(origins = "*")
public class SalaryItemController {

    @Autowired
    private SalaryItemService salaryItemService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('" + ROLE_ADMIN + "', '" + HR_MANAGER + "', '" + HR_COMPENSATION_BENEFIT + "', '"
            + HR_EMPLOYEE + "')")
    public ResponseEntity<ApiResponse<List<SalaryItemDto>>> getAllSalaryItems() {
        List<SalaryItem> result = salaryItemService.getAllSalaryItems();
        List<SalaryItemDto> dtoList = result != null
                ? result.stream().map(SalaryItemDto::new).collect(Collectors.toList())
                : java.util.Collections.emptyList();
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách khoản lương thành công", dtoList));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('" + ROLE_ADMIN + "', '" + HR_MANAGER + "', '" + HR_COMPENSATION_BENEFIT + "')")
    public ResponseEntity<ApiResponse<SalaryItemDto>> saveSalaryItem(@RequestBody SalaryItemDto dto) {
        SalaryItem item = dto != null ? dto.toEntity() : new SalaryItem();
        SalaryItem result = salaryItemService.saveSalaryItem(item);
        return ResponseEntity.ok(ApiResponse.success("Lưu khoản lương thành công", new SalaryItemDto(result)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + ROLE_ADMIN + "', '" + HR_MANAGER + "', '" + HR_COMPENSATION_BENEFIT + "')")
    public ResponseEntity<ApiResponse<Void>> deleteSalaryItem(@PathVariable UUID id) {
        salaryItemService.deleteSalaryItem(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa khoản lương thành công", null));
    }
}
