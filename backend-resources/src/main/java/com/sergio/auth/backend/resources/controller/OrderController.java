package com.sergio.auth.backend.resources.controller;
import com.sergio.auth.backend.resources.dto.OrderDto;
import com.sergio.auth.backend.resources.model.Order;
import com.sergio.auth.backend.resources.service.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@Transactional
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> listOrder(){
        List<Order> orders = orderService.listOrders();
        List<OrderDto> orderDtos = orders.stream().map(orderService::dtoMapper).toList();
        return ResponseEntity.ok().body(orderDtos);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> findOrder(@PathVariable("orderId") int id){
        Order order = orderService.findOrder(id);
        return ResponseEntity.ok().body(orderService.dtoMapper(order));
    }

    @PostMapping("/add")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto) throws ParseException {
        Order order = orderService.EntityMapper(orderDto);
        Calendar calendar = Calendar.getInstance();
        order.setOrderedTime(calendar);
        orderService.addOrder(order);
        return ResponseEntity
                .ok()
                .body(orderDto);
    }

    @PostMapping("/edit")
    public ResponseEntity<OrderDto> editEmployee(@RequestBody OrderDto orderDto){
        orderService.editOrder(orderService.EntityMapper(orderDto));
        return ResponseEntity
                .accepted()
                .body(orderDto);
    }

    @GetMapping("/delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") int id){
        return ResponseEntity
                .ok()
                .body(orderService.deleteOrder(id));
    }

    @GetMapping("/delete/all")
    public ResponseEntity<String> deleteAllEmployee(){
        return ResponseEntity
                .ok()
                .body(orderService.deleteAllOrders());
    }

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
}
