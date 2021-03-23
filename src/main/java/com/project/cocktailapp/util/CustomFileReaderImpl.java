package com.project.cocktailapp.util;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Component
public class CustomFileReaderImpl implements CustomFileReader {
    @Override
    public List<String> read(String path) throws FileNotFoundException {
        List<String> result = new LinkedList<>();
        Scanner scanner = new Scanner(new FileInputStream(path));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!"".equals(line)) {
                result.add(line);
            }
        }

        return result;
    }
}
