package com.ecom.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.users.cart.CartItem;
import com.ecom.users.cart.CartItemRepository;
import com.ecom.users.cart.CartService;
import com.ecom.vendor.entity.Item;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private com.ecom.users.profile.AddressRepository addressRepository;

    @Transactional
    public Order placeOrder(OrderRequestDto orderDto) {
        Long userId = orderDto.getUserId();
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(cartItems.get(0).getUser());

        // Set Address
        if (orderDto.getAddressId() != null) {
            com.ecom.users.profile.Address address = addressRepository.findById(orderDto.getAddressId())
                    .orElseThrow(() -> new IllegalArgumentException("Address not found"));
            order.setShippingAddress(address);
        }

        // Set Payment
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setPaymentStatus("PENDING");

        double total = 0.0;
        for (CartItem ci : cartItems) {
            Item it = ci.getItem();

            // Check inventory
            if (it.getInventory() < ci.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for item: " + it.getItemName());
            }

            // Deduct inventory
            it.setInventory(it.getInventory() - ci.getQuantity());
            it.updateInventoryStatus();

            OrderItem oi = new OrderItem();
            oi.setItemId(it.getId());
            oi.setItemName(it.getItemName());
            oi.setPrice(it.getPrice());
            oi.setQuantity(ci.getQuantity());
            order.addItem(oi);
            total += (it.getPrice() != null ? it.getPrice() : 0.0) * ci.getQuantity();
        }
        order.setTotalAmount(total);
        order.setStatus(OrderStatus.PLACED);
        order.setCreatedAt(java.time.LocalDateTime.now());
        order.setUpdatedAt(java.time.LocalDateTime.now());
        order.setExpectedDeliveryDate(java.time.LocalDateTime.now().plusDays(7)); // Default 7 days delivery
        Order saved = orderRepository.save(order);

        // clear cart
        cartService.clearCart(userId);

        return saved;
    }

    public Optional<Order> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> listOrdersForUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
