package com.example.springproject.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_despesas")
public class DespesaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Column(name = "valor", nullable = false)
	private BigDecimal valor;

	@Column(name = "data", nullable = false)
	private LocalDateTime data;
	
	@Column(name = "categoria", nullable = true)
	private String categoria;
	
	private enum Categorias {
		Alimentação,
		Saúde,
		Moradia,
		Transporte,
		Educação,
		Lazer,
		Imprevistos,
		Outras
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public boolean containsCategoria(String value) {
        for (Categorias categoria : Categorias.values()) {
            if (categoria.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
	
	/*
	public boolean containsCategoriaByIndex(int index) {
	    for (Categorias categoria : Categorias.values()) {
	        if (categoria.ordinal() == index) {
	            return true;
	        }
	    }
	    return false;
	}
	*/
	
	public List<String> AllCategorias() {
		return Arrays.stream(Categorias.values()).map(Enum::toString).collect(Collectors.toList());
	}
}