package serializations;

import java.util.List;


public interface Serializable<T>{
    String objToJson(T obj);
    String ListOfObjToJson(List<T> listOfObj);
}
