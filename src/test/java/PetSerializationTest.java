import entities.Pet;
import restrictions.Animals;
import serializations.PetSerializer;

public class PetSerializationTest {
    private boolean implement(Pet pet) {
        try {
            PetSerializer petSerializer = new PetSerializer();
            String jsonStringOfPet = petSerializer.objToJson(pet);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public void test() {
        try {
            Pet pet = new Pet("bob", Animals.CAT);
            if (implement(pet)) {
                System.out.println("Pet Serialization completed!");
            }
        }
        catch (Exception e) {
            System.out.println("Wrong initialization!");
        }
    }

}
