package org.vetcabinet.patients.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vetcabinet.patients.model.*;

import java.util.Date;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    boolean existsByShortNameAndSpecieAndBreedAndSexAndColorAndHairTypeAndBirthday(String shortName, Specie specie, Breed breed, Sex sex, Color color, HairType hairType, Date birthday);
}
