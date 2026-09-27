package com.ga.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }


    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "category", orphanRemoval = true)
    private List<Item> recipeList;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


}
