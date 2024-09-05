package com.github.manoelpiovesan.utils;

import com.github.manoelpiovesan.entities.Product;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
@RegisterForReflection
public class Startup {


    @Transactional
    void onStart(@Observes StartupEvent ev) {
        System.out.println("The application is starting...");

        if (Product.listAll().isEmpty()) {
            for (int i = 0; i < 10; i++) {
                Product.create("Product " + i, 10.0 * (i + 1), "Description " + i);
            }
        } else {
            System.out.println("Products already created");
        }

    }

    void onStop(@Observes ShutdownEvent ev) {
        System.out.println("The application is stopping...");
    }
}
