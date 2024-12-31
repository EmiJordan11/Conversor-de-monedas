package org.example.servicios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.dto.DTOConversion;
import org.example.entidades.Conversion;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorService {
    public static Conversion consumoApi(String monedas, float cantidad){
        String url = "https://v6.exchangerate-api.com/v6/3c7fa5aa4ad58465c36b8a2f/pair/";
        Conversion conversion = new Conversion();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url+monedas+"/"+cantidad))
                .build();
        try{
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            Gson gson =new GsonBuilder()
                    .create();

            //convierto el JSON a DTO
            DTOConversion dtoConversion = gson.fromJson(json, DTOConversion.class);

            //convierto el DTO a la entidad correspondiente
           conversion = new Conversion(dtoConversion);

        } catch(IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
        return conversion;
    }
}
