import java.util.ArrayList;
import java.util.Scanner;
public class Simulation{
    private Terrain ter;
    private ArrayList<Agent> ag;
    public Simulation(int n, int m, int largeur, int hauteur) {
        Agent agtest = new Animal(-1, -1, -1); //pour initialiser le tableau des boolean qu on a dans la classe Agents
        boolean bex = false;
        while (!bex){
            try{
                if(largeur >= 0 && largeur <= Terrain.NBLIGNESMAX && hauteur >=0 && hauteur <= Terrain.NBCOLONNESMAX){
                    bex = true;
                } else {
                    throw new TerrainException("dimensions sont invalides");
                }
            } catch(TerrainException e){
                System.out.println(e.getMessage());
                System.out.println("Faut saisir largeur et hauteur de terrain valides");
                Scanner in = new Scanner(System.in);
                System.out.print("Input largeur : ");
                largeur = in.nextInt();
                System.out.print("Input hauteur : ");
                hauteur = in.nextInt();
            }
        }
        ter = new Terrain(largeur, hauteur);//on cree le terrain
        ag = new ArrayList<Agent> (); //liste des agents 
        for(int i =0; i<m ; i++){
            double rand = Math.random(); //probabilite moitie de Feuilles moities des champignons :moitie toxique et moitie non
            if(rand<0.25){
                int x = -1;
                int y = -1;
                while(!(ter.sontValides(x, y) && ter.caseEstVide(x,y))){
                    x = (int)(Math.random() * ter.nbLignes);
                    y = (int)(Math.random() * ter.nbColonnes);
                }
                Champignon cnew = new Champignon((int)(Math.random()*10)+1, x, y, (int)(Math.random()*10)+1);
                ter.setCase(x,y,cnew);
            } else if (rand < 0.5){
                int x = -1;
                int y = -1;
                while(!(ter.sontValides(x, y) && ter.caseEstVide(x,y))){
                    x = (int)(Math.random() * ter.nbLignes);
                    y = (int)(Math.random() * ter.nbColonnes);
                }
                ChampignonToxique cnew = new ChampignonToxique((int)(Math.random()*10)+1, x, y, (int)(Math.random()*10)+1);
                ter.setCase(x,y,cnew);
            } else {
                int x = -1;
                int y = -1;
                while(!(ter.sontValides(x, y) && ter.caseEstVide(x,y))){
                    x = (int)(Math.random() * ter.nbLignes);
                    y = (int)(Math.random() * ter.nbColonnes);
                }
                Feuille cnew = new Feuille((int)(Math.random()*10)+1, x, y);
                ter.setCase(x,y,cnew);
            }
        }
        for(int i =0; i<n; i++){
            double rand = Math.random();
            if(rand < 0.25){
                boolean b = false;
                int x = -1;
                int y = -1;
                while(!b){
                    b = true;
                    x = (int)(Math.random() * ter.nbLignes);
                    y = (int)(Math.random() * ter.nbColonnes); //les booleans pour verifier que les cases du mon tableau sont pas Occupees
                    if(Agent.tab!=null){
                    if(Agent.tab[x]!=null){
                        if (Agent.tab[x][y]==false){
                            b= false;
                        }
                    }
                    }
                }
                ag.add(new Insecte(x,y,(int)(Math.random()*10)+1));
            } else if (rand < 0.5){
                boolean b = false;
                int x = -1;
                int y = -1;
                while(!b){
                    b = true;
                    x = (int)(Math.random() * ter.nbLignes);
                    y = (int)(Math.random() * ter.nbColonnes);
                    if(Agent.tab!=null){
                    if(Agent.tab[x]!=null){
                        if (Agent.tab[x][y]==false){
                            b= false;
                        }
                    }
                    }
                }
                ag.add(new Animal(x,y,(int)(Math.random()*10)+1));
            } else {
                boolean b = false;
                int x = -1;
                int y = -1;
                while(!b){
                    b = true;
                    x = (int)(Math.random() * ter.nbLignes);
                    y = (int)(Math.random() * ter.nbColonnes);
                    if(Agent.tab!=null){
                    if(Agent.tab[x]!=null){
                        if (Agent.tab[x][y]==false){
                            b= false;
                        }
                    }
                    }
                }
                ag.add(new Oiseau(x,y,(int)(Math.random()*10)+1));
            }
        }
    }
    public void simulate(int iter){
        System.out.println("ETAT INITIAL:");
        ter.affiche(3);
        ArrayList<Ressource> liste = ter.lesRessources();
        System.out.println("Ressources sur le terrain:");
		for (Ressource r : liste) {
			System.out.println(r);
		}
        System.out.println("Nombre des cases de terrain avec ressources = " + liste.size());
        System.out.println("Agents:");
        for (Agent agt : ag) {
			System.out.println(agt);
		}
        System.out.println("Nombre des agents = " + ag.size());
        for(int i =0; i< iter; i++){
            System.out.println("ITERATION " + (i+1) + ":");
            for(Agent agt : ag){
                int x = agt.getX();
                int y = agt.getY();
                if(agt instanceof AgentTransporte && !ter.caseEstVide(x,y)){
                     AgentTransporte agm = (AgentTransporte) agt;
                     int xd = -1;
                     int yd = -1;
                     while(!(ter.sontValides(xd, yd) && ter.caseEstVide(xd,yd))){
                        xd = (int)(Math.random() * ter.nbLignes);
                        yd = (int)(Math.random() * ter.nbColonnes);
                     }
                     agm.transporter(ter.getCase(x,y),xd, yd);
                } else if (agt instanceof AgentTransporte){
                     AgentTransporte agm = (AgentTransporte) agt;
                     agm.seDeplacer((int)(Math.random() * ter.nbLignes), (int)(Math.random() * ter.nbColonnes));
                } else {
                     agt.seDeplacer((int)(Math.random() * ter.nbLignes), (int)(Math.random() * ter.nbColonnes));
                }
                int x2 = agt.getX();
                int y2 = agt.getY();
                if(agt instanceof MangeModifiable){
                    if(!ter.caseEstVide(x2,y2)){
                        Ressource r = ter.getCase(x2, y2);
                        if (!(r instanceof Decomposition)){
                            AgentMange agm = (AgentMange) agt;
                            agm.manger(r, ter);
                        }
                    }
                }
                if(agt instanceof MangeDecomposition){
                    if(!ter.caseEstVide(x2,y2)){
                        Ressource r = ter.getCase(x2, y2);
                        if (!(r instanceof Modifiable)){
                            AgentMange agm = (AgentMange) agt;
                            agm.manger(r, ter);
                        }
                    }
                }
            }
            for(int k=0; k<ag.size();k++){
                Agent at = ag.get(k);
                if (at.getEnergie()<=0){
                    System.out.println(at.toString() + " est mort");
                    ag.remove(k);
                }
            }
            ArrayList<Ressource> res = ter.lesRessources();
            for (Ressource r : res){
                if (r instanceof Modifiable && ter.sontValides(r.getX(), r.getY()) && !ter.caseEstVide(r.getX(), r.getY())){
                    int x = r.getX();
                    int y = r.getY();
                    Modifiable md = (Modifiable) r;
                    if (md.ifMort(ter)){
                        Decomposition dnew = new Decomposition((int)(Math.random()*10)+1, x, y);
                        ter.setCase(x, y, dnew);
                    }
                }
            }
            if(i%10==0 && i>0){
                for(int j =0; j<10 ; j++){
                    double rand = Math.random(); //probabilite moitie de Feuilles moities des champignons :moitie toxique et moitie non
                    if(rand<0.25){
                        int x = -1;
                        int y = -1;
                        while(!(ter.sontValides(x, y) && ter.caseEstVide(x,y))){
                            x = (int)(Math.random() * ter.nbLignes);
                            y = (int)(Math.random() * ter.nbColonnes);
                        }
                        Champignon cnew = new Champignon((int)(Math.random()*10)+1, x, y, (int)(Math.random()*10)+1);
                        ter.setCase(x,y,cnew);
                    } else if (rand < 0.5){
                        int x = -1;
                        int y = -1;
                        while(!(ter.sontValides(x, y) && ter.caseEstVide(x,y))){
                            x = (int)(Math.random() * ter.nbLignes);
                            y = (int)(Math.random() * ter.nbColonnes);
                        }
                        ChampignonToxique cnew = new ChampignonToxique((int)(Math.random()*10)+1, x, y, (int)(Math.random()*10)+1);
                        ter.setCase(x,y,cnew);
                    } else {
                        int x = -1;
                        int y = -1;
                        while(!(ter.sontValides(x, y) && ter.caseEstVide(x,y))){
                            x = (int)(Math.random() * ter.nbLignes);
                            y = (int)(Math.random() * ter.nbColonnes);
                        }
                        Feuille cnew = new Feuille((int)(Math.random()*10)+1, x, y);
                        ter.setCase(x,y,cnew);
                    }
                }
            }
            ter.affiche(3);
            ArrayList<Ressource> lte = ter.lesRessources();
            System.out.println("Ressources sur le terrain:");
		    for (Ressource r : lte) {
			    System.out.println(r);
		    }
            System.out.println("Nombre des cases de terrain avec ressources = " + lte.size());
            System.out.println("Agents:");
            for (Agent agt : ag) {
			    System.out.println(agt);
		    }
             System.out.println("Nombre des agents = " + ag.size());
        }
    }
    
}