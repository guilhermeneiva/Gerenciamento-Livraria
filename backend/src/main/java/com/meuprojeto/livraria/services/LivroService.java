package com.meuprojeto.livraria.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.meuprojeto.livraria.DTOs.LivroDTO;
import com.meuprojeto.livraria.entities.Livro;
import com.meuprojeto.livraria.repositories.LivroRepository;
import com.meuprojeto.livraria.services.exception.DataBaseException;
import com.meuprojeto.livraria.services.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;

	public List<LivroDTO> findAll() {
		List<Livro> list = repository.findAll();
		return list.stream().map(x -> new LivroDTO(x)).collect(Collectors.toList());
	}

	public LivroDTO findById(Long id) {
		Optional<Livro> livro = repository.findById(id);
		Livro entity = livro.orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado para o ID: " + id));
		return new LivroDTO(entity);
	}

	public LivroDTO insert(LivroDTO dto) {
		Livro entity = new Livro();
		entity.setAnoPublicacao(dto.getAnoPublicacao());
		entity.setAutor(dto.getAutor());
		entity.setCategoria(dto.getCategoria());
		entity.setImgUrl(dto.getImgUrl());
		entity.setTitulo(dto.getTitulo());
		entity.setQuantidade(dto.getQuantidade());
		entity = repository.save(entity);
		return new LivroDTO(entity);
	}

	public LivroDTO update(Long id, LivroDTO dto) {
		try {
			Livro entity = repository.getReferenceById(id);
			entity.setAnoPublicacao(dto.getAnoPublicacao());
			entity.setAutor(dto.getAutor());
			entity.setCategoria(dto.getCategoria());
			entity.setImgUrl(dto.getImgUrl());
			entity.setTitulo(dto.getTitulo());
			entity.setQuantidade(dto.getQuantidade());
			entity = repository.save(entity);
			return new LivroDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id " + id + " not found.");
		}
	}

	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Id " + id + " not found.");
		}
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity Violation.");
		}
	}

	public List<LivroDTO> finByCategoria(String categoria) {
		List<Livro> list = repository.findByCategoria(categoria);
		return list.stream().map(x -> new LivroDTO(x)).collect(Collectors.toList());
	}

	public LivroDTO atualizarQuantidade(Long id, int novaQuantidade) {
		try {
			Livro entity = repository.getReferenceById(id);
			entity.setQuantidade(novaQuantidade);
			entity = repository.save(entity);
			return new LivroDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id " + id + " not found.");
		}
	}
}
