package com.springframework.sfgdi.pets;

public class PetServiceFactory {

    public PetService getPetService(String petType) {
        return petType.equals("cat") ? new CatPetService() : new DogPetService();
    }

}
