package pr.miem.vlad.serializations;

import java.util.ArrayList;

public interface Serializer<T>{
    String objToJson(T obj);
    String ListOfObjToJson(ArrayList<T> listOfObj);
}
