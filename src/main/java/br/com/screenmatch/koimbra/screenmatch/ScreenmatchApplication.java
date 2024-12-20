package br.com.screenmatch.koimbra.screenmatch;

import br.com.screenmatch.koimbra.screenmatch.model.DadosSerie;
import br.com.screenmatch.koimbra.screenmatch.service.ConsumoApi;
import br.com.screenmatch.koimbra.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String serie = "game+of+thrones";
		String temporada = "1";

		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t="+serie+"&apikey=91d7be43");
		System.out.println(json);
		var conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);


    }
}
