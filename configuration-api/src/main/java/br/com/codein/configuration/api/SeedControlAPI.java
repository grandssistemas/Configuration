package br.com.codein.configuration.api;

import br.com.codein.configuration.domain.model.SeedControl;
import io.gumga.application.GumgaService;
import io.gumga.presentation.GumgaAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seedcontrol")
public class SeedControlAPI extends GumgaAPI<SeedControl, Long> {

    @Autowired
    public SeedControlAPI(GumgaService<SeedControl, Long> service) {
        super(service);
    }


}

