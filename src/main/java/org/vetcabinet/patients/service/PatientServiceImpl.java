package org.vetcabinet.patients.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vetcabinet.exception.AlreadyExistsException;
import org.vetcabinet.patients.dto.PatientDto;
import org.vetcabinet.patients.dto.PatientUpdateDto;
import org.vetcabinet.patients.mapper.*;
import org.vetcabinet.patients.model.*;
import org.vetcabinet.patients.repository.*;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientsService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final BreedRepository breedRepository;
    private final BreedMapper breedMapper;
    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;
    private final HairTypeRepository hairTypeRepository;
    private final HairTypeMapper hairTypeMapper;
    private final SexRepository sexRepository;
    private final SexMapper sexMapper;
    private final SpecieRepository specieRepository;
    private final SpecieMapper specieMapper;

    @Override
    public PatientDto create(PatientDto patient) {
        Patient patientData = patientMapper.toPatient(patient);
        System.out.println(patientData);

        if (patientRepository.existsByShortNameAndSpecieAndBreedAndSexAndColorAndHairTypeAndBirthday(patientData.getShortName(), patientData.getSpecie(), patientData.getBreed(), patientData.getSex(), patientData.getColor(), patientData.getHairType(), patientData.getBirthday())) {
            throw new AlreadyExistsException("Данные о животном уже есть в системе");
        }
        // Проверяем, существуют ли связанные сущности
        Breed breed = breedRepository.save(patientData.getBreed()) ;
        Color color = colorRepository.save(patientData.getColor());
        HairType hairType = hairTypeRepository.save(patientData.getHairType());
        Sex sex = sexRepository.save(patientData.getSex());
        Specie specie =specieRepository.save(patientData.getSpecie());

        // Устанавливаем связанные сущности в объект Patient
        patientData.setBreed(breed);
        patientData.setColor(color);
        patientData.setHairType(hairType);
        patientData.setSex(sex);
        patientData.setSpecie(specie);

        return patientMapper.toPatientDto(patientRepository.save(patientData));
    }

    @Override
    public PatientUpdateDto update(PatientUpdateDto patient, UUID id) {
        if (!patientRepository.existsById(id)) {
            throw new AlreadyExistsException("Данные о животном не найдены в системе");
        }
        Patient patientData = patientMapper.toPatient(patient);
        return patientMapper.toPatientUpdateDto(patientRepository.save(patientData));
    }
}
