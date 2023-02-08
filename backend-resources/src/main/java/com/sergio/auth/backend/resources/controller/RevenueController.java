package com.sergio.auth.backend.resources.controller;

import com.sergio.auth.backend.resources.dto.RevenueDto;
import com.sergio.auth.backend.resources.model.Revenue;
import com.sergio.auth.backend.resources.repository.RevenueRepo;
import com.sergio.auth.backend.resources.service.services.RevenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/revenue")
@Transactional
@CrossOrigin
public class RevenueController {
    private final RevenueService revenueService;

    @GetMapping("/all")
    public ResponseEntity<List<RevenueDto>> listRevenue(){
        List<Revenue> revenues = revenueService.listRevenues();
        revenues.sort(Comparator.comparing(Revenue::getTime));
        List<RevenueDto> orderDtos = revenues.stream().map(revenueService::dtoMapper).toList();
        return ResponseEntity.ok().body(orderDtos);
    }

    @GetMapping("/{revenueId}")
    public ResponseEntity<RevenueDto> searchForRevenue(@PathVariable("revenueId") int id){
        Revenue revenue = revenueService.findRevenue(id);
        return ResponseEntity.ok().body(revenueService.dtoMapper(revenue));
    }

    @PostMapping("/add")
    public ResponseEntity<RevenueDto> addRevue(@RequestBody RevenueDto revenueDto){
        revenueService.addRevenue(revenueService.EntityMapper(revenueDto));
        return ResponseEntity
                .ok()
                .body(revenueDto);
    }

    @PostMapping("/edit")
    public ResponseEntity<RevenueDto> editRevenue(@RequestBody RevenueDto revenueDto){
        revenueService.editRevenue(revenueService.EntityMapper(revenueDto));
        return ResponseEntity
                .accepted()
                .body(revenueDto);
    }

    @GetMapping("/delete/{revenueId}")
    public ResponseEntity<String> deleteRevenue(@PathVariable("revenueId") int id){
        return ResponseEntity
                .ok()
                .body(revenueService.deleteRevenue(id));
    }

    @GetMapping("/delete/all")
    public ResponseEntity<String> deleteAllRevenues(){
        return ResponseEntity
                .ok()
                .body(revenueService.deleteAllRevenues());
    }

    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }
}
