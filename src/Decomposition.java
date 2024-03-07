public class Decomposition extends Ressource{
    private static int cpt = 0;
    public final int ident;
    public Decomposition(int quantite, int x, int y){
        super("Decomposition", quantite);
        this.setPosition(x,y);
        cpt++;
        ident = cpt;
    }
    public static int getCpt(){
        return cpt;
    }
    public Decomposition(Decomposition dec){
        this(dec.getQuantite(),dec.getX(), dec.getY());
    }
    public Decomposition clone(){
        return new Decomposition(this.getQuantite(), this.getX(), this.getY());
    }
}

/*la classe decomposition herite de ressource aussi (un type de ressource) meme choses que champignons

pourquoi on peut acceder aux x y et setPosition alors qu ils sont pas attributs de la classe Decomposition et champignon*/