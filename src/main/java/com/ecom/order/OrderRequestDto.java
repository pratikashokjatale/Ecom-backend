package com.ecom.order;

import com.ecom.payment.PaymentMethod;

public class OrderRequestDto {
    private Long userId;
    private Long addressId;
    private PaymentMethod paymentMethod;

    public OrderRequestDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
