package com.test.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.test.demo.DTO.PetResponseDTO;

@Service
public class TestPetService {

    private static final String urlString = "https://petstore.swagger.io/v2/pet/findByStatus?status=";

    public PetResponseDTO getMyPetCountByStatus(String status, String petName) {
        JSONArray json = null;
        PetResponseDTO pet = new PetResponseDTO();
        int doggie = 0;
        try {
            json = readJsonFromUrl(urlString + status);

            System.out.println("Json output  => "+json.toString());

            for (int i = 0; i < json.length(); i++) {
                JSONObject dog = new JSONObject(json.get(i).toString());
                if (petName != null && dog.has("name") && petName.equalsIgnoreCase(dog.getString("name")))
                    doggie++;
            }
            pet.petCount = doggie;
            return pet;
        } catch (IOException | JSONException e) {
            pet.petCount = -1;
            pet.errorMessage = e.getMessage();
            return pet;
        }

    }

    public static JSONArray readJsonFromUrl(String url) throws IOException, JSONException {

        InputStream is = new URL(url).openStream();

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private static String readAll(BufferedReader rd) throws IOException {

        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = rd.readLine()) != null) {
            response.append(inputLine);
        }
        return response.toString();
    }

}
