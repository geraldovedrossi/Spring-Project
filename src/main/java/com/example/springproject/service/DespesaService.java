package com.example.springproject.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springproject.model.entity.DespesaEntity;
import com.example.springproject.repository.DespesaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository despesaRepository;
	
	public DespesaEntity criar(DespesaEntity despesa) {
		if(despesa.getId() == null) {
			if(despesa.getCategoria() == null) {
				despesa.setCategoria("Outras");
			}
			if(despesa.containsCategoria(despesa.getCategoria())) {
				return despesaRepository.save(despesa);
			}
			throw new EntityNotFoundException("Categoria inválida");
		}
		throw new EntityNotFoundException("Corpo da Receita não pode conter um ID");
	}
	
	public Optional<DespesaEntity> ler(Long id) {
		if(despesaRepository.existsById(id)) {
			return despesaRepository.findById(id);
		}
		throw new EntityNotFoundException("Receita não encontrada!");
	}
	
	public List<DespesaEntity> listarTodos() {
        List<DespesaEntity> listaEntities = despesaRepository.findAll();
        return listaEntities;
    }
	
	public List<DespesaEntity> listarPelaDescricao(String descricao) {
		List<DespesaEntity> listaEntities = despesaRepository.findAllByDescricaoContainingIgnoreCase(descricao);
		return listaEntities;
	}
	
	public List<String> listarTodasCategorias() {
		DespesaEntity despesaEntity = new DespesaEntity();
		return despesaEntity.AllCategorias();
	}
	
	public List<DespesaEntity> listarPorAnoMes(int ano, int mes) {
		LocalDateTime data1 = LocalDateTime.of(ano, Month.of(mes), 1, 0, 0);
		LocalDateTime data2 = LocalDateTime.of(ano, Month.of(mes), Month.of(mes).length(Year.of(ano).isLeap()), 0, 0);
		List<DespesaEntity> listaEntities = despesaRepository.findAllByDataBetween(data1, data2);
		return listaEntities;
	}
	
	public DespesaEntity editar(DespesaEntity despesa) {
		if(despesaRepository.existsById(despesa.getId())) {
			return despesaRepository.save(despesa);
		}
		throw new EntityNotFoundException("Receita não encontrada");
	}
    
    public void deletar(Long id) {
    	if(despesaRepository.existsById(id)) {
    		despesaRepository.deleteById(id);
    		return;
    	}
    	throw new EntityNotFoundException("Receita não encontrada!");
    }
}
