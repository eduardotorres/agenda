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
import com.tadi.agendadat.repository.ContatoRepository;


@RestController
@RequestMapping("/contatos")
public class ContatoService {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@PostMapping
	public Contato save(@Valid @RequestBody Contato contato){
		return contatoRepository.save(contato);
	}
	
	@GetMapping
	public List<Contato> list(){
		return contatoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Contato>> listById(@PathVariable Long id){
		Optional<Contato> c= contatoRepository.findById(id);
		if(c==null){
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(c);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contato> update(@PathVariable Long id, @Valid @RequestBody Contato contato){
		Contato c=contatoRepository.getOne(id);
		if(c==null){
			return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(contato, c, "id");
		c=contatoRepository.save(c);
		return ResponseEntity.ok(c);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Contato c=contatoRepository.getOne(id);
		if(c==null){
			return ResponseEntity.notFound().build();
		}
		
		contatoRepository.delete(c);
		return ResponseEntity.noContent().build();
	}
}
