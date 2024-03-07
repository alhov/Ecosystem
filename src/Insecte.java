public class Insecte extends AgentMange implements MangeDecomposition{
    public final int ident;
    private static int cpt = 0;
    public Insecte(int x, int y, int energie){
        super(x, y, energie, "Insecte");
        cpt++;
        ident = cpt;
    }
    public static int getCpt(){
        return cpt;
    }
    public void manger(Ressource d,Terrain ter){
        System.out.println(this.toString() + " mange " + d.toString());
        this.energie = this.energie + d.getQuantite();
        ter.videCase(d.getX(), d.getY());
    }
    public Insecte(Insecte ins){
        this(ins.getX(), ins.getY(), ins.getEnergie());
    }
    public Insecte clone(){
        return new Insecte(x, y, energie);
    }
}