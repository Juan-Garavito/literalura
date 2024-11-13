package com.reto.literalura.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Peticion {

    private HttpClient httpClient;
    private HttpResponse httpResponse;
    private HttpRequest httpRequest;


    public Peticion(){
        this.httpClient = HttpClient.newBuilder().build();
    }

    public String hacerPeticion(String url) {
        try {
            httpRequest = HttpRequest.newBuilder(new URI(url)).build();
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        }catch (URISyntaxException e){
            System.out.println("Error con la url de la api");
        }catch (IOException | InterruptedException e){
            System.out.println("Error con la conexion de la api");
        }
        System.out.println(httpResponse.toString());
        return httpResponse.body().toString();
    }
}
