package com.jpabook.jpashop;

import com.jpabook.jpashop.domain.*;
import com.jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;


/**
 * 주문 두 개
 */
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){

        initService.dbUtil1();
        initService.dbUtil2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private  final EntityManager em;

        public void dbUtil1(){
            Member member = createMember("userA", new Address("서울", "1", "1111"));
            em.persist(member);

            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

        private static Book createBook(String name, int price, int quantity) {
            Book book1 =  new Book();
            book1.setName(name);
            book1.setPrice(price);
            book1.setStockQuantity(quantity);
            return book1;
        }


        public void dbUtil2(){
            Member member = createMember("userB", new Address("진주", "2", "2222"));
            em.persist(member);

            Book book1 = createBook("SPRING1 BOOK", 20000, 200);
            em.persist(book1);

            Book book2 = createBook("SPRING2 BOOK", 40000, 300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

        private static Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private static Member createMember(String name, Address address) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(address);
            return member;
        }

    }
}
