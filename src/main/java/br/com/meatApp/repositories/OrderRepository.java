package br.com.meatApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meatApp.domain.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{

}
