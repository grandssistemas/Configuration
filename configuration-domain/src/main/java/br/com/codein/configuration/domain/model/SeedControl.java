package br.com.codein.configuration.domain.model;

import br.com.codein.configuration.domain.model.enums.SeedType;
import io.gumga.domain.GumgaModel;
import io.gumga.domain.GumgaMultitenancy;
import io.gumga.domain.GumgaMultitenancyPolicy;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by rafael on 14/02/17.
 */
@GumgaMultitenancy(policy = GumgaMultitenancyPolicy.ORGANIZATIONAL)
@SequenceGenerator(name = GumgaModel.SEQ_NAME, sequenceName = "SEQ_SEEDCONTROL")
@Audited
@Entity
public class SeedControl extends GumgaModel<Long> implements Serializable {

    @Version
    @ApiModelProperty(hidden = true)
    private Integer version;

    @NotNull(message = "The seedVersion field can't be null")
    @ApiModelProperty(value = "Versão do seed que a organização está", position = 1, required = true)
    private Integer seedVersion;

    @NotNull(message = "The type field can't be null")
    @ApiModelProperty(value = "Tipo da classe que aquela versão é valida", position = 2, required = true)
    @Enumerated(EnumType.STRING)
    private SeedType type;

    public SeedControl() {
    }

    public Integer getSeedVersion() {
        return seedVersion;
    }

    public void setSeedVersion(Integer seedVersion) {
        this.seedVersion = seedVersion;
    }

    public SeedType getType() {
        return type;
    }

    public void setType(SeedType type) {
        this.type = type;
    }
}
