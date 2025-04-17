package com.nubank.clientmanager.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "contato")
public class ContatoEntity {
    @Getter
    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "nome", nullable = false,length = 200)
    private String nome;

    @Getter
    @Setter
    @Column(name = "email", nullable = true,length = 200)
    private String email;

    @Getter
    @Setter
    @Column(name = "telefone", nullable = false,length = 15)
    private String telefone;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="cliente_id")
    private ClienteEntity cliente;
}
