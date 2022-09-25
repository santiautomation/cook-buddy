package com.github.santiautomation.cookbuddy.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "subcategory")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id")
    private Long subcategoryId;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="category_id")
    private Category category;
}
