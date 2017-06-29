package polynews.polytech.unice.fr.polynews.model;

/**
 * Created by Joel CANCELA VAZ on 22/03/2017.
 */

public enum EMediaType {
    PICTURE,
    VIDEO;

    public static EMediaType intToEMediaType(int integer){
        switch(integer){
            case 0:
                return PICTURE;
            case 1:
                return VIDEO;
        }
        return null;
    }
}
