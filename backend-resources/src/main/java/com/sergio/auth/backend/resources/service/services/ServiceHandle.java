package com.sergio.auth.backend.resources.service.services;

import com.sergio.auth.backend.resources.dto.ServiceDto;
import com.sergio.auth.backend.resources.model.Branch;
import com.sergio.auth.backend.resources.model.Order;
import com.sergio.auth.backend.resources.model.Service;


import java.util.List;

public interface ServiceHandle {

    List<Service> listServices();

    Service findService(int id);

    Service addService(Service service);

    Service editService(Service payload);

    Order receiveOrder(Order order);
    String deleteService(int id);

    String deleteAllServices();

    ServiceDto dtoMapper(Service service);

    Service EntityMapper(ServiceDto serviceDto);
}
