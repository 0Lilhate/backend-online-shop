package com.example.catalogserver.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sizes")
public class Size{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_size")
    private Long idSize;

    @Column(name = "eur_size")
    private String eurSize;

    @Column(name = "ru_size")
    private String ruSize;

    @Column(name = "world_size")
    private String worldSize;

//    @ManyToOne
//    @JoinColumn(name="clothe_id")
//    private Clothe clothe;



    public Size(String eurSize, String ruSize, String worldSize) {
        this.eurSize = eurSize;
        this.ruSize = ruSize;
        this.worldSize = worldSize;
    }
}
