package com.academia.dio.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author Jhansen Barreto
 */
@Data
@Entity
public class AvaliacaoFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp", nullable = false)
    private LocalDateTime dataAvaliacao;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false)
    private Double altura;

    public String getIMC() {
        return String.format("%.2f", (peso / (altura * altura)));
    }
}
