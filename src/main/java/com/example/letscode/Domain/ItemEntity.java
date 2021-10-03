package com.example.letscode.Domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    @NotBlank(message = "Write sm!")
    @Length(max = 2048,message = "too long!" )
    private String text;
    @Length(max = 1024,message = "too long!" )
    private String tag;
    @NotNull(message = "Write sm integer!")
    private Integer startPrice;
    private Integer price;
    private Integer finishPrice;
    private Boolean isBought = false;


    private String filename;

    public String getAuthorName(){
        return owner.getUsername();
    }
}
