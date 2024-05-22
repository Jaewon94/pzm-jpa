package com.jaewon.jpa.repository;

import com.jaewon.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // 1. JpaRepository에서 제공되는 메서드를 사용하는 방법
    // - 이미 만들어져 있어서 따로 작성할 필요 없음
    // 2. 쿼리메서드를 사용하는 방법 (규칙을 가지고 만들어지는 메서드)
    List<Customer> findByAge(int age);

    Optional<Customer> findByUsernameAndPassword(String username, String password);

    Optional<Customer> findByUsername(String username);

    List<Customer> findByAgeGreaterThanEqual(int age);

    // 3. JPQL(사용자 정의 쿼리) : Java Persistence Query Language
    // -1. Entity(Object)를 기준으로 쿼리 만들기

    @Query("select cus from Customer as cus where cus.rating = :rating")
    List<Customer> getRating(@Param("rating") String rating); // vip, gole, silver

    // -2. Table을 기준으로 쿼리만들기(nativeQuery)
    @Query(value = "select * from customer where rating = ?1", nativeQuery = true)
    List<Customer> getRatingTable(String rating);

    // 4. QueryDSL을 사용 : Hibernate Query Language의 쿼리를 타입에 안전하게 생성 및 관리해주는 Java 프레임워크이다.
    // 장점: 이는 SQL보다 가독성이 높고, 개발자가 쿼리를 더 직관적이고 유연하게 작성할 수 있다.
    // QueryDSL은 다른

}
