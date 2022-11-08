package com.example.catalogserver.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "descriptions")
public class Description{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_description")
    private Long idDescription;

    @Column(name = "textile")
    private String textile;

    @Column(name = "about_cloth")
    private String aboutCloth;

    public Description(String textile, String aboutCloth) {
        this.textile = textile;
        this.aboutCloth = aboutCloth;
    }
}
