package br.com.codein.configuration.application.service;

import br.com.codein.configuration.application.repository.SeedControlRepository;
import br.com.codein.configuration.domain.model.SeedControl;
import io.gumga.application.GumgaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeedControlService extends GumgaService<SeedControl, Long> {

	private SeedControlRepository repository;

	@Autowired
	public SeedControlService(SeedControlRepository repository) {
		super(repository);
		this.repository = repository;
	}
}
