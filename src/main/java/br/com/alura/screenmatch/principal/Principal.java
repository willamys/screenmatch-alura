package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    Scanner leitura = new Scanner(System.in);

    private static final String ENDERECO = "http://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=eb1bd517";

    private ConsumoAPI consumoAPI =  new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();

    public void exibeMenu(){

        System.out.println("Digite o nome da série:");
        var nomeSerie = leitura.nextLine();
        var json = consumoAPI.obterDados(ENDERECO.concat(nomeSerie.replace(" ", "+")).concat(API_KEY));
        DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
			json = consumoAPI.obterDados(ENDERECO.concat(nomeSerie.replace(" ", "+")).concat("&season="+i).concat(API_KEY));
			DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}
