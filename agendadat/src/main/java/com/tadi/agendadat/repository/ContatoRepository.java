package com.tadi.agendadat.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tadi.agendadat.model.Contato;

public interface ContatoRepository  extends JpaRepository<Contato, Long>{
	

}
