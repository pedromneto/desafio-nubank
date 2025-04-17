package com.nubank.clientmanager.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
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

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContatoEntity> contatos;

    public void adicionarContato(ContatoEntity contato) {
        contato.setCliente(this);
        this.contatos.add(contato);
    }

    public void removerContato(ContatoEntity contato) {
        this.contatos.remove(contato);
        contato.setCliente(null);
    }

}
