package com.example.querydslsubquery.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderDto {
  private Long orderId;
  private PaymentType paymentType;
  private String orderCode;
  private Long price;
  private String address;
  private String postCode;
}
