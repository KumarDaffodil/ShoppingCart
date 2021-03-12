package com.example.ShoppingCart;

import com.example.ShoppingCart.models.data.Pages;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.Optional;


@EnableJpaRepositories
public interface PageRepository extends JpaRepository<Pages,Integer> {




    Pages findBySlug(String slug);

    @Query("SELECT p FROM Pages p WHERE p.slug=:slug and p.id!=:id")
    Pages findBySlug(String slug,int id);
}
