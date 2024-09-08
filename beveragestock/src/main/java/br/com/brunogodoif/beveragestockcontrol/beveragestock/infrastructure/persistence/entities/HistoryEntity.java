package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", nullable = false)
    private OperationTypeEntity operationType;

    @Column(name = "volume", nullable = false)
    private double volume;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "responsible", nullable = false, length = 100)
    private String responsible;

    @ManyToOne(fetch = FetchType.LAZY) // Set to LAZY to improve performance
    @JoinColumn(name = "section_id", nullable = false)
    private SectionEntity section;

    @ManyToOne(fetch = FetchType.LAZY) // Set to LAZY to improve performance
    @JoinColumn(name = "beverage_id", nullable = false)
    private BeverageEntity beverage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryEntity that = (HistoryEntity) o;
        return Objects.equals(id, that.id); // Usar apenas o ID para verificar igualdade
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Usar apenas o ID para calcular o hash
    }
}
