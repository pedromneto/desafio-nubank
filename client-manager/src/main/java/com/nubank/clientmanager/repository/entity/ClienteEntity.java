package com.nubank.clientmanager.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
public class ClienteEntity  {
    @Getter
    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "nome", nullable = false,length = 200)
    private String nome;

}
