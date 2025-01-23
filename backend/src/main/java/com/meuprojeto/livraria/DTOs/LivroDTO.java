package com.meuprojeto.livraria.DTOs;

import com.meuprojeto.livraria.entities.Livro;

public class LivroDTO {

	private Long id;
	private String titulo;
	private String autor;
	private int anoPublicacao;
	private String categoria;
	private String imgUrl;
	private int quantidade;

	public LivroDTO() {

	}

	public LivroDTO(Long id, String titulo, String autor, int anoPublicacao, String categoria, String imgUrl, int quantidade) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.anoPublicacao = anoPublicacao;
		this.categoria = categoria;
		this.imgUrl = imgUrl;
		this.quantidade = quantidade;
	}

	public LivroDTO(Livro entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
		this.autor = entity.getAutor();
		this.anoPublicacao = entity.getAnoPublicacao();
		this.categoria = entity.getCategoria();
		this.imgUrl = entity.getImgUrl();
		this.quantidade = entity.getQuantidade();
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
