package com.example.springproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springproject.model.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

	public Optional<UsuarioEntity> findByUsuario(String usuario);
	
	// SELECT * FROM tb_usuarios WHERE usuario = ?;
}