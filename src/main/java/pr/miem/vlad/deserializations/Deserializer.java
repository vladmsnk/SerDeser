package pr.miem.vlad.deserializations;

import java.util.List;


public interface Deserializer<T>{
    T fromJsonToObj(String jsonString);
    List<T> fromJsonToList(String jsonString);
}
