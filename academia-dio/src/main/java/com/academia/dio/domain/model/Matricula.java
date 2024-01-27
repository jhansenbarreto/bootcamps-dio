package com.academia.dio.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author Jhansen Barreto
 */
@Data
@Entity
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @OneToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp", nullable = false)
    private LocalDateTime dataMatricula;

    @Column(nullable = false)
    private Boolean ativa = Boolean.TRUE;

    @Column(columnDefinition = "timestamp")
    private LocalDateTime dataDesmatricula = null;
}
