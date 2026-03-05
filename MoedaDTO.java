package org.example.modelos;

// Aqui dizemos que queremos a sigla (base_code) e a lista de taxas (conversion_rates)
import java.util.Map;

public record MoedaDTO(String base_code, Map<String, Double> conversion_rates) {
}