package com.example.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springproject.model.DespesaEntity;

@Repository
public interface DespesaRepository extends JpaRepository <DespesaEntity, Long>{

}
