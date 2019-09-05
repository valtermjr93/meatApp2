package br.com.meatApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.meatApp.domain.MenuItem;
import br.com.meatApp.repositories.MenuItemRepository;
import br.com.meatApp.services.exception.DataIntegrityException;
import br.com.meatApp.services.exception.ObjectNotFoundException;


@Service
public class MenuItemService {
	
	@Autowired
	private MenuItemRepository MenuItemRepository;	
	
	public List<MenuItem> findAll(){
		return MenuItemRepository.findAll(); 
				
	}
	
	public MenuItem findById(Integer id) {
		Optional<MenuItem> MenuItem = MenuItemRepository.findById(id);
		return MenuItem.orElseThrow(() -> new ObjectNotFoundException("Item do Menu não encontardo! ID: " + id));
	}
	
	public MenuItem insert(MenuItem MenuItem) {
		MenuItem.setId(null);
		return MenuItemRepository.save(MenuItem);
	}
	public MenuItem update(MenuItem MenuItem, Integer id) {
		MenuItem.setId(id);
		return MenuItemRepository.save(MenuItem);
	}
	public void delete(Integer id) {
		this.findById(id);
		try {
			MenuItemRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Ocorreu um erro de integridade. Este Item já possui pedidos");
		}
		
	}
}