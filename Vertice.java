import java.util.ArrayList;

public class Vertice {
    private String nome;
    private ArrayList<String> esportes;
    private ArrayList<Aresta> arestasEntrada;
    private ArrayList<Aresta> arestasSaida;

    public Vertice(String nome) {
        this.nome = nome;
        this.esportes = new ArrayList<String>();
        this.arestasEntrada = new ArrayList<Aresta>();
        this.arestasSaida = new ArrayList<Aresta>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<String> getEsportes() {
        return this.esportes;
    }

    public ArrayList<Aresta> getArestasEntrada() {
        return this.arestasEntrada;
    }

    public ArrayList<Aresta> getArestasSaida() {
        return this.arestasSaida;
    }

    public void adicionarEsporte(String esporte) {
        this.esportes.add(esporte);
    }

    public void adicionarArestaEntrada(Aresta aresta) {
        this.arestasEntrada.add(aresta);
    }

    public void adicionarArestaSaida(Aresta aresta) {
        this.arestasSaida.add(aresta);
    }

}
