package deserializations;

import java.util.List;


public interface Deserializer<T>{
    T FromJsonToObj(String jsonString);
    List<T> FromJsonToList(String jsonString);
}
