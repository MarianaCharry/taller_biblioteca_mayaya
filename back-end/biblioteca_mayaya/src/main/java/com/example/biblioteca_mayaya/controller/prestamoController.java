package com.example.biblioteca_mayaya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.biblioteca_mayaya.interfaceService.IPrestamoService;
import com.example.biblioteca_mayaya.models.prestamo;


@RestController
@RequestMapping("/api/v1/prestamo")
@CrossOrigin
public class prestamoController {
	
	@Autowired
	  


	  private IPrestamoService prestamoService;
		/*
		 * retorna un json , indicando si funciono, presentó
		 * error, los datos solicitados
		 */
	  
	  @PostMapping("/")
	  public ResponseEntity<Object> save(@ModelAttribute("ingreso") ingreso ingreso) {
		    // V erificar si el paciente ya tiene un ingreso activo
		    List<prestamo> listaPacienteA = ingresoService.filtroEstado(ingreso.getPaciente().getId_paciente());
		    if (!listaPacienteA.isEmpty()) {
		        return new ResponseEntity<>("el paciente ya tiene un ingreso activo", HttpStatus.BAD_REQUEST);
		    }
	 	    List<ingreso> ingresos = ingresoService.filtroCamaOcupada(ingreso.getCama(), ingreso.getHabitacion());
		    if (!ingresos.isEmpty()) {
		        return new ResponseEntity<>("La cama y la habitación ya están ocupadas", HttpStatus.BAD_REQUEST);
		    }
		           
		    	  
		    // Guardar el nuevo ingreso

		    ingresoService.save(ingreso);

		    return new ResponseEntity<>(ingreso, HttpStatus.OK);
		    
		}
	  
		@GetMapping("/")
		public ResponseEntity<Object>findAll(){
			var ListaIngreso = ingresoService.findAll();
			return new ResponseEntity<>(ListaIngreso, HttpStatus.OK);
		}
		
		//filtro
			@GetMapping("/busquedafiltro/{filtro}")
			public ResponseEntity<Object>filtroIngreso(@PathVariable String filtro){
				var ListaIngreso = ingresoService.filtroIngreso(filtro);
				return new ResponseEntity<>(ListaIngreso, HttpStatus.OK);
			}
		
		//@PathVariable recibe una variable por el enlace 
		
		@GetMapping("/{id_ingreso}")
		public ResponseEntity<Object> findOne ( @PathVariable String id_ingreso ){
			var cliente= ingresoService.findOne(id_ingreso);
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<Object> delete ( @PathVariable String id ){
			 var ingreso= ingresoService.findOne(id).get();
			 if (ingreso!=null) {
				 if (ingreso.getEstado().equals("H")) {
					 ingreso.setEstado("AH");
					 ingresoService.save(ingreso);
					 return new ResponseEntity<>("Se ha deshabilitado correctamente", HttpStatus.OK);
					 
				 } else 
					 ingreso.setEstado("H");
				 ingresoService.save(ingreso);   
				 return new ResponseEntity<>("Se ha habilitado correctamente",HttpStatus.OK);
				 
			 } else {
				 return new ResponseEntity<>("No se ha encontrado el medico", HttpStatus.BAD_REQUEST);
			 }
		
		
	     }
			
		
		
		@PutMapping("/{id_ingreso}")
		public ResponseEntity<Object> update  ( @PathVariable String id_ingreso, @ModelAttribute("ingreso") ingreso ingresoUpdate){
			var ingreso= ingresoService.findOne(id_ingreso).get();
			if (ingreso != null) {
				
				ingreso.setPaciente(ingresoUpdate.getPaciente());
				ingreso.setMedico(ingresoUpdate.getMedico());
				ingreso.setHabitacion(ingresoUpdate.getHabitacion());
				ingreso.setCama(ingresoUpdate.getCama());
				ingreso.setFecha_ingreso(ingresoUpdate.getFecha_ingreso());
				ingreso.setFecha_salida(ingresoUpdate.getFecha_salida());
				ingreso.setEstado(ingresoUpdate.getEstado());
				
				ingresoService.save(ingreso);
				return new ResponseEntity<>("Guardado", HttpStatus.OK);
				
			}
			else {
				return new ResponseEntity<>("Error ingreso no encontrado", HttpStatus.BAD_REQUEST);
			}
		}
}
