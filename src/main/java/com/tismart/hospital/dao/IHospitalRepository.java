package com.tismart.hospital.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tismart.hospital.model.Hospital;
import com.tismart.hospital.model.Sede;

public interface IHospitalRepository extends JpaRepository<Hospital, Long>{

	List<Hospital> findAllByNombreContaining(String nombre);

	List<Hospital> findAllBySede(Sede sede);

}
