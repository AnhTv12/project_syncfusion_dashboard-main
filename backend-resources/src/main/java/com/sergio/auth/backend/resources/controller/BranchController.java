package com.sergio.auth.backend.resources.controller;
import com.sergio.auth.backend.resources.dto.BranchDto;
import com.sergio.auth.backend.resources.model.Branch;
import com.sergio.auth.backend.resources.service.services.BranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/branch")
@Transactional
@CrossOrigin
public class BranchController {

    private final BranchService branchService;

    @GetMapping("/all")
    public ResponseEntity<List<BranchDto>> branches(){
        List<Branch> branches = branchService.listBranches();
        List<BranchDto> branchDtoList = branches.stream().map(branchService::dtoMapper).toList();
        return ResponseEntity.ok().body(branchDtoList);
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<BranchDto> branch(@PathVariable("branchId") int branchId){
        Branch branch = branchService.findBranch(branchId);
        BranchDto branchDto = branchService.dtoMapper(branch);
        return ResponseEntity.ok().body(branchDto);
    }


    @PostMapping("/add")
    public ResponseEntity<BranchDto> addBranch(@RequestBody BranchDto branch){
        Branch branch1 = branchService.EntityMapper(branch);
        branchService.addBranch(branch1);
        return ResponseEntity
                        .ok()
                        .body(branch);
    }

    @PostMapping("/edit")
    public ResponseEntity<BranchDto> editBranch(@RequestBody BranchDto branchDto){
        branchService.editBranch(branchService.EntityMapper(branchDto));
        return ResponseEntity
                .accepted()
                .body(branchDto);
    }

    @GetMapping("/delete/{branchId}")
    public ResponseEntity<String> deleteBranch(@PathVariable("branchId") int id){
        return ResponseEntity
                .ok()
                .body(branchService.deleteBranch(id));
    }

    @GetMapping("/delete/all")
    public ResponseEntity<String> deleteBranch(){
        return ResponseEntity
                .ok()
                .body(branchService.deleteAllBranches());
    }

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }
}
