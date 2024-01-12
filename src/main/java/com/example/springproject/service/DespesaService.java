package com.example.springproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springproject.model.DespesaEntity;
import com.example.springproject.repository.DespesaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository despesaRepository;
	
	public DespesaEntity criar(DespesaEntity receita) {
		if(receita.getId() == null) {
			return despesaRepository.save(receita);
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
	
	public DespesaEntity editar(DespesaEntity despesa, Long idJson, Long idPath) {
		if(idJson == idPath) {
			if(despesaRepository.existsById(idJson)) {
				return despesaRepository.save(despesa);
			}
			throw new EntityNotFoundException("Receita não encontrada");
		}
		throw new EntityNotFoundException("Id do Path não corresponde ao Id do Json");
	}
    
    public void deletar(Long id) {
    	if(despesaRepository.existsById(id)) {
    		despesaRepository.deleteById(id);
    		return;
    	}
    	throw new EntityNotFoundException("Receita não encontrada!");
    }
}
