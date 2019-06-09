package com.tadi.agendadat.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tadi.agendadat.model.Contato;
import com.tadi.agendadat.model.EContato;
import com.tadi.agendadat.repository.EContatoRepository;

@RestController
@RequestMapping("/econtatos")
public class EContatoService {
	
	@Autowired
	private EContatoRepository eContatoRepository;
	
	@PostMapping
	public EContato save(@Valid @RequestBody EContato eContato){
		return eContatoRepository.save(eContato);
	}
	
	@GetMapping
	public List<EContato> list(){
		return eContatoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<EContato>> listById(@PathVariable Long id){
		Optional<EContato> ec= eContatoRepository.findById(id);
		if(ec==null){
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(ec);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EContato> update(@PathVariable Long id, @Valid @RequestBody EContato eContato){
		EContato ec=eContatoRepository.getOne(id);
		if(ec==null){
			return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(eContato, ec, "id");
		ec=eContatoRepository.save(ec);
		return ResponseEntity.ok(ec);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		EContato ec=eContatoRepository.getOne(id);
		if(ec==null){
			return ResponseEntity.notFound().build();
		}
		
		eContatoRepository.delete(ec);
		return ResponseEntity.noContent().build();
	}
}
