public class TerrainException extends Exception{
    public TerrainException(String message){
        super("Probleme de Terrain : " + message);
    }
}