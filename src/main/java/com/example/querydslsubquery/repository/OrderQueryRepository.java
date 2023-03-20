package com.example.querydslsubquery.repository;

import static com.example.querydslsubquery.entity.QOrder.order;
import static com.example.querydslsubquery.entity.QOrderDetail.orderDetail;

import com.example.querydslsubquery.dto.OrderDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLTemplates;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class OrderQueryRepository {

  private final SQLTemplates sqlTemplates;

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional(readOnly = true)
  public List<OrderDto> findOrderList() {
    JPASQLQuery<?> jpaSqlQuery = new JPASQLQuery<>(entityManager, sqlTemplates);
    StringPath subQueryAlias = Expressions.stringPath("sub_query_order");
    StringPath address = Expressions.stringPath(order, "address"); // Embedded 오류로 인한 별도 path 생성
    StringPath postCode = Expressions.stringPath(order, "post_code");  // Embedded 오류로 인한 별도 path 생성

    List<OrderDto> orderList = jpaSqlQuery
        .select(
            Projections.constructor(
                OrderDto.class,
                order.orderId,
                order.paymentType,
                order.orderCode,
                order.price,
                address,
                postCode
            )
        )
        .from(order)
        .innerJoin(
            JPAExpressions.select(order.orderId).distinct()
                .from(order)
                .innerJoin(orderDetail).on(orderDetail.orderDetailId.eq(order.orderId))
                .where(orderDetail.goodsName.like("someGoodsName%")),
            subQueryAlias
        ).on(order.orderId.eq(
            Expressions.numberPath(Long.class, subQueryAlias, "order_id")
        ))
        .fetch();

    return orderList;
  }
}
