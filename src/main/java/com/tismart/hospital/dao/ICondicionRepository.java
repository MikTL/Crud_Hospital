package com.tismart.hospital.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tismart.hospital.model.Condicion;
public interface ICondicionRepository extends JpaRepository<Condicion, Long>{

}
