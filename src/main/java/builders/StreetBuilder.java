package builders;

import entities.*;
import restrictions.Animals;

public class StreetBuilder implements Builder{
   private Street street;
   private Home home;
   private Room room;
   private Person person;
   private Pet pet;

   public void establishStreet(String streetName) {
      this.street = new Street(streetName);
   }

   public void buildHome(int homeNumber) {
      this.home = new Home(homeNumber);
      this.street.addHome(home);
   }

   public void buildRoom(int roomNumber) {
      this.room = new Room(roomNumber);
      this.home.addRoom(room);
   }

   public void addResident(String personName, String personLastName, int moneyCount) {
      this.person = new Person(personName, personLastName, moneyCount);
      this.room.assignResident(person);
   }

   public void assignPetToResident(String petName, Animals animalType) {
      this.pet = new Pet(petName, animalType);
      this.person.assignPet(pet);
   }

   public Street getFinalStreet() {
      return street;
   }
}
