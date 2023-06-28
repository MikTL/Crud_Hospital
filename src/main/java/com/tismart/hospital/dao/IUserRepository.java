package com.tismart.hospital.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.tismart.hospital.model.Usuario;

public interface IUserRepository extends JpaRepository<Usuario, Long>{

	UserDetails findByNombreUsuario(String username);

}
