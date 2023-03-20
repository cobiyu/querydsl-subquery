package com.example.querydslsubquery.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Embeddable
public class AddressInfo {
  @Column(name="address")
  private String address;

  @Column(name="post_code")
  private String postCode;
}
