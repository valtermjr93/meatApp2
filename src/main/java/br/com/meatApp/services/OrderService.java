package br.com.meatApp.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meatApp.domain.OrderItem;
import br.com.meatApp.domain.OrderItemPK;
import br.com.meatApp.domain.Orders;
import br.com.meatApp.repositories.OrderItemRepository;
import br.com.meatApp.repositories.OrderRepository;
import br.com.meatApp.services.exception.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private MenuItemService menuItemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private OrderItemRepository orderItemRespository;
	
	public List<Orders> findAll(){
		return orderRepository.findAll();
	}
	
	public Orders findById(Integer id) {
		Optional<Orders> order = orderRepository.findById(id);
		return order.orElseThrow(() -> new ObjectNotFoundException("Pedido nã encontrado! ID: " + id));
	}
	
	public Orders insert(Orders order) {
		order.setId(null);
		order.setData(LocalDateTime.now());
		order.setUser(userService.findById(order.getUser().getId()));
		order.setRestaurant(restaurantService.findById(order.getRestaurant().getId()));
		order = orderRepository.save(order);
		
		List<OrderItem> items = order.getOrderItems();
		int item = 1;
		for (OrderItem it : items) {
			it.setOrders(order);
			it.setOrderItemId(item);
			it.setMenuItem(menuItemService.findById(it.getMenuItem().getId()));
			
			orderItemRespository .save(it);
			item++;
		}
		return order;
	}
}
