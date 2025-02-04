package com.company.gestionworkflow.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@JmixEntity
@Table(name = "VENTE", uniqueConstraints = {
        @UniqueConstraint(name = "IDX_VENTE_UNQ", columnNames = {"REFERENCE"})
})
@Entity
public class Vente extends Workflow {
    @Column(name = "REFERENCE", nullable = false)
    @NotNull
    private String reference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}