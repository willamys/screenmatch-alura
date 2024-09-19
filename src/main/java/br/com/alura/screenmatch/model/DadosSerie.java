package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) //igonar desconhecidos
public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("imdbRating") String avaliacao) {
//@JsonProperty serializa e desearilza com o nome informado
//@JsonAlias serializa o dado que vem da api que tem esse nome, podemos passar um {"nome1", "nome2"}
}
