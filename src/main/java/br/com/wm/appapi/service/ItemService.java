package br.com.wm.appapi.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.wm.appapi.exception.ValidacaoException;
import br.com.wm.appapi.model.Item;
import br.com.wm.appapi.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	public Page<Item> listar(String codigoDescricao, Pageable pageable) {
		Page<Item> itens = null;
		if(StringUtils.isEmpty(codigoDescricao)) {
			itens = itemRepository.findAll(pageable);
		}else {
			itens = itemRepository.findByCodigoContainingOrDescricaoContaining(codigoDescricao, codigoDescricao, pageable);
		}
		return itens;
	}
	
	public Item salvar(Item item) {
		return itemRepository.save(item);
	}
	
	public Item buscarItemPeloId(Long id) {
		Optional<Item> optional = itemRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return optional.get();
	}
	
	public Item atualizar(Long id, Item item) {		
		Item itemSalvo = buscarItemPeloId(id);
		BeanUtils.copyProperties(item, itemSalvo, "id");
		return itemRepository.save(itemSalvo);
	}
	
	public void remover(Long id) {
		Item item = buscarItemPeloId(id);
		itemRepository.delete(item);
	}
	
}
