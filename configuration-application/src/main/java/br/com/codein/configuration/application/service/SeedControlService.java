package br.com.codein.configuration.application.service;

import br.com.codein.configuration.application.repository.SeedControlRepository;
import br.com.codein.configuration.domain.model.SeedControl;
import br.com.codein.configuration.domain.model.enums.SeedType;
import io.gumga.application.GumgaService;
import io.gumga.core.QueryObject;
import io.gumga.core.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeedControlService extends GumgaService<SeedControl, Long> {

    private SeedControlRepository repository;

    @Autowired
    public SeedControlService(SeedControlRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    @Transactional
    public SeedControl save(SeedControl resource) {
        SeedControl saved = getSeedControlByType(resource.getType());
        if(saved != null){
            saved.setSeedVersion(resource.getSeedVersion());
            super.save(saved);
            return saved;
        }
        super.save(resource);
        return resource;
    }

    @Transactional
    public Integer getVersion(SeedType type) {
        SeedControl control = getSeedControlByType(type);
        if(control == null){
            return -1;
        }
        return control.getSeedVersion();
    }

    private SeedControl getSeedControlByType(SeedType type) {
        String hql = "obj.type = '" + type.name() + "'";
        QueryObject query = new QueryObject();
        query.setAq(hql);
        SearchResult<SeedControl> search = repository.search(query);
        if (search.getCount() == 0) {
            return null;
        }
        return search.getValues().get(0);
    }


}
