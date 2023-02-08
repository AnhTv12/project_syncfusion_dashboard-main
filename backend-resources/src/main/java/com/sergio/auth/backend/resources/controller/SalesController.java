package com.sergio.auth.backend.resources.controller;
import com.sergio.auth.backend.resources.dto.SaleDto;
import com.sergio.auth.backend.resources.dto.saleDataDto.YearSaleDto;
import com.sergio.auth.backend.resources.model.Branch;
import com.sergio.auth.backend.resources.model.Sales;
import com.sergio.auth.backend.resources.model.Service;
import com.sergio.auth.backend.resources.repository.SalesRepo;
import com.sergio.auth.backend.resources.service.services.BranchService;
import com.sergio.auth.backend.resources.service.services.SalesService;
import com.sergio.auth.backend.resources.service.services.ServiceHandle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@Transactional
@CrossOrigin
public class SalesController {
    private final SalesService salesService;
    private final BranchService branchService;
    private final ServiceHandle serviceHandle;

    @GetMapping("/all")
    public ResponseEntity<?> listSale(@RequestParam("start")int start, @RequestParam("end")int end){
        return ResponseEntity.ok().body(salesService.findSalesPerYear(start,end));
    }

    @GetMapping("/branches")
    public ResponseEntity<?> listSalePerBranchEachYear(@RequestParam("start")int start, @RequestParam("end")int end){
        List<String> branchesName = branchService.listBranches().stream().map(Branch::getBranchName).toList();
        List<List<YearSaleDto>> response = new ArrayList<>();
        branchesName.forEach(name->{
            response.add(salesService.findBranchSalesPerYear(start,end,name));
        });
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/service")
    public ResponseEntity<?> listSalePerServiceEachYear(@RequestParam("start")int start, @RequestParam("end")int end){
        List<String> serviceName = serviceHandle.listServices().stream().map(Service::getName).toList();
        List<List<YearSaleDto>> response = new ArrayList<>();
        serviceName.forEach(name->{
            response.add(salesService.findServiceSalesPerYear(start,end,name));
        });
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<SaleDto> searchForSale(@PathVariable("saleId") int id){
        Sales sales = salesService.findSaleById(id);
        return ResponseEntity.ok().body(salesService.dtoMapper(sales));
    }

    @PostMapping("/add")
    public ResponseEntity<SaleDto> addSale(@RequestBody SaleDto saleDto){
        salesService.addSale(salesService.EntityMapper(saleDto));
        return ResponseEntity
                .ok()
                .body(saleDto);
    }

    @PostMapping("/edit")
    public ResponseEntity<SaleDto> editSale(@RequestBody SaleDto saleDto){
        salesService.editSale(salesService.EntityMapper(saleDto));
        return ResponseEntity
                .accepted()
                .body(saleDto);
    }

    @GetMapping("/delete/{saleId}")
    public ResponseEntity<String> deleteSale(@PathVariable("saleId") int id){
        return ResponseEntity
                .ok()
                .body(salesService.deleteSale(id));
    }

    @GetMapping("/delete/all")
    public ResponseEntity<String> deleteAllSales(){
        return ResponseEntity
                .ok()
                .body(salesService.deleteAllSales());
    }


    public SalesController(SalesService salesService, BranchService branchService, ServiceHandle serviceHandle) {
        this.salesService = salesService;
        this.branchService = branchService;
        this.serviceHandle = serviceHandle;
    }
}
