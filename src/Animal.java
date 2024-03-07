public class Animal extends AgentMange implements MangeModifiable{
    public final int ident;
    private static int cpt = 0;
    public Animal(int x, int y, int energie){
        super(x, y, energie, "Animal");
        cpt++;
        ident = cpt;
    }
    public static int getCpt(){
        return cpt;
    }
    public void manger(Ressource c, Terrain ter){
        System.out.println(this.toString() + " mange " + c.toString());
        if(c instanceof Toxique){
            this.energie = this.energie - c.getQuantite();
            if (this.energie < 0){
                this.energie = 0;
            }
        } else{
            this.energie = this.energie + c.getQuantite();
        }
        ter.videCase(c.getX(), c.getY());//lanimal quand il mange la ressource on doit vider la case ou y a la ressource
    }
    public Animal(Animal ani){
        this(ani.getX(), ani.getY(), ani.getEnergie());
    }
    public Animal clone(){
        return new Animal(x, y, energie);
    }
}
/*la classe Animal herite de la classe agentsMange et implement l interface MangeModifiable donc l animal mange que les champignons
d apres ce que j ai compris on prends l enrgie des ressources c ets leurs quantite 
et puis c est l animal mange un chmpignon toxique son energie va se diminuer */