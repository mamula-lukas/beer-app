package com.example.beer_app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author mamula.lukas@gmail.com
 */
@Data
@Document(collection = "BeerScore")
public class BeerScore{
    @Id
    private String id;
    @Indexed(unique = true)
    private String beerId;
    @Indexed(unique = true)
    private String userId;
    private String userName;
    private String email;
    private int score;
    private String comment;
}
