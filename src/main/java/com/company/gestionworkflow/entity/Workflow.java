package com.company.gestionworkflow.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@MappedSuperclass
public abstract class Workflow {
    @InstanceName
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private State status; // État actuel de l'entité

    @Column(nullable = false)
    private String codeWorkflow;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }

    public String getCodeWorkflow() {
        return codeWorkflow;
    }

    public void setCodeWorkflow(String codeWorkflow) {
        this.codeWorkflow = codeWorkflow;
    }
}