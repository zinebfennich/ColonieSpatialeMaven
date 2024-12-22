package Colonie;

public class Ressource {
    private String nom;
    private boolean disponible;

    public Ressource(String nom) {
        this.nom = nom;
        this.disponible = true;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String toString() {
        return "la ressource est " + nom;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Ressource ressource = (Ressource) o;
        return nom.equals(ressource.nom);
    }

    @Override
    public int hashCode() { return nom.hashCode(); }
}