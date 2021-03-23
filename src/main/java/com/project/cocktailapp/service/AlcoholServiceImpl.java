package com.project.cocktailapp.service;

import com.google.gson.Gson;
import com.project.cocktailapp.constraints.Constants;
import com.project.cocktailapp.model.binding.AlcoholBindingModel;
import com.project.cocktailapp.model.entity.AlcoholEntity;
import com.project.cocktailapp.repository.AlcoholRepository;
import com.project.cocktailapp.util.CustomFileReader;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class AlcoholServiceImpl implements AlcoholService {

    private final CustomFileReader reader;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final AlcoholRepository alcoholRepository;
    private final Logger logger;

    @Autowired
    public AlcoholServiceImpl(CustomFileReader reader, Gson gson, ModelMapper modelMapper, AlcoholRepository alcoholRepository, Logger logger) {
        this.reader = reader;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.alcoholRepository = alcoholRepository;
        this.logger = logger;
    }

    @Override
    public void importAlcohols() throws FileNotFoundException {
        if (alcoholRepository.count() == 0) {
            String read = String.join("", reader.read(Constants.ALCOHOL_PATH));
            AlcoholBindingModel[] alcoholBindingModels = this.gson.fromJson(read, AlcoholBindingModel[].class);

            for (AlcoholBindingModel alcoholBindingModel : alcoholBindingModels) {
                AlcoholEntity alcoholEntity = modelMapper.map(alcoholBindingModel, AlcoholEntity.class);
                alcoholRepository.save(alcoholEntity);
                logger.info(alcoholEntity.getBaseName() + " is saved in DB");
            }
        }
    }
}
