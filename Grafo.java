import java.util.ArrayList;

public class Grafo {
    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> arestas;

    public Grafo() {
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
    }

    public void adicionarVertice(String nome) {
        Vertice novoVertice = new Vertice(nome);
        this.vertices.add(novoVertice);
        System.out.println("Novo vértice criado: " + novoVertice.getNome());
    }

    public void adicionarEsporte(String nome, String esporte) {
        Vertice vertice = this.getVertice(nome);
        vertice.adicionarEsporte(esporte);
        System.out.println("Esporte " + esporte + " adicionado para usuário " + nome);
    }

    public void adicionarAresta(String nomeInicio, String nomeFim) {
        Vertice inicio = this.getVertice(nomeInicio);
        Vertice fim = this.getVertice(nomeFim);

        Aresta aresta = new Aresta(inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
        System.out.println("Aresta adicionada");
    }

    public Vertice getVertice(String nome) {
        Vertice vertice = null;
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getNome().equals(nome)) {
                vertice = this.vertices.get(i);
                break;
            }
        }

        return vertice;
    }

    public void sugerirConexoes(String nome) {
        Vertice usuario = this.getVertice(nome);
        ArrayList<String> esportesDoUsuario = usuario.getEsportes();

        ArrayList<Vertice> sugeridos = new ArrayList<Vertice>();

        for (int i = 0; i < this.vertices.size(); i++) {
            if (!this.vertices.get(i).equals(usuario)
                    && compartilhamEsportes(this.vertices.get(i), esportesDoUsuario)) {

                if (!conexaoExistente(usuario, this.vertices.get(i))) {
                    sugeridos.add(this.vertices.get(i));
                }
            }
        }

        if (sugeridos.isEmpty()) {
            System.out.println("Nenhuma sugestão de amizade encontrada.");
        } else {
            System.out.println("Sugestões de amizade para " + usuario.getNome() + ":");
            for (int i = 0; i < sugeridos.size(); i++) {
                System.out.println(sugeridos.get(i).getNome());
            }
        }
    }

    public void desfazerConexao(String nomeInicio, String nomeFim) {
        Vertice inicio = this.getVertice(nomeInicio);
        Vertice fim = this.getVertice(nomeFim);
    
        if (inicio == null || fim == null) {
            System.out.println("Um dos vértices não foiencontrado.");
        }
    
        Aresta arestaRemover = null;
    
        for (int i = 0; i <  inicio.getArestasSaida().size(); i++) {
            if (inicio.getArestasSaida().get(i).getFim().equals(fim)) {
                arestaRemover = inicio.getArestasSaida().get(i);
                break;
            }
        }
    
        if (arestaRemover != null) {
            inicio.getArestasSaida().remove(arestaRemover);
    
            fim.getArestasEntrada().remove(arestaRemover);
            this.arestas.remove(arestaRemover);
    
            System.out.println("Conexão entre " + nomeInicio + " e " + nomeFim + " foi desfeita.");
        } else {
            System.out.println("Não há conexão entre " + nomeInicio + " e " + nomeFim + ".");
        }
    }

    private boolean compartilhamEsportes(Vertice vertice, ArrayList<String> esportesDoVerticeAtual) {
        for (int i = 0; i < vertice.getEsportes().size(); i++) {
            if (esportesDoVerticeAtual.contains(vertice.getEsportes().get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean conexaoExistente(Vertice usuario, Vertice amigo) {
        for (int i = 0; i < usuario.getArestasSaida().size(); i++) {
            if (usuario.getArestasSaida().get(i).getFim().equals(amigo)) {
                return true;
            }
        }

        for (int i = 0; i < amigo.getArestasSaida().size(); i++) {
            if (amigo.getArestasSaida().get(i).getFim().equals(usuario)) {
                return true;
            }
        }

        return false;
    }

    public void getArestasLigadasAoVertice(String nome) {
        Vertice usuario = this.getVertice(nome);
        ArrayList<Aresta> conexoes = new ArrayList<Aresta>();

        System.out.println("Arestas de saída de " + usuario.getNome() + ":");
        for (Aresta aresta : usuario.getArestasSaida()) {
            System.out.println("Ligado a " + aresta.getFim().getNome());
        }

        System.out.println("Arestas de entrada em " + usuario.getNome() + ":");
        for (Aresta aresta : usuario.getArestasEntrada()) {
            System.out.println("Vindo de " + aresta.getInicio().getNome());
        }
    }

    public void listarEsportes(String nomeVertice) {
        Vertice vertice = this.getVertice(nomeVertice); 
        if (vertice != null) {
            for (int i = 0; i < vertice.getEsportes().size(); i++) {
                System.out.println(vertice.getEsportes().get(i)+"\n");
            }
        } 
    }

    public void visualizarGrafo() {
        System.out.println("=== Vértices existentes ===");
        for (int i = 0; i < this.vertices.size(); i++) {

            for (int j = 0; j < this.vertices.get(i).getEsportes().size(); j++) {
                System.out.println(this.vertices.get(i).getNome() + ", Esportes: " +
                        this.vertices.get(i).getEsportes().get(j) + ";");

            }
        }
        System.out.println("\n");
        System.out.println("=== Arestas existentes ===");
        for (int i = 0; i < this.arestas.size(); i++) {
            System.out.println("Aresta ligando "
                    + this.arestas.get(i).getInicio().getNome() + " a " + this.arestas.get(i).getFim().getNome());
        }
        System.out.println("\n");
    }
}