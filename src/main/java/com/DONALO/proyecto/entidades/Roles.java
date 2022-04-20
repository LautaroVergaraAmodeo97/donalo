package com.DONALO.proyecto.entidades;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public Set<Usuario> getUsers() {
        return users;
    }

    public void setUsers(Set<Usuario> users) {
        this.users = users;
    }
}