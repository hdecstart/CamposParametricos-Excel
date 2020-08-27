package com.hdecstudio.generation.data.controller;

import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class Resource {
    
    @RequestMapping("/export")
    public String saveCSV(@RequestParam("l") Integer l, @RequestParam("data") String data){
        String msg = "";
        try{
            String[] control = data.split(",");
            String[] row = new String[control.length/l];

            String fileCSV = "C:\\DataGeneric\\data.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(fileCSV));

            int cont = 0;
            for (String item : control) {
                row[cont] = item;
                if ( cont == l-1 ){
                    writer.writeNext(row);
                    cont = 0;
                    continue;
                }
                cont++;
            }
            
            writer.close();
            msg = "Exported";
        }catch(IOException io){
            msg = "Internal error";
        }

        return msg;
    }
}