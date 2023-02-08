package com.sergio.auth.backend.resources.controller;
import com.sergio.auth.backend.resources.dto.ServiceDto;
import com.sergio.auth.backend.resources.model.Service;
import com.sergio.auth.backend.resources.service.services.ServiceHandle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/service")
@Transactional
@CrossOrigin
public class ServiceController {
    private final ServiceHandle serviceHandle;

    @GetMapping("/all")
    public ResponseEntity<List<ServiceDto>> listService(){
        List<Service> services = serviceHandle.listServices();
        List<ServiceDto> serviceDtos = services.stream().map(serviceHandle::dtoMapper).toList();
        return ResponseEntity.ok().body(serviceDtos);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceDto> searchForService(@PathVariable("serviceId") int id){
        Service service = serviceHandle.findService(id);
        return ResponseEntity.ok().body(serviceHandle.dtoMapper(service));
    }

    @PostMapping("/add")
    public ResponseEntity<ServiceDto> addService(@RequestBody ServiceDto serviceDto){
        serviceHandle.addService(serviceHandle.EntityMapper(serviceDto));
        return ResponseEntity
                .ok()
                .body(serviceDto);
    }

    @PostMapping("/edit")
    public ResponseEntity<ServiceDto> editService(@RequestBody ServiceDto serviceDto){
        serviceHandle.editService(serviceHandle.EntityMapper(serviceDto));
        return ResponseEntity
                .accepted()
                .body(serviceDto);
    }

    @GetMapping("/delete/{serviceId}")
    public ResponseEntity<String> deleteService(@PathVariable("serviceId") int id){
        return ResponseEntity
                .ok()
                .body(serviceHandle.deleteService(id));
    }

    @GetMapping("/delete/all")
    public ResponseEntity<String> deleteAllService(){
        return ResponseEntity
                .ok()
                .body(serviceHandle.deleteAllServices());
    }

    public ServiceController(ServiceHandle serviceHandle) {
        this.serviceHandle = serviceHandle;
    }
}
