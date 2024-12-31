package org.example.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dto.DTOConversion;

@Getter
@Setter
@NoArgsConstructor
public class Conversion {
    private String monedaOrigen;
    private String monedaDestino;
    private float ratio;
    private float resultado;

    public Conversion(DTOConversion dto) {
        this.monedaOrigen = dto.base_code();
        this.monedaDestino = dto.target_code();
        this.ratio = dto.conversion_rate();
        this.resultado = dto.conversion_result();
    }
}
