package com.project.cocktailapp.service.impl;

import com.google.gson.Gson;
import com.project.cocktailapp.constraints.Constants;
import com.project.cocktailapp.exception.EntityNotFoundException;
import com.project.cocktailapp.model.binding.CocktailAddBindingModel;
import com.project.cocktailapp.model.binding.CocktailBindingModel;
import com.project.cocktailapp.model.entity.AlcoholEntity;
import com.project.cocktailapp.model.entity.CocktailEntity;
import com.project.cocktailapp.model.entity.ProductEntity;
import com.project.cocktailapp.model.entity.UserEntity;
import com.project.cocktailapp.model.view.CocktailDetailViewModel;
import com.project.cocktailapp.model.view.CocktailViewModel;
import com.project.cocktailapp.model.view.ProductViewModel;
import com.project.cocktailapp.repository.AlcoholRepository;
import com.project.cocktailapp.repository.CocktailRepository;
import com.project.cocktailapp.repository.LogDetailsRepository;
import com.project.cocktailapp.service.CocktailService;
import com.project.cocktailapp.service.UserService;
import com.project.cocktailapp.util.CustomFileReader;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CocktailServiceImpl implements CocktailService {

    private final CocktailRepository cocktailRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final CustomFileReader reader;
    private final Logger logger;
    private final AlcoholRepository alcoholRepository;
    private final UserService userService;
    private final LogDetailsRepository logDetailsRepository;

    @Autowired
    public CocktailServiceImpl(CocktailRepository cocktailRepository, ModelMapper modelMapper, Gson gson, CustomFileReader reader, Logger logger, AlcoholRepository alcoholRepository, UserService userService, LogDetailsRepository logDetailsRepository) {
        this.cocktailRepository = cocktailRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.reader = reader;
        this.logger = logger;
        this.alcoholRepository = alcoholRepository;
        this.userService = userService;
        this.logDetailsRepository = logDetailsRepository;
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
                        new EntityNotFoundException("no such alcohol"));
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

    @Override
    public CocktailDetailViewModel findCocktailById(Long id) {

        return cocktailRepository.findById(id)
                .map(cocktail -> {
                    CocktailDetailViewModel cocktailDetailViewModel = modelMapper.map(cocktail, CocktailDetailViewModel.class);
                    cocktailDetailViewModel.setBaseAlcohol(cocktail.getBaseAlcohol().getBaseName().name());
                    cocktailDetailViewModel.setProducts(cocktail
                            .getProducts()
                            .stream()
                            .map(product -> modelMapper
                                    .map(product, ProductViewModel.class)).collect(Collectors.toSet()));
                    return cocktailDetailViewModel;
                }).orElseThrow(() -> new EntityNotFoundException("no such cocktail ID"));

    }

    @Override
    public List<CocktailViewModel> getNewestCocktails() {
        return cocktailRepository
                .findNewestCocktails()
                .stream()
                .limit(6)
                .map(cocktailEntity -> modelMapper.map(cocktailEntity,CocktailViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CocktailViewModel> getCocktailsByAlcoholId(Long id) {
       return cocktailRepository
               .findAllByBaseAlcoholId(id)
               .stream()
               .map(cocktailEntity -> modelMapper.map(cocktailEntity,CocktailViewModel.class))
               .collect(Collectors.toList());
    }

    @Override
    public boolean cocktailsExist(String name) {

        return cocktailRepository.findByNameIgnoreCase(name).isPresent();
    }

    @Override
    @Transactional
    public void addCocktail(CocktailAddBindingModel cocktailAddBindingModel) {
        CocktailEntity cocktailEntity = modelMapper.map(cocktailAddBindingModel,CocktailEntity.class);
        UserEntity userEntity = userService.getUserByUsername(cocktailAddBindingModel.getUsername());
        cocktailEntity.setUser(userEntity);
        Set<ProductEntity> products = new HashSet<>();
        String[] input = cocktailAddBindingModel.getProducts().split("\\r?\\n");
        for (String line : input) {
            String[] split = line.split("-");
            for (int i = 0; i < split.length -1; i++) {
                String product = split[0];
                double quantity = Double.parseDouble(split[1]);
                ProductEntity productEntity = new ProductEntity();
                productEntity.setName(product);
                productEntity.setQuantity(quantity);
                products.add(productEntity);
            }
        }
        cocktailEntity.setProducts(products);
        cocktailEntity.setAddedOn(LocalDateTime.now());
        cocktailEntity.setBaseAlcohol(alcoholRepository
                .findByBaseName(cocktailAddBindingModel.getBaseAlcohol())
                .orElseThrow(()-> new EntityNotFoundException("no such alcohol")));

        System.out.println();
        cocktailRepository.save(cocktailEntity);
    }

    @Override
    public CocktailEntity getById(Long id) {
        return cocktailRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("no such cocktail"));
    }

    public List<CocktailViewModel> getMostViewedCocktails(){

        List<Integer> mostViewed = logDetailsRepository.findMostViewed();
        List<CocktailViewModel> cocktailViewModels = new ArrayList<>();

        for (Integer cocktail : mostViewed) {
            CocktailEntity cocktailEntity = cocktailRepository.findById( (long) cocktail).orElseThrow(() ->  new EntityNotFoundException("no such cocktail"));
            CocktailViewModel mapped = modelMapper.map(cocktailEntity, CocktailViewModel.class);
            cocktailViewModels.add(mapped);
        }

        return cocktailViewModels;
    }
}
