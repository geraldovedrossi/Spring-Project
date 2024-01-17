package com.example.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springproject.model.dto.ResumoDTO;
import com.example.springproject.service.ResumoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/resumo")
@Slf4j
public class ResumoController {
	
	@Autowired
	private ResumoService resumoService;
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<ResumoDTO> resumoMensal(@PathVariable int ano, @PathVariable int mes){
		return ResponseEntity.ok(resumoService.resumoMensal(ano, mes));
	}
}
