package com.tismart.hospital.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tismart.hospital.dao.ISedeRepository;
import com.tismart.hospital.model.Hospital;
import com.tismart.hospital.model.dto.EditarHospitalDTO;
import com.tismart.hospital.model.dto.HospitalDTO;
import com.tismart.hospital.model.dto.RegistrarHospitalDTO;
import com.tismart.hospital.service.IHospitalService;

@Controller
@RequestMapping("/main")
public class HospitalController {
	@Autowired
	private IHospitalService hospitalService;
	@Autowired
	private ISedeRepository sedeRepository;
	
	@GetMapping
	public String menuPrincipal(Model model) {
		model.addAttribute("hospitales", hospitalService.listarHospitales());
		model.addAttribute("sedes", sedeRepository.findAll());
		return "menu";
	}
	@GetMapping("/nuevo")
	public String formRegistrarHospital(Model model) {
		RegistrarHospitalDTO hospital= new RegistrarHospitalDTO();
		model.addAttribute("hospital", hospital);
		model.addAttribute("sedes", sedeRepository.findAll());
		return "registrar_hospital";
	}
	@PostMapping("/registrar")
	public String registrarHospital(@ModelAttribute("hospital") RegistrarHospitalDTO hospDto) {
		hospitalService.registrarHospital(hospDto);
		return "redirect:/main";
	}
	
	@GetMapping("/editar/{id}")
	public String formEditarHospital(@PathVariable Long id,Model model ) {
		Hospital hospital = hospitalService.obtenerHospitalPorId(id);
		if(hospital==null) return "redirect:/main";
		
		RegistrarHospitalDTO hospitalData= new RegistrarHospitalDTO();
		hospitalData.setId(id);
		hospitalData.setNombre(hospital.getNombre());
		hospitalData.setAntiguedad(hospital.getAntiguedad());
		hospitalData.setFechaRegistro(hospital.getFechaRegistro().toLocalDate().toString());
		hospitalData.setArea(hospital.getArea());
		hospitalData.setIdCondicion(hospital.getCondicion().getId().toString());
		hospitalData.setIdDistrito(hospital.getDistrito().getId().toString());
		hospitalData.setIdGerente(hospital.getGerente().getId().toString());
		hospitalData.setIdSede(hospital.getSede().getId().toString());
	
		model.addAttribute("hospital",hospitalData );
		model.addAttribute("sedes", sedeRepository.findAll());
		return "editar_hospital";
	}
	@PostMapping("/editar/{id}")
	public String editarHospital(@PathVariable Long id, @ModelAttribute("hospital") EditarHospitalDTO hospDTO) {
		hospDTO.setId(id);
		hospitalService.actualizarHospital(hospDTO);
		return "redirect:/main";
	}
	@GetMapping("/{id}")
	public String eliminarHospital(@PathVariable Long id) {
		hospitalService.eliminarHospital(id);
		return "redirect:/main";
	}
	@GetMapping("/busqueda")
	public String busquedaHospital(
			@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String sede,
			Model model
			) {
		List<HospitalDTO> resultados= new ArrayList<>();
		Boolean datos= false;
		int cantidad=0;
		if(sede==null) {
			resultados= hospitalService.buscarPorNombre(nombre);
		}else if(nombre==null) {
			resultados= hospitalService.buscarPorIdSede(sede);
		}
		if(resultados!=null) {
			datos=true;
			cantidad=resultados.size();
			model.addAttribute("datos", datos);
		}
		
		model.addAttribute("resultados", resultados);
		model.addAttribute("cantidad", cantidad);
		return "busqueda";
		
	}
}

