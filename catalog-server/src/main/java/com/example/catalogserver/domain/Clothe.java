package com.example.catalogserver.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clothes",uniqueConstraints = @UniqueConstraint(columnNames = {"name_cloth", "color_cloth" }))
public class Clothe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cloth")
    private Long idCloth;

    @Column(name = "name_cloth")
    private String nameCloth;

    @Column(name = "color_cloth")
    private String colorCloth;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price")
    private String price;

    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @OneToMany(mappedBy="clothe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @ManyToMany(targetEntity = Size.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "clothe_size", joinColumns = @JoinColumn(name = "id_cloth"),
            inverseJoinColumns = @JoinColumn(name = "id_size"))
//    @JsonBackReference
    private Set<Size> size;


    @OneToMany(mappedBy = "clothe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonBackReference
    private List<Image> images = new ArrayList<>();


    @OneToOne(fetch = FetchType.EAGER)

    private Description description;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
//    @JsonBackReference
    private Gender gender;

    public Clothe(String nameCloth, String colorCloth, Long quantity, String price, Set<Size> size, List<Image> images, Description description, Gender gender) {
        this.nameCloth = nameCloth;
        this.colorCloth = colorCloth;
        this.quantity = quantity;
        this.price = price;
        this.size = size;
        this.images = images;
        this.description = description;
        this.gender = gender;
    }

    public Clothe(String nameCloth, String colorCloth, Long quantity, String price, Set<Size> size, Description description, Gender gender) {
        this.nameCloth = nameCloth;
        this.colorCloth = colorCloth;
        this.quantity = quantity;
        this.price = price;
        this.size = size;
        this.description = description;
        this.gender = gender;
    }
}
