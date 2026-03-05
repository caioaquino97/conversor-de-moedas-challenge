package org.example.ferramentas;

import com.google.gson.Gson;
import org.example.modelos.MoedaDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoeda {
    public MoedaDTO buscaMoeda(String codigoMoeda) {
        // ATENÇÃO: Você vai precisar de uma chave da API para colocar aqui depois
        String endereco = "https://v6.exchangerate-api.com/v6/68c1a6036bb40d028e88c0fe/latest/" + codigoMoeda;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Aqui o GSON entra em ação traduzindo o texto do site para o nosso MoedaDTO
            return new Gson().fromJson(response.body(), MoedaDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Não consegui obter os valores da moeda. Verifique a chave ou a conexão.");
        }
    }
}