package polynews.polytech.unice.fr.polynews.model;

/**
 * Created by Joel CANCELA VAZ on 22/03/2017.
 */

public enum ECategory {
    POLITICS,
    SOCIETY;


    public static ECategory intToECategory(int integer){
        switch(integer){
            case 1:
                return POLITICS;
            case 2:
                return SOCIETY;
        }
        return null;
    }

}
