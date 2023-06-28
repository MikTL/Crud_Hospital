package com.tismart.hospital.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tismart.hospital.dao.ICondicionRepository;
import com.tismart.hospital.dao.IDistritoRepository;
import com.tismart.hospital.dao.IGerenteRepository;
import com.tismart.hospital.dao.IHospitalRepository;
import com.tismart.hospital.dao.ISedeRepository;
import com.tismart.hospital.model.Condicion;
import com.tismart.hospital.model.Distrito;
import com.tismart.hospital.model.Gerente;
import com.tismart.hospital.model.Hospital;
import com.tismart.hospital.model.Sede;
import com.tismart.hospital.model.dto.EditarHospitalDTO;
import com.tismart.hospital.model.dto.HospitalDTO;
import com.tismart.hospital.model.dto.RegistrarHospitalDTO;
@Service
public class HospitalServiceImpl implements IHospitalService {
	@Autowired
	private IHospitalRepository hospitalrepo;
	@Autowired
	private ICondicionRepository condicionRepo;
	@Autowired
	private IDistritoRepository distritoRepo;
	@Autowired
	private IGerenteRepository gerenteRepo;
	@Autowired
	private ISedeRepository sedeRepo;

	
	@Override
	public List<HospitalDTO> listarHospitales() {
		List<Hospital> hospitales= hospitalrepo.findAll();
		for (Hospital hospital : hospitales) {
			System.out.println(hospital.getNombre());
		}
		List<HospitalDTO> hospitalesDto = new ArrayList<>();
		for (Hospital hospital : hospitales) {
			Gerente gerente=hospital.getGerente();
			String nombreGerente=gerente.getDescGerente();
			Distrito distrito= hospital.getDistrito();
			String nombreDistrito= distrito.getDescDistrito();
			HospitalDTO hosDTO= new HospitalDTO(hospital.getId(),hospital.getNombre(), nombreDistrito, nombreGerente);
			hospitalesDto.add(hosDTO);
		}
		
		return hospitalesDto;
	}

	@Override
	@Transactional
	public void registrarHospital(RegistrarHospitalDTO hospDto) {
		Hospital hospital= new Hospital();
		hospital.setNombre(hospDto.getNombre());
		hospital.setAntiguedad(hospDto.getAntiguedad());
		hospital.setArea(hospDto.getArea());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime fechaRegistro = LocalDate.parse(hospDto.getFechaRegistro(), formatter).atStartOfDay();
		hospital.setFechaRegistro(fechaRegistro);
		
		Optional<Condicion> condi= condicionRepo.findById(Long.valueOf(hospDto.getIdCondicion()));
		hospital.setCondicion(condi.isPresent()?condi.get():null);
	
		Optional<Distrito> distrito= distritoRepo.findById(Long.valueOf(hospDto.getIdDistrito()));
		hospital.setDistrito(distrito.isPresent() ? distrito.get() : null);
		
		Optional<Gerente> gerente= gerenteRepo.findById(Long.valueOf(hospDto.getIdGerente()));
		hospital.setGerente(gerente.isPresent() ? gerente.get() : null);
		
		Optional<Sede> sede= sedeRepo.findById(Long.valueOf(hospDto.getIdSede()));
		hospital.setSede(sede.isPresent()?sede.get():null);
		hospitalrepo.save(hospital);
	}

	@Override
	public Hospital obtenerHospitalPorId(Long id) {
		Optional<Hospital> hospital=hospitalrepo.findById(id);
		if(hospital.isPresent()) {
			return hospital.get();
		}else {
			return null;
		}
	}

	@Override
	@Transactional
	public void actualizarHospital(EditarHospitalDTO hospDTO) {
		Optional<Hospital> hospitalActualizado= hospitalrepo.findById(hospDTO.getId());
		if(hospitalActualizado.isPresent()) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime fechaRegistro = LocalDate.parse(hospDTO.getFechaRegistro(), formatter).atStartOfDay();
			
			hospitalActualizado.get().setNombre(hospDTO.getNombre());
			hospitalActualizado.get().setAntiguedad(hospDTO.getAntiguedad());
			hospitalActualizado.get().setArea(hospDTO.getArea());
			hospitalActualizado.get().setNombre(hospDTO.getNombre());
			hospitalActualizado.get().setFechaRegistro(fechaRegistro);
			
			Optional<Condicion> condi= condicionRepo.findById(Long.valueOf(hospDTO.getIdCondicion()));
			hospitalActualizado.get().setCondicion(condi.isPresent()?condi.get():null);
			
			Optional<Distrito> distrito= distritoRepo.findById(Long.valueOf(hospDTO.getIdDistrito()));
			hospitalActualizado.get().setDistrito(distrito.isPresent()?distrito.get():null);
			
			Optional<Gerente> gerente= gerenteRepo.findById(Long.valueOf(hospDTO.getIdGerente()));
			hospitalActualizado.get().setGerente((gerente.isPresent()?gerente.get():null));
			
			Optional<Sede> sede= sedeRepo.findById(Long.valueOf(hospDTO.getIdSede()));
			hospitalActualizado.get().setSede((sede.isPresent()?sede.get():null));

			hospitalrepo.save(hospitalActualizado.get());
		}
		
	}

	@Override
	@Transactional
	public void eliminarHospital(Long id) {
		hospitalrepo.deleteById(id);
	}

	@Override
	public List<HospitalDTO> buscarPorNombre(String nombre) {
		List<Hospital>resultados=hospitalrepo.findAllByNombreContaining(nombre);
		for (Hospital hospital : resultados) {
			System.out.println(hospital.getNombre());
		}
		List<HospitalDTO> hospitalesDto = new ArrayList<>();
		for (Hospital hospital : resultados) {
			Gerente gerente=hospital.getGerente();
			String nombreGerente=gerente.getDescGerente();
			Distrito distrito= hospital.getDistrito();
			String nombreDistrito= distrito.getDescDistrito();
			HospitalDTO hosDTO= new HospitalDTO(hospital.getId(),hospital.getNombre(), nombreDistrito, nombreGerente);
			hospitalesDto.add(hosDTO);
		}
		
		return hospitalesDto;
	}

	@Override
	public List<HospitalDTO> buscarPorIdSede(String sede) {
		
		 Optional<Sede> sedeSelect= sedeRepo.findById(Long.valueOf(sede));
		 List<HospitalDTO> hospitalesDto = new ArrayList<>();
		 if(sedeSelect.isPresent()) {
			 List<Hospital> resultados= hospitalrepo.findAllBySede(sedeSelect.get());
			 if(resultados==null) {
				 return null;
			 }
				for (Hospital hospital : resultados) {
					Gerente gerente=hospital.getGerente();
					String nombreGerente=gerente.getDescGerente();
					Distrito distrito= hospital.getDistrito();
					String nombreDistrito= distrito.getDescDistrito();
					HospitalDTO hosDTO= new HospitalDTO(hospital.getId(),hospital.getNombre(), nombreDistrito, nombreGerente);
					hospitalesDto.add(hosDTO);
				}
		 }
		
		return hospitalesDto;
	}

}
