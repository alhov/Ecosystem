public class Feuille extends Ressource{
    private static int cpt = 0;
    public final int ident;
    public Feuille(int quantite, int x, int y){
        super("Feuille", quantite);
        this.setPosition(x,y);
        cpt++;
        ident = cpt;
    }
    public static int getCpt(){
        return cpt;
    }
    public Feuille(Feuille feu){
        this(feu.getQuantite(),feu.getX(), feu.getY());
    }
    public Feuille clone(){
        return new Feuille(this.getQuantite(), this.getX(), this.getY());
    }
}

/*meme chose pour la Feuille est un type de ressourse donc il herites de la classe resource
dans les trois classes j ai une methode static pour recuperer le compteur*/