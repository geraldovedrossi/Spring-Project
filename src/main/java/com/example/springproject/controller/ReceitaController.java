package com.example.springproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springproject.model.ReceitaEntity;
import com.example.springproject.service.ReceitaService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/receitas")
@Slf4j
public class ReceitaController {
	
	@Autowired 
	private ReceitaService receitaService;
	
	@PostMapping
	public ResponseEntity<Object> criar(@RequestBody ReceitaEntity receitaEntity){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(receitaService.criar(receitaEntity));
		} catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
	}
	
	@PutMapping
	public ResponseEntity<Object> editar(@RequestBody ReceitaEntity receitaEntity){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(receitaService.editar(receitaEntity, receitaEntity.getId()));
		} catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
	}
	
	@GetMapping
	public ResponseEntity<List<ReceitaEntity>> listarTodos(){
		return ResponseEntity.ok(receitaService.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> ler(@PathVariable Long id){
		try {
			return ResponseEntity.ok(receitaService.ler(id));
		} catch (Exception ex){
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletar2(@PathVariable Long id){
		try {
			receitaService.deletar(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception ex){
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
	}
}
