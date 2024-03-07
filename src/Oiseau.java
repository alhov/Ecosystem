public class Oiseau extends AgentTransporte{
    public final int ident;
    private static int cpt = 0;
    public Oiseau(int x, int y, int energie){
        super(x, y, energie, "Oiseau");
        cpt++;
        ident = cpt;
    }
    public static int getCpt(){
        return cpt;
    }
    public void transporter(Ressource d, int x, int y){
        boolean b = super.seDeplacer(x, y);
        if (b){
            System.out.println(this.toString() + " transporte "  + d.toString() + " vers position (" + x + ", " + y + ")");
            d.initialisePosition();
            d.setPosition(x, y);
        }  
    }
    public Oiseau(Oiseau ois){
        this(ois.getX(), ois.getY(), ois.getEnergie());
    }
    public Oiseau clone(){
        return new Oiseau(x, y, energie);
    }
}
/*il va transporter la ressource dans x et y*/