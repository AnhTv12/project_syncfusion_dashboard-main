package com.sergio.auth.backend.resources.service.impl;
import com.sergio.auth.backend.resources.dto.ServiceDto;
import com.sergio.auth.backend.resources.model.Branch;
import com.sergio.auth.backend.resources.model.Customers;
import com.sergio.auth.backend.resources.model.Order;
import com.sergio.auth.backend.resources.model.Service;
import com.sergio.auth.backend.resources.repository.SalesRepo;
import com.sergio.auth.backend.resources.repository.ServiceRepo;
import com.sergio.auth.backend.resources.service.services.ServiceHandle;

import javax.transaction.Transactional;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceHandleImp implements ServiceHandle {
    private final ServiceRepo serviceRepo;
    private final SalesRepo salesRepo;

    public ServiceHandleImp(ServiceRepo serviceRepo,
                            SalesRepo salesRepo) {
        this.serviceRepo = serviceRepo;
        this.salesRepo = salesRepo;
    }

    @Override
    public List<Service> listServices() {
        return serviceRepo.findAll();
    }

    @Override
    public Service findService(int id) {
        return serviceRepo.findServiceByServiceId(id);
    }

    @Override
    public Service addService(Service service) {
        return serviceRepo.save(service);
    }

    @Override
    public Service editService(Service payload) {
        return serviceRepo.save(payload);
    }

    @Override
    @Transactional
    public Order receiveOrder(Order order) {
        Service service = findService(order.getServices().getServiceId());
        service.getOrders().add(order);
        addService(service);
        order.setPrice(service.getPrice());
        return order;
    }

    @Override
    public String deleteService(int id) {
        return null;
    }

    @Override
    public String deleteAllServices() {
        return null;
    }

    @Override
    public ServiceDto dtoMapper(Service service) {
        List<Integer> customerIds = service.getCustomers().stream().map(Customers::getCustomerId).toList();
        List<Integer> orderIds = service.getOrders().stream().map(Order::getOrderId).toList();
        return new ServiceDto(service.getServiceId(), service.getName(),service.getDescription(), service.getShift(service.getServiceShift()), customerIds, orderIds,service.getPrice());
    }

    @Override
    public Service EntityMapper(ServiceDto serviceDto) {
        return serviceRepo.findServiceByServiceId(serviceDto.getServiceId());
    }
}
