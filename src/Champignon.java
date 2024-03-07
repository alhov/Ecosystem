public class Champignon extends Ressource implements Modifiable{
    private static int cpt = 0;
    public final int ident;
    private int longueurVie;
    public Champignon(int quantite, int x, int y, int lv){
        super("Champignon", quantite);
        cpt++;
        this.setPosition(x,y); //on place le champignon dans le terrain
        ident = cpt;
        this.longueurVie = lv;
    }
    public static int getCpt(){
        return cpt;
    }
    public int getLongueurVie(){
        return longueurVie;
    }
    public boolean ifMort(Terrain ter){
        longueurVie = longueurVie - 1;
        if (longueurVie <= 0){
            System.out.println(this.toString() + " est mort");
            mourir(ter);
            return true;
        }
        return false;
    }
    public void mourir(Terrain ter){
        ter.videCase(super.getX(), super.getY());
    }
    public Champignon(Champignon cham){
        this(cham.getQuantite(),cham.getX(), cham.getY(), cham.getLongueurVie());
    }
    public Champignon clone(){
        return new Champignon(this.getQuantite(), this.getX(), this.getY(), this.getLongueurVie());
    }
}

/*la classe champignon herite de Ressourse : un champignon en peut se modifier mourir par exemple
il a trois attributs un identifient du chqmp un compteur qui gere l identifient sa longueur de vie 
un constructeur qui appelle le constructeur de la classe ressourse il prends en parametres le nom de la ressource et sa quantite
on icremente le compteur
on appele la fonction setPosition de la classe Ressource qui est public elle prends en parametres les cordonnes x et y
deux methodes : la methodes mourir qui initialise par 0 les cordonnees de la case ou il se trouve le champignon apres sa mort
la methode ifMort a chaque tour de boucle la longuer de la vie se di;inue de 1 quand la longueur de vie vaut le champignon va mourir logiquement

setposition est dans la classe ressource*/