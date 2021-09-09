package deserializations;

import java.util.List;


public interface Deserializable<T>{
    T FromJsonToObj(String jsonString);
    List<T> FromJsonToList(String jsonString);
}
