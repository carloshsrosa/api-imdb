package br.com.alura;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Locale;
import java.util.Scanner;

public class getRequest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		getApiKey();
		
		// cliente HTTP
		HttpClient client = HttpClient.newHttpClient();

		// criar a requisição
		HttpRequest request = HttpRequest.newBuilder().GET().timeout(Duration.ofSeconds(10))
				.uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + getApiKey())).build();

		// enviando uma solicitação
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		// imprimir o conteúdo recebido
		System.out.println(response.statusCode());
		System.out.println(response.body());
	}

	private static String getApiKey() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("secret-api-key.txt"), "UTF-8");

		String apiKey = null;
		while (scanner.hasNextLine()) {
			String linha = scanner.nextLine();

			Scanner linhaScanner = new Scanner(linha);
			linhaScanner.useLocale(Locale.US);
			linhaScanner.useDelimiter(",");

			apiKey = linhaScanner.next();

			linhaScanner.close();

		}
		
		scanner.close();
		return apiKey;
		
	}

}
