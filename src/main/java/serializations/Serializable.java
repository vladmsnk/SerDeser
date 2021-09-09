package serializations;

import java.util.ArrayList;



public interface Serializable<T>{
    String objToJson(T obj);
    String ListOfObjToJson(ArrayList<T> listOfObj);
}
