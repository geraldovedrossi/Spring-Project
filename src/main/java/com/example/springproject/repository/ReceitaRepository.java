package com.example.springproject.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springproject.model.entity.ReceitaEntity;

@Repository
public interface ReceitaRepository extends JpaRepository <ReceitaEntity, Long>{

	public List <ReceitaEntity> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
	
	public List <ReceitaEntity> findAllByDataBetween(@Param("date") LocalDateTime data1, LocalDateTime data2);
}
