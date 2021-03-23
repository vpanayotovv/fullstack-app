package com.project.cocktailapp.model.binding;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductBindingModel {

    @Expose
    private String name;

    @Expose
    private double quantity;
}
