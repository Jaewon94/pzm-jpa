package com.jaewon.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 일련번호

    @Column(nullable = false, unique = true)
    private String username; // 고객ID
    private String password;
    private String customerName; // 고객 이름
    private int age;
    private String rating;  // 등급
    private String occupation;  // 직업

    @Column(columnDefinition = "int default 0")
    private int reserves;   // 적립금

    // JPA에게 나는 연관관계의 주인이 아니다라고 말해줌(FK X)
    // 1(Customer) : N(Review)
    // CascadeType.ALL : 고객이 삭제가 되면 연결된 리뷰 정보도 함꼐 삭제되어야 한다.
    // fetch = FetchType.EAGER : 즉시로딩 -  메모리를 비효율적 사용
    // fetch = FetchType.LAZY : 지연로딩 - 메모리 절약, 참조순환 문제 발생(N+1 : 데이터베이스 참조가 계속 발생)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews; // Object는 컬럼으로 만들어지지 않는다.

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cart> carts;
}
