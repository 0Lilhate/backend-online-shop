package com.example.catalogserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Long idImage;

    @Column(name = "url_image")
    private String urlImage;

    @ManyToOne
    @JoinColumn(name="clothe_id")
    @JsonIgnore
    private Clothe clothe;

    public Image(String urlImage, Clothe clothe) {
        this.urlImage = urlImage;
        this.clothe = clothe;
    }

    public Image(String urlImage) {
        this.urlImage = urlImage;
    }
}
