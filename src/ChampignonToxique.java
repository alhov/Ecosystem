public class ChampignonToxique extends Champignon implements Modifiable, Toxique{
    public ChampignonToxique(int quantite, int x, int y, int lv){
       super(quantite, x, y, lv);
    }
    public ChampignonToxique(ChampignonToxique tox){
        this(tox.getQuantite(), tox.getX(), tox.getY(), tox.getLongueurVie());
    }
    public ChampignonToxique clone(){
        return new ChampignonToxique(this.getQuantite(), this.getX(), this.getY(), this.getLongueurVie());
    }
    public String toString(){
        return "Toxique " + super.toString();
    }
}

/*cette classe est heritee de la classe champignon implement deux interfaces modifiable qu elle elle peut mourir 
toxique car elle est toxique*/