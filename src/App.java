import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //fazer conexção com o HTTP  dos Top 10 filmes da imDb
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //extrair só os dados que interessa (título, pôster e classificação)
        var parser = new JsonPaser(); 
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //extrair e manipular os dados como quiser
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[1mTitulo:\u001b[m " + filme.get("title"));
            System.out.println("\u001b[3mURL da imagem:\u001b[m" + filme.get("image"));
            System.out.println("\u001b[37m \u001b[36mA Classificação é:\u001b[m" + filme.get("imDbRating"));
            
           
        }
    }
}
