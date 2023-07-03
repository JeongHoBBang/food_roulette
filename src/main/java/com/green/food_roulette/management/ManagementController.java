package com.green.food_roulette.management;

import com.green.food_roulette.management.model.ManagementEntity;
import com.green.food_roulette.management.model.ManagementMonthDto;
import com.green.food_roulette.management.model.ManagementMonthVo;
import com.green.food_roulette.management.model.ManagemetSetMonthDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@Tag(name = "월별 목표")
@RequiredArgsConstructor
public class ManagementController {
    private final ManagementService service;

    @PostMapping("/main/{iuser}/plan")
    @Operation(summary = "이달의 목표 설정하기",description = "iuser=유저 pk" +
            "   monthLimit= 원하는 한도"  )
    public ManagementMonthVo setUserMonthManagement(@PathVariable Long iuser,@RequestBody ManagemetSetMonthDto dto){
        ManagementEntity entity = new ManagementEntity();
        entity.setIuser(iuser);
        entity.setMonthLimit(dto.getMonthLimit());
        return service.setUserThisMonthManagement(entity);
    }

    @GetMapping("/main/{iuser}")
    @Operation(summary = "이달의 목표 불러오기",description = "iuser=url의 iuser값을 읽어 실행")
    public ManagementMonthVo getUserThisMonthManagement(@PathVariable Long iuser){
        ManagementEntity entity = new ManagementEntity();
        entity.setIuser(iuser);
       try {
           ManagementMonthVo result = service.getUserThisMonthManagement(entity);
           return result;
       }catch (Exception e){
           return null;
       }
    }

    @GetMapping("/calculate/{iuser}")
    @Operation(summary = "이번달을 제외한 목표 불러오기",description = "iuser=유저 id")
    public List<ManagementMonthVo> getUserManagementList(@PathVariable Long iuser){
        ManagementMonthDto dto = new ManagementMonthDto();
        dto.setIuser(iuser);
        return service.getUserManagementList(dto);
    }

    @PatchMapping("/main/{iuser}")
    @Operation(summary = "이달의 한도 수정",description = "")
    public ManagementMonthVo patchUserMonthManagement(@PathVariable Long iuser,@RequestBody ManagemetSetMonthDto dto)throws Exception{
        ManagementEntity entity = new ManagementEntity();
        entity.setMonthLimit(dto.getMonthLimit());
        entity.setIuser(iuser);
        return service.updUserMonthManagement(entity);
    }



}
