package br.com.meatApp;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.meatApp.domain.MenuItem;
import br.com.meatApp.domain.OrderItem;
import br.com.meatApp.domain.OrderItemPK;
import br.com.meatApp.domain.Orders;
import br.com.meatApp.domain.Restaurant;
import br.com.meatApp.domain.User;
import br.com.meatApp.repositories.MenuItemRepository;
import br.com.meatApp.repositories.OrderItemRepository;
import br.com.meatApp.repositories.OrderRepository;
import br.com.meatApp.repositories.RestaurantRepository;
import br.com.meatApp.repositories.UserRepository;

@SpringBootApplication
public class MeatAppApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private MenuItemRepository menuItemRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(MeatAppApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		User user1 = new User(1, "Valter", "valter@hotmail.com", "123");
		User user2 = new User(2, "Saulo", "saulo@gmail.com", "456");
		userRepository.saveAll(Arrays.asList(user1, user2));
		
		Restaurant r1 = new Restaurant(1, "Pizzaria", "só queijo", "30 min", 30.0, "ExampleImage", "20h às 00h", "Pizzaria");
		restaurantRepository.saveAll(Arrays.asList(r1));
		
		MenuItem m1 = new MenuItem(1, "4 queijos", "só tem queijo", 3.0, "ImageExample", r1);
		MenuItem m2 = new MenuItem(2, "calabresa", "só tem calabresa", 4.0, "ImageExample", r1);
		MenuItem m3 = new MenuItem(3, "peperoni", "só tem peperoni", 2.0, "ImageExample", r1);
		menuItemRepository.saveAll(Arrays.asList(m1,m2,m3));
		
		Orders o1 = new Orders(1, LocalDateTime.now(), user1, r1, "Rua A", "120", null, "cartão");
		Orders o2 = new Orders(2, LocalDateTime.now(), user2, r1, "Rua B", "20", null, "dinheiro");
		orderRepository.saveAll(Arrays.asList(o1,o2));
		
		OrderItem oi1 = new OrderItem(new OrderItemPK(o1,1),1,4.5,m2);
		OrderItem oi2 = new OrderItem(new OrderItemPK(o1,2),2,10.0,m1);
		OrderItem oi3 = new OrderItem(new OrderItemPK(o2,1),2,7.5,m3);
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3));
	}
	
	

}
