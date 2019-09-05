package br.com.meatApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.meatApp.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	
}
