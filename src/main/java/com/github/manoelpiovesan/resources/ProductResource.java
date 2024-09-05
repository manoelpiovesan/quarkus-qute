package com.github.manoelpiovesan.resources;


import com.github.manoelpiovesan.entities.Product;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateGlobal;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;


@Path("/products")
public class ProductResource {

    @Inject
    Template products;

    @Inject
    Template add_product;

    public ProductResource(Template products, Template add_product) {
        this.products = products;
        this.add_product = add_product;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(Product.listAll()).build();
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response create(@FormParam("name") String name, @FormParam("price") Double price, @FormParam("description") String description) {
        Product.create(name, price, description);
        return Response.seeOther(URI.create("/products/view")).build();
    }

    /*
    Page to view all products
     */
    // TODO(manoel): separate the view from the controller.
    @GET
    @Path("/view")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance view() {
        return products.data("products", Product.listAll());
    }

    /*
    Page to create a new product
     */

    @GET
    @Path("/new")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance newProduct() {
        return add_product.data("products", Product.listAll());
    }


}
