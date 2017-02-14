package br.com.codein.configuration.application.service;

import br.com.codein.AbstractTest;
import br.com.codein.configuration.domain.model.SeedControl;
import br.com.codein.configuration.domain.model.enums.SeedType;
import io.gumga.core.GumgaThreadScope;
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

//    @Test
    public void savePayment() throws Exception {

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


    @After
    public void tearDown() throws Exception {
        GumgaThreadScope.organizationCode.set("1.");
        if(seed.getId() != null){
            service.delete(seed);
        }
    }
}