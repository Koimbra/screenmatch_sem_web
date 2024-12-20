package br.com.screenmatch.koimbra.screenmatch.principal;

import br.com.screenmatch.koimbra.screenmatch.model.DadosSerie;
import br.com.screenmatch.koimbra.screenmatch.model.DadosTemporada;
import br.com.screenmatch.koimbra.screenmatch.service.ConsumoApi;
import br.com.screenmatch.koimbra.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ADRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=91d7be43";

    public void exibeMenu() {
        System.out.print("Digite o nome da série: ");
        var nomeSerie = scanner.nextLine();
        var json = consumo.obterDados(ADRESS + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporada> listaTemporadas = new ArrayList<>();
		for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
			json = consumo.obterDados(ADRESS + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
			DadosTemporada temporada = conversor.obterDados(json, DadosTemporada.class);
			listaTemporadas.add(temporada);
		}
		listaTemporadas.forEach(System.out::println);

        listaTemporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

    }

}
