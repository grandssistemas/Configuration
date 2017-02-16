package br.com.codein.configuration.application.service;

import br.com.codein.AbstractTest;
import br.com.codein.configuration.domain.model.SeedControl;
import br.com.codein.configuration.domain.model.enums.SeedType;
import io.gumga.core.GumgaThreadScope;
import io.gumga.core.SearchResult;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by rafael on 14/02/17.
 */
public class SeedControlServiceTest extends AbstractTest {

    @Autowired
    private SeedControlService service;

    private SeedControl seed;

    @Before
    public void setUp() throws Exception {
        GumgaThreadScope.organizationCode.set("1.");
        seed = new SeedControl();
        seed.setSeedVersion(1);
        seed.setType(SeedType.PAYMENT);
    }

    @Test
    public void savePayment() throws Exception {
        seed = service.save(seed);
        Assert.assertNotNull(seed.getId());
        Assert.assertNotNull(seed.getOi());

        Long id = seed.getId();

        SeedControl seedAux = new SeedControl();
        seedAux.setSeedVersion(3);
        seedAux.setType(SeedType.PAYMENT);

        Integer oldVersion = seed.getSeedVersion();
        Integer version = 3;
        seedAux = service.save(seedAux);

        Assert.assertEquals(id,seedAux.getId());
        Assert.assertNotEquals(oldVersion,seedAux.getSeedVersion());
        Assert.assertEquals(version,seedAux.getSeedVersion());
    }

    @Test
    public void getVersionExistent() throws Exception {
        service.save(seed);
        Integer version = service.getVersion(SeedType.PAYMENT);
        Integer excpected = 1;
        Assert.assertEquals(excpected,version);
    }

    @Test
    public void getVersionExistentTenacy() throws Exception {
        service.save(seed);
        Integer version = service.getVersion(SeedType.PAYMENT);
        Integer expected = 1;
        Assert.assertEquals(expected,version);
        GumgaThreadScope.organizationCode.set("2.");
        version = service.getVersion(SeedType.PAYMENT);
        Assert.assertNotEquals(expected,version);
        expected = -1;
        Assert.assertEquals(expected,version);
    }

    @Test
    public void getVersionNonExistent() throws Exception {
        Integer version = service.getVersion(SeedType.PAYMENT);
        Integer excpectedNE = 1;
        Integer excpectedE = -1;
        Assert.assertNotEquals(excpectedNE,version);
        Assert.assertEquals(excpectedE,version);
    }

    @Test
    public void getVersionNonExistentWithExistent() throws Exception {
        service.save(seed);
        Integer version = service.getVersion(SeedType.PAYMENT);
        Integer expected = 1;
        Assert.assertEquals(expected,version);
        version = service.getVersion(SeedType.TESTE);
        Assert.assertNotEquals(expected,version);
        expected = -1;
        Assert.assertEquals(expected,version);
    }

    @Test
    public void getAllWithExistent() throws Exception {
        seed = service.save(seed);
        Assert.assertNotNull(seed.getId());
        Assert.assertNotNull(seed.getOi());

        SeedControl seedAux = new SeedControl();
        seedAux.setSeedVersion(3);
        seedAux.setType(SeedType.PAYMENT);
        service.save(seedAux);

        SearchResult<SeedControl> searchResult = service.getAll();
        Long count = 1l;
        Assert.assertEquals(count,searchResult.getCount());
    }


    @After
    public void tearDown() throws Exception {
        GumgaThreadScope.organizationCode.set("1.");
        if(seed.getId() != null){
            service.delete(seed);
        }
    }
}