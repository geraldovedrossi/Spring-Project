package com.example.springproject.controller;

import java.util.List;

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

import com.example.springproject.model.entity.DespesaEntity;
import com.example.springproject.service.DespesaService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/despesas")
@Slf4j
public class DespesaController {

	@Autowired
	private DespesaService despesaService;
	
	@GetMapping("/categorias")
	public  ResponseEntity<Object> listarCategorias(){
		return ResponseEntity.status(HttpStatus.OK).body(despesaService.listarTodasCategorias());
	}
	
	@PostMapping
	public ResponseEntity<Object> criar(@RequestBody DespesaEntity despesaEntity){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(despesaService.criar(despesaEntity));
		} catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> editar(@RequestBody DespesaEntity despesaEntity, @PathVariable Long id){
		try {
			despesaEntity.setId(id);
			return ResponseEntity.status(HttpStatus.OK).body(despesaService.editar(despesaEntity));
		} catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
	}
	
	@GetMapping
	public ResponseEntity<List<DespesaEntity>> listarTodos(){
		return ResponseEntity.ok(despesaService.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> ler(@PathVariable Long id){
		try {
			return ResponseEntity.ok(despesaService.ler(id));
		} catch (Exception ex){
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
	}
	
	@GetMapping("/descricao={descricao}")
	public ResponseEntity<List<DespesaEntity>> listarPelaDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(despesaService.listarPelaDescricao(descricao));
	}
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<List<DespesaEntity>> listarPelaDescricao(@PathVariable int ano, @PathVariable int mes){
		return ResponseEntity.ok(despesaService.listarPorAnoMes(ano, mes));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletar(@PathVariable Long id){
		try {
			despesaService.deletar(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception ex){
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
	}
}
