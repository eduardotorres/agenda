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

import com.tadi.agendadat.model.ContatoComercial;
import com.tadi.agendadat.repository.ContatoComercialRepository;

@RestController
@RequestMapping("/contatocomercial")
public class ContatoComercialService {
	
	@Autowired
	private ContatoComercialRepository contatoComercialRepository;
	
	@PostMapping
	public ContatoComercial save(@Valid @RequestBody ContatoComercial contatoComercial){
		return contatoComercialRepository.save(contatoComercial);
}
	@GetMapping
	public List<ContatoComercial> list(){
		return contatoComercialRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<ContatoComercial>> listById(@PathVariable Long id){
		Optional<ContatoComercial> cc=contatoComercialRepository.findById(id);
		if(cc==null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cc);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ContatoComercial> update(@PathVariable Long id, @Valid @RequestBody ContatoComercial contatoComercial){
		ContatoComercial cc= contatoComercialRepository.getOne(id);
		if(cc==null){
			return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(contatoComercial, cc, "id");
		cc=contatoComercialRepository.save(cc);
		return ResponseEntity.ok(cc);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		ContatoComercial cc=contatoComercialRepository.getOne(id);
		if(cc==null){
			return ResponseEntity.notFound().build();
		}
		contatoComercialRepository.delete(cc);
		return ResponseEntity.noContent().build();
	}
}
