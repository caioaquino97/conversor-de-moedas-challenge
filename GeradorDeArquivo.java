package org.example.ferramentas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List; // Importante para aceitar a lista

public class GeradorDeArquivo {
    // Verifique se o que está entre parênteses é (List<String> listaDeConversoes)
    public void salvaJson(List<String> listaDeConversoes) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        FileWriter escrita = new FileWriter("historico_conversoes.json");
        escrita.write(gson.toJson(listaDeConversoes));
        escrita.close();

        System.out.println("\nArquivo JSON gerado com sucesso!");
    }
}