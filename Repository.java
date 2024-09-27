import java.util.ArrayList;

public class Repository {
    ArrayList<Vertice> usuarios = new ArrayList<Vertice>();

    public Repository() {
        this.usuarios = new ArrayList<Vertice>();
        addUsuarios();
    }

    public void addUsuarios() {
       
        this.usuarios.add(new Vertice("Admin"));
        this.usuarios.add(new Vertice("Nina"));
        this.usuarios.add(new Vertice("Nancy"));
        this.usuarios.add(new Vertice("Ned"));
        this.usuarios.add(new Vertice("Nico"));
        this.usuarios.add(new Vertice("Nilce"));
    }

    public ArrayList<Vertice> getUsuarios() {
        return this.usuarios;
    }
}
