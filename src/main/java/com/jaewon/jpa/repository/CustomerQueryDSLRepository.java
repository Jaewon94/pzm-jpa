package com.jaewon.jpa.repository;

import com.jaewon.jpa.entity.Customer;
import com.jaewon.jpa.entity.QCustomer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerQueryDSLRepository {

    private final JPAQueryFactory queryFactory;

    // Customer -> QCustomer 이걸 가지고 사용
    public Customer getUsernameAndPassword(String username, String password) {
        QCustomer customer = QCustomer.customer;

        // SQL
        return queryFactory.selectFrom(customer)
                .where(customer.username.eq(username)
                .and(customer.password.eq(password)))
                .fetchOne();
    }

    public List<Customer> getRating(String rating) {
        QCustomer customer = QCustomer.customer;
        return queryFactory.selectFrom(customer)
                .where(customer.rating.eq(rating))
                .fetch();
    }
}
