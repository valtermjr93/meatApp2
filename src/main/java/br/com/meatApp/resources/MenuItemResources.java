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

import br.com.meatApp.domain.MenuItem;
import br.com.meatApp.services.MenuItemService;

@RestController
@RequestMapping(value="MenuItems")
public class MenuItemResources {
	
	@Autowired
	private MenuItemService MenuItemService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MenuItem>> findAll(){
		List<MenuItem> MenuItems = MenuItemService.findAll();
		return ResponseEntity.ok().body(MenuItems);
	}
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<MenuItem> findById(@PathVariable Integer id){
		MenuItem MenuItem = MenuItemService.findById(id);
		return ResponseEntity.ok().body(MenuItem);
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<MenuItem> insert(@Valid @RequestBody MenuItem MenuItem){
		MenuItem = MenuItemService.insert(MenuItem);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id/{id}").buildAndExpand(MenuItem.getId()).toUri();
		return ResponseEntity.created(uri).body(MenuItem);
	}
	@RequestMapping(value="id/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody MenuItem MenuItem, @PathVariable Integer id){
		MenuItem = MenuItemService.update(MenuItem, id);
		return ResponseEntity.noContent().build();
		
	}
	@RequestMapping(value="id/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<MenuItem> delete(@PathVariable Integer id){
		MenuItemService.delete(id);
		return ResponseEntity.noContent().build();
	}
}