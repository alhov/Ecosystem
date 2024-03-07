public abstract class AgentTransporte extends Agent{
    public AgentTransporte(int x, int y, int energie, String type){
        super(x, y, energie, type);
    }
    public boolean seDeplacer(int xnew, int ynew){
        if(tab[xnew][ynew]==true){
            tab[x][y] = true;
            tab[xnew][ynew] = false;
            x = xnew;
            y = ynew;
            energie = energie - 1;
            energie = energie + 1; //pour les agents qui se deplacent et ils mangents on garde l enrgie constante
            return true;
        }
        return false;
    }
    public abstract void transporter(Ressource r, int x, int y);
}

/*cette classe herite de la classe agents 
c'est les oiseaux qui transportent les champignons et les feuilles 
il est ou le tableau tab?
une methode pour se deplacer

une methode pour se transporter */