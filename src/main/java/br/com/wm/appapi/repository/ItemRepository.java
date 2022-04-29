package br.com.wm.appapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wm.appapi.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
	Page<Item> findByCodigo(String codigo, Pageable pageable);
	
}
