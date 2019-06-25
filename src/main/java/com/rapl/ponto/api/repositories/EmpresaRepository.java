package com.rapl.ponto.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.rapl.ponto.api.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	@Transactional(readOnly = true)
	Empresa findByCnpj(String cnpj);
}
