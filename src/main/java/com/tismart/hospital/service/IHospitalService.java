package com.tismart.hospital.service;

import java.util.List;

import com.tismart.hospital.model.Hospital;
import com.tismart.hospital.model.dto.EditarHospitalDTO;
import com.tismart.hospital.model.dto.HospitalDTO;
import com.tismart.hospital.model.dto.RegistrarHospitalDTO;

public interface IHospitalService {
	public List<HospitalDTO> listarHospitales();

	public void registrarHospital(RegistrarHospitalDTO hospDto);

	public Hospital obtenerHospitalPorId(Long id);

	public void actualizarHospital(EditarHospitalDTO hospDTO);

	public void eliminarHospital(Long id);

	public List<HospitalDTO> buscarPorNombre(String nombre);

	public List<HospitalDTO> buscarPorIdSede(String sede);
}
