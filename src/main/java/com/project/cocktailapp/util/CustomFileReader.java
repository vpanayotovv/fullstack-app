package com.project.cocktailapp.util;

import java.io.FileNotFoundException;
import java.util.List;

public interface CustomFileReader {

    List<String> read(String path) throws FileNotFoundException;

}
