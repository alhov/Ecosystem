public abstract class AgentMange extends Agent{
    public AgentMange(int x, int y, int energie, String type){
        super(x, y, energie, type);
    }
    public abstract void manger(Ressource r, Terrain ter);
}
/*Agents Mange herite de la classe agents 
c'est les insectes qui mange les animaux qui mangents les champignons et les feuilles il a une methode manger qui prends une ressource en parametres

l agent qui mange il va verifier d abord si ya une ressource dans sa cas eet va la manger*/