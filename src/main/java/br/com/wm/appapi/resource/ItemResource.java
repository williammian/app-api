package br.com.wm.appapi.resource;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.wm.appapi.model.Item;
import br.com.wm.appapi.service.ItemService;

@RestController
@RequestMapping("/itens")
public class ItemResource {
	
	@Autowired
	private ItemService itemService;

	@GetMapping()
	public Page<Item> listar(@RequestParam(required = false) String codigo, 
			@PageableDefault(sort = "codigo", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
		return itemService.listar(codigo, pageable);
	}
	
	@PostMapping()
	@Transactional
	public ResponseEntity<Item> cadastrar(@RequestBody @Valid Item item, UriComponentsBuilder uriBuilder) {
		Item itemSalvo = itemService.salvar(item);
		URI uri = uriBuilder.path("/itens/{id}").buildAndExpand(itemSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(itemSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Item> buscar(@PathVariable Long id) {
		Item item = itemService.buscarItemPeloId(id);
		return ResponseEntity.ok(item);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Item> atualizar(@PathVariable Long id, @RequestBody @Valid Item item) {
		Item itemSalvo = itemService.atualizar(id, item);
		return ResponseEntity.ok(itemSalvo);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		itemService.remover(id);
		return ResponseEntity.ok().build();
	}
}
