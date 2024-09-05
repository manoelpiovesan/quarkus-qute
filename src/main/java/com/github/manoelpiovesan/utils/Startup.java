package com.github.manoelpiovesan.utils;

import com.github.manoelpiovesan.entities.Product;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;


@ApplicationScoped
@RegisterForReflection
public class Startup {

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        System.out.println("The application is starting...");

        if(!Product.listAll().isEmpty()){
            Product.create("Product 1", 10.0, "Description 1");
            Product.create("Product 2", 20.0, "Description 2");
            Product.create("Product 3", 30.0, "Description 3");
        }else{
            System.out.println("Products already created");
        }

    }

    void onStop(@Observes ShutdownEvent ev) {
        System.out.println("The application is stopping...");
    }
}
