package com.example.querydslsubquery.entity;

import com.example.querydslsubquery.dto.PaymentType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "orders")
@Entity
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private long orderId;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_type")
  private PaymentType paymentType;

  @Column(name = "order_code")
  private String orderCode;

  @Column(name = "price")
  private long price;

  @Embedded
  private AddressInfo addressInfo;

  @Builder.Default
  @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
  private List<OrderDetail> orderDetails = new ArrayList<>();

  public void addOrderDetail(OrderDetail orderDetail) {
    orderDetail.setOrder(this);
    orderDetails.add(orderDetail);
  }
}
