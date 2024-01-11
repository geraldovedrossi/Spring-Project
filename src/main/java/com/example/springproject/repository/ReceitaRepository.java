package com.example.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springproject.model.ReceitaEntity;

@Repository
public interface ReceitaRepository extends JpaRepository <ReceitaEntity, Long>{

}
