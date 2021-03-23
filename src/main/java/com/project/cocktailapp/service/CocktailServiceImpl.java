package com.project.cocktailapp.service;

import com.google.gson.Gson;
import com.project.cocktailapp.constraints.Constants;
import com.project.cocktailapp.model.binding.CocktailBindingModel;
import com.project.cocktailapp.model.entity.AlcoholEntity;
import com.project.cocktailapp.model.entity.CocktailEntity;
import com.project.cocktailapp.model.view.CocktailViewModel;
import com.project.cocktailapp.repository.AlcoholRepository;
import com.project.cocktailapp.repository.CocktailRepository;
import com.project.cocktailapp.util.CustomFileReader;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CocktailServiceImpl implements CocktailService {

    private final CocktailRepository cocktailRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final CustomFileReader reader;
    private final Logger logger;
    private final AlcoholRepository alcoholRepository;

    @Autowired
    public CocktailServiceImpl(CocktailRepository cocktailRepository, ModelMapper modelMapper, Gson gson, CustomFileReader reader, Logger logger, AlcoholRepository alcoholRepository) {
        this.cocktailRepository = cocktailRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.reader = reader;
        this.logger = logger;
        this.alcoholRepository = alcoholRepository;
    }

    @Override
    public void importCocktails() throws FileNotFoundException {

        if (cocktailRepository.count() == 0) {

            String read = String.join("", reader.read(Constants.COCKTAILS_PATH));
            CocktailBindingModel[] cocktailBindingModels = this.gson.fromJson(read, CocktailBindingModel[].class);

            for (CocktailBindingModel cocktailBindingModel : cocktailBindingModels) {
                CocktailEntity cocktailEntity = modelMapper.map(cocktailBindingModel, CocktailEntity.class);
                cocktailEntity.setAddedOn(LocalDateTime.now());
                AlcoholEntity alcoholEntity = alcoholRepository.findByBaseName(cocktailBindingModel.getBaseAlcohol()).orElseThrow(() ->
                        new IllegalArgumentException("no such alcohol"));
                cocktailEntity.setBaseAlcohol(alcoholEntity);
                cocktailRepository.save(cocktailEntity);
                logger.info(cocktailEntity.getName() + " is saved in DB");
            }
        }
    }

    @Override
    public List<CocktailViewModel> getAllCocktails() {
       return cocktailRepository
               .findAll()
               .stream()
               .map(cocktailEntity -> modelMapper.map(cocktailEntity,CocktailViewModel.class))
               .collect(Collectors.toList());
    }
}
