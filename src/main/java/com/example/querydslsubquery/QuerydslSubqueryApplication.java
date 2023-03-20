package com.example.querydslsubquery;

import com.example.querydslsubquery.dto.PaymentType;
import com.example.querydslsubquery.entity.AddressInfo;
import com.example.querydslsubquery.entity.Order;
import com.example.querydslsubquery.entity.OrderDetail;
import com.example.querydslsubquery.repository.OrderRepository;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class QuerydslSubqueryApplication {

  public static void main(String[] args) {
    SpringApplication.run(QuerydslSubqueryApplication.class, args);
  }

  @RequiredArgsConstructor
  @Component
  public class TestDataInitializer implements CommandLineRunner {
    private final OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
      IntStream.range(0, 20).forEach(value -> {
        Order order = Order.builder()
            .paymentType(PaymentType.PAYPAL)
            .orderCode("someOrderCode")
            .addressInfo(
                AddressInfo.builder()
                    .address("add"+ value)
                    .postCode("post"+ value)
                    .build()
            )
            .price(1000L)
            .build();

        OrderDetail orderDetail = OrderDetail.builder()
            .goodsName("someGoodsName"+ value)
            .order(order)
            .build();

        order.addOrderDetail(orderDetail);

        orderRepository.save(order);
      });

    }
  }

}
