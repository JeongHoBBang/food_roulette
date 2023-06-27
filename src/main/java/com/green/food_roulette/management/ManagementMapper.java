package com.green.food_roulette.management;

import com.green.food_roulette.management.model.ManagementMonthDto;
import com.green.food_roulette.management.model.ManagementMonthVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagementMapper {
    ManagementMonthVo getUserThisMonthManagement(ManagementMonthDto dto);
    List<ManagementMonthVo>getUserManagementList(ManagementMonthDto dto);
}
