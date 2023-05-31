package com.moroTechLibrary.library.model.mapper;

import com.moroTechLibrary.library.dao.person.PersonDAO;
import com.moroTechLibrary.library.dto.PersonDTO;
import com.moroTechLibrary.library.model.Person;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Component
public class PersonMapper {
    private final ModelMapper modelMapper;

    public PersonDTO convertToDto(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    public Person convertToEntity(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    public PersonDAO convertToDAO(Person person) {
        return modelMapper.map(person, PersonDAO.class);
    }

    public List<PersonDTO> convertDAOToListDto(List<PersonDAO> personList) {
        return personList.stream()
                .map(this::convertToDtoFromDAO)
                .collect(Collectors.toList());
    }

    public List<Person> convertToListEntity(List<PersonDAO> personDAOList) {
        return personDAOList.stream()
                .map(this::convertDAOToPerson)
                .collect(Collectors.toList());
    }

    public Person convertDAOToPerson(PersonDAO personDAO) {
        return modelMapper.map(personDAO, Person.class);
    }

    public PersonDAO convertToPersonDAO(Person person) {
        return modelMapper.map(person, PersonDAO.class);
    }

    public List<PersonDTO> convertToListDtoFromDAO(List<PersonDAO> personDAOList) {
        return personDAOList.stream()
                .map(this::convertToDtoFromDAO)
                .collect(Collectors.toList());
    }

    public PersonDTO convertToDtoFromDAO(PersonDAO personDAO) {
        return modelMapper.map(personDAO, PersonDTO.class);
    }

    public List<PersonDAO> convertToListDAO(List<Person> personList) {
        return personList.stream()
                .map(this::convertToDAO)
                .collect(Collectors.toList());
    }
}
