package serializations;

import java.util.List;


public interface Srializable<T>{
    String objToJson(T obj);
    String ListOfObjToJson(List<T> listOfObj);
}
