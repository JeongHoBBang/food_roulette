package com.green.food_roulette.payment;

import com.green.food_roulette.payment.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Description;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "유저 소비내역")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;

    @PostMapping("/main/{iuser}")
    @Operation(summary = "당첨 메뉴 등록",description = "imenu=메뉴의 id값" +
            " imanagement=이달의 목표 id값")
    public int setUserPayment(@RequestBody PyamentInsDto dto){
       return service.insUserPayment(dto);
    };

    @GetMapping("/calendar/{iuser}")
    @Operation(summary = "해당 달의 소비 내역들",description = "iuser=유저id" +
            "year = yy" +
            "month= mm")
    public List<PaymentMonthListVo> getUserPaymentList(@PathVariable Long iuser,String year,int month){
        PaymentMonthListDto dto = new PaymentMonthListDto();
        dto.setIuser(iuser);
        dto.setYear(year);
        dto.setMonth(month);
        return service.getUserPaymentList(dto);
    }

    @GetMapping("/calendar/{iuser}/detail")
    @Operation(summary = "해당 일의 소비 내역들 ",description = "paymentAt ex)yy-mm-dd 날자정보" +
            "iuser= 유저 id")
    public List<PaymentDetailVo>getUserDetailPayment(@PathVariable Long iuser,String  paymentAt){
        PaymentDetailDto dto = new PaymentDetailDto();
        dto.setIuser(iuser);
        dto.setPaymentAt(paymentAt);
        return service.getUserDetailPayment(dto);

    }
    @PatchMapping("/calendar/{iuser}/detail")
    @Operation(summary = "후기 작성",description = "유저 id" +
            "  ipayment=리뷰 id값" +
            " currentmenupirce= 먹은 가격" +
            "review= 페페 스코어 1~3으로만 받기 가능" +
            " restaurant =먹은 장소" +
            " 리턴으로 1이 오면 정상 등록 -1이면 한도초과")
    public int reviewPayment(@RequestBody PaymentReviewDto dto){
        try {
            return service.reviewPayment(dto);
        } catch (Exception e) {
            return -1;
        }


    }
}
