package Packet;/* Created by Vidush Namah on 2/15/2017 */

public enum Type {

    //DATA TYPE
    HELLO,
    GOODBYE,
    LINE,
    CIRCLE,
    SQUARE,
    FREE;

    @Override
    public String toString() {
        switch (this) {
            case HELLO: return "HELLO";
            case LINE: return "LINE";
            case CIRCLE: return "CIRCLE";
            case SQUARE: return "SQUARE";
            case FREE: return "FREE";

            default: throw new IllegalArgumentException("Invalid Wrapper Type");
        }
    }

}
