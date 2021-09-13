package builders;

import restrictions.Animals;

public interface Builder {
    void establishStreet(String streetName);
    void buildHome(int homeNumber);
    void buildRoom(int roomNumber);
    void addResident(String personName, String personLastName, int moneyCount);
    void assignPetToResident(String petName, Animals animalType);
}
