package br.com.meatApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.meatApp.domain.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer>{
	
}
