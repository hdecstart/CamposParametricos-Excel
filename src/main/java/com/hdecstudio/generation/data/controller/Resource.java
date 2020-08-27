package com.hdecstudio.generation.data.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class Resource {
    
    @RequestMapping("/export")
    public String saveCSV(@RequestParam("l") Integer l, 
    @RequestParam("header") String header,
    @RequestParam("data") String data){
        String msg = "";
        try{
            String[] control = data.split(",");

            String fileCSV = "C:\\DataGeneric\\data.csv";
            File file = new File(fileCSV);
            PrintWriter pw = new PrintWriter(file);
            pw.println(header);

            int cont = 0;
            for (String item : control) {
                if ( cont != l -1 ){
                    pw.print(item+",");
                }else{
                    pw.println(item);
                    cont = 0;
                    continue;
                }
                cont++;
            }
            
            pw.close();
            msg = "Exported";
        }catch(IOException io){
            msg = "Internal error";
        }

        return msg;
    }
}