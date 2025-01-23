package com.meuprojeto.livraria.resource;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.meuprojeto.livraria.DTOs.LivroDTO;
import com.meuprojeto.livraria.services.LivroService;

@RestController
@RequestMapping("/livros")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class LivroResource {

	@Autowired
	private LivroService service;

	@GetMapping
	public ResponseEntity<List<LivroDTO>> findAll() {
		List<LivroDTO> listDTO = service.findAll();
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<LivroDTO> findById(@PathVariable Long id) {
		LivroDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping(value = "categoria/{categoria}")
	public ResponseEntity<List<LivroDTO>> findByCategory(@PathVariable String categoria) {
		List<LivroDTO> listDTO = service.finByCategoria(categoria);
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<LivroDTO> insert(@RequestBody LivroDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<LivroDTO> update(@PathVariable Long id, @RequestBody LivroDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}/quantidade")
	public ResponseEntity<LivroDTO> atualizarQuantidade(@PathVariable Long id, @RequestParam int quantidade) {
		LivroDTO atualizado = service.atualizarQuantidade(id, quantidade);
		return ResponseEntity.ok().body(atualizado);
	}
}
