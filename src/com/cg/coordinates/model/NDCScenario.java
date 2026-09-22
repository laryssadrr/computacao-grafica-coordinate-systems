package com.cg.coordinates.model;

/**
 * Representa os dois cenarios possiveis de coordenadas normalizadas
 * do dispositivo (NDC):
 *
 *  UNIT      -> intervalo [0,1] x [0,1]
 *  CENTERED  -> intervalo [-1,1] x [-1,1]
 */
public enum NDCScenario {

    UNIT("[0,1] x [0,1]"),
    CENTERED("[-1,1] x [-1,1]");

    private final String descricao;

    NDCScenario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
