package com.school.Service;

import com.school.Entity.School;
import com.school.Payload.SchoolDto;
import com.school.Repository.SchoolRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService{

    private SchoolRepository schoolRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public SchoolDto createSchool(SchoolDto schoolDto) {
       School school = mapToEntity(schoolDto);
        School savedEntity = schoolRepository.save(school);
        SchoolDto dto = mapToDto(savedEntity);
        return dto;
    }

    @Override
    public void deleteSchoolById(long id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public SchoolDto updateSchoolById(long id, SchoolDto schoolDto) {
        Optional<School> opSc = schoolRepository.findById(id);
        School school = opSc.get();

        school.setName(schoolDto.getName());
        school.setEmail(schoolDto.getEmail());
        school.setMobile(school.getMobile());
        School savedEntity = schoolRepository.save(school);
        SchoolDto dto = mapToDto(savedEntity);
        return dto;

    }

    @Override
    public List<SchoolDto> getAllSchool(int pageNo, int pageSize, String sortBy) {
//        List<School> all = schoolRepository.findAll();
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<School> all = schoolRepository.findAll(pageable);
        List<School> schdto = all.getContent();
        List<SchoolDto> schoolDtos = schdto.stream().map(s -> mapToDto(s)).collect(Collectors.toList());
        return schoolDtos;
    }




    School mapToEntity(SchoolDto dto){
        School school = new School();
        school.setName(dto.getName());
        school.setEmail(dto.getEmail());
        school.setMobile(dto.getMobile());
        return school;

}

SchoolDto mapToDto (School school){
        SchoolDto dto = new SchoolDto();
        dto.setId(school.getId());
        dto.setName(school.getName());
        dto.setEmail(school.getEmail());
        dto.setMobile(school.getMobile());
        return dto;

}

}
