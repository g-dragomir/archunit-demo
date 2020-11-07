package com.demo.product.rest;

import com.demo.product.domain.ProductItem;
import com.demo.product.service.ProductService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Product API", tags = "Product APIs")
@RestController
@RequestMapping(path = "products",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity getProductById(@PathVariable("productId") int productId) {

        try {
            ProductItem productItem = productService.getProductItemById(productId);

            return ResponseEntity.ok().body(productItem);

        } catch (Exception ex) {
            LOGGER.error("Error getting product with id: " + productId, ex);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // other APIs ...
}
