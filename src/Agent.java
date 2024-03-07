public abstract class Agent{
    public static boolean[][] tab;
    protected int x,y;
    protected int energie;
    public final int ident;
    public String type;
    private static int cpt = 0;
    public Agent(int x, int y, int energie, String type){
        if(cpt == 0){
            tab = new boolean[Terrain.NBLIGNESMAX][];
            for(int i = 0; i < Terrain.NBLIGNESMAX; i++){
                tab[i] = new boolean[Terrain.NBCOLONNESMAX]; //on cree un tableau de boolean  a chque fois qu une case du tableau est occupe par un agent on mets false et quand agents se deplace c est une case liberee on mets true
            }
            for(int i =0 ; i < Terrain.NBLIGNESMAX; i++){
                for(int j =0; j< Terrain.NBCOLONNESMAX; j++){
                    tab[i][j] = true;
                }
            }
        }
        this.x = x;
        this.y = y;
        if(x >=0 && y >=0 && x < Terrain.NBLIGNESMAX && y < Terrain.NBCOLONNESMAX){
            tab[x][y] = false;
        }
        this.energie = energie;
        cpt++;
        this.ident = cpt;
        this.type = type;
    }
    public double distance(int x, int y){
        return Math.sqrt((x-this.x)*(x-this.x) + (y-this.y)*(y-this.y));
    }
    public boolean seDeplacer(int xnew, int ynew){
        if(tab[xnew][ynew]==true){
            System.out.println(toString() + " deplace vers position (" + xnew + ", " + ynew + ")");
            tab[x][y] = true;
            tab[xnew][ynew] = false;
            x = xnew;
            y = ynew;
            energie = energie - 1; //quand l animal se deplace il perd de l energie 
            return true;
        }
        return false;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getEnergie(){
        return energie;
    }
    public static int getCpt(){
        return cpt;
    }
    public String getType(){
        return type;
    }
    public String toString(){
        return this.type + " " + this.ident + " de position (" + this.x +", " + this.y + ") avec energie " + this.energie;
    }


}