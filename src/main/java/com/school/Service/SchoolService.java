package com.school.Service;

import com.school.Payload.SchoolDto;

import java.util.List;

public interface SchoolService {
    SchoolDto createSchool(SchoolDto schoolDto);



    void deleteSchoolById(long id);

    SchoolDto updateSchoolById(long id, SchoolDto schoolDto);


    List<SchoolDto> getAllSchool(int pageNo, int pageSize, String sortBy);
}
