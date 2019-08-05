package Helpers;
/* Created by Vidush Namah on 2/15/2017 */

import java.io.*;

public class Serializer {

    public static byte[] Serialize(Object O) throws IOException {
        try(ByteArrayOutputStream B = new ByteArrayOutputStream()){
            try(ObjectOutputStream OUT = new ObjectOutputStream(B)){
                OUT.writeObject(O);
            }
            return B.toByteArray();
        }
    }

    public static Object Deserialize(byte[] Bytes) throws IOException, ClassNotFoundException {
        try(ByteArrayInputStream B = new ByteArrayInputStream(Bytes)){
            try(ObjectInputStream O = new ObjectInputStream(B)){
                return O.readObject();
            }
        }
    }

}