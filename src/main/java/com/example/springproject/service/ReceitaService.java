package com.example.springproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springproject.model.ReceitaEntity;
import com.example.springproject.repository.ReceitaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;
	
	public ReceitaEntity criar(ReceitaEntity receita) {
		if(receita.getId() == null) {
			return receitaRepository.save(receita);
		}
		throw new EntityNotFoundException("Corpo da Receita não pode conter um ID");
	}
	
	public Optional<ReceitaEntity> ler(Long id) {
		if(receitaRepository.existsById(id)) {
			return receitaRepository.findById(id);
		}
		throw new EntityNotFoundException("Receita não encontrada!");
	}
	
	public List<ReceitaEntity> listarTodos() {
        List<ReceitaEntity> listaEntities = receitaRepository.findAll();
        return listaEntities;
    }
	
	public ReceitaEntity editar(ReceitaEntity receita, Long id) {
		if(receitaRepository.existsById(id)) {
			return receitaRepository.save(receita);
		}
		throw new EntityNotFoundException("Receita não encontrada");
	}
    
    public void deletar(Long id) {
    	if(receitaRepository.existsById(id)) {
    		receitaRepository.deleteById(id);
    		return;
    	}
    	throw new EntityNotFoundException("Receita não encontrada!");
    }
	
}
