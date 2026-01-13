package com.ecom.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.auth.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/api/user/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Order> placeOrder(@AuthenticationPrincipal UserDetailsImpl currentUser,
            @RequestBody OrderRequestDto orderDto) {
        Long userId = orderDto.getUserId();
        if (currentUser != null) {
            userId = currentUser.getId();
            orderDto.setUserId(userId);
        }
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }
        Order o = orderService.placeOrder(orderDto);
        return ResponseEntity.ok(o);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Order>> listOrders(@AuthenticationPrincipal UserDetailsImpl currentUser,
            @RequestParam(value = "userId", required = false) Long userId) {
        if (currentUser != null) {
            userId = currentUser.getId();
        }
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderService.listOrdersForUser(userId));
    }

    @GetMapping("/{orderId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Order> getOrder(@AuthenticationPrincipal UserDetailsImpl currentUser,
            @PathVariable Long orderId) {
        return orderService.getOrder(orderId).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{orderId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrderStatus> getOrderStatus(@AuthenticationPrincipal UserDetailsImpl currentUser,
            @PathVariable Long orderId) {
        return orderService.getOrder(orderId).map(o -> ResponseEntity.ok(o.getStatus()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}