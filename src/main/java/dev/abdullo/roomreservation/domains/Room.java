package dev.abdullo.roomreservation.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Room implements BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer capacity;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Field field;
}
