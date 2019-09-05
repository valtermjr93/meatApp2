package br.com.meatApp.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.meatApp.domain.Restaurant;
import br.com.meatApp.services.RestaurantService;

@RestController
@RequestMapping(value="restaurants")
public class RestaurantResources {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Restaurant>> findAll(){
		List<Restaurant> Restaurants = restaurantService.findAll();
		return ResponseEntity.ok().body(Restaurants);
	}
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Restaurant> findById(@PathVariable Integer id){
		Restaurant Restaurant = restaurantService.findById(id);
		return ResponseEntity.ok().body(Restaurant);
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Restaurant> insert(@Valid @RequestBody Restaurant Restaurant){
		Restaurant = restaurantService.insert(Restaurant);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id/{id}").buildAndExpand(Restaurant.getId()).toUri();
		return ResponseEntity.created(uri).body(Restaurant);
	}
	@RequestMapping(value="id/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Restaurant Restaurant, @PathVariable Integer id){
		Restaurant = restaurantService.update(Restaurant, id);
		return ResponseEntity.noContent().build();
		
	}
	@RequestMapping(value="id/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Restaurant> delete(@PathVariable Integer id){
		restaurantService.delete(id);
		return ResponseEntity.noContent().build();
	}
}