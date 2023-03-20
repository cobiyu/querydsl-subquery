package com.example.querydslsubquery.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderQueryRepositoryTest {
  @Autowired
  private OrderQueryRepository orderQueryRepository;

  @Test
  public void getOrderList_Test(){
    orderQueryRepository.findOrderList();
  }
}