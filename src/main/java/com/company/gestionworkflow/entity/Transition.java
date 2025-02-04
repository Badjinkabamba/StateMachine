package com.company.gestionworkflow.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JmixEntity
@Table(name = "TRANSITION", indexes = {
        @Index(name = "IDX_TRANSITION_SOURCE_STATE", columnList = "SOURCE_STATE_ID"),
        @Index(name = "IDX_TRANSITION_TARGET_STATE", columnList = "TARGET_STATE_ID"),
        @Index(name = "IDX_TRANSITION_EVENT", columnList = "EVENT_ID")
})
@Entity
public class Transition {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @JoinColumn(name = "SOURCE_STATE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private State sourceState;
    @JoinColumn(name = "EVENT_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Event event;
    @JoinColumn(name = "TARGET_STATE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private State targetState;
    @Column(name = "CODE_WORKFLOW", nullable = false)
    @NotNull
    private String codeWorkflow;
    @Column(name = "REQUIRED_ROLE")
    private String required_role;

    public String getRequired_role() {
        return required_role;
    }

    public void setRequired_role(String required_role) {
        this.required_role = required_role;
    }

    public String getCodeWorkflow() {
        return codeWorkflow;
    }

    public void setCodeWorkflow(String codeWorkflow) {
        this.codeWorkflow = codeWorkflow;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public State getTargetState() {
        return targetState;
    }

    public void setTargetState(State targetState) {
        this.targetState = targetState;
    }

    public State getSourceState() {
        return sourceState;
    }

    public void setSourceState(State sourceState) {
        this.sourceState = sourceState;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}