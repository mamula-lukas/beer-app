package com.example.beer_app.excepion;

import jakarta.annotation.Nonnull;

/**
 * @author mamula.lukas@gmail.com
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(@Nonnull String message) {
        super(message);
    }

}