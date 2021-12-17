package JeuxMonopoly;

import java.util.Random;

public class Des {
    static Random rd = new Random();
    
    static int lancer(){
        return rd.nextInt(5) + 1;
    }
}
