package com.school.Controller;

import com.school.Entity.School;
import com.school.Payload.SchoolDto;
import com.school.Service.SchoolService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v4/School")
public class SchoolController {

    private SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<SchoolDto> addSchool(@RequestBody SchoolDto schoolDto){

        SchoolDto schDto = schoolService.createSchool(schoolDto);
        return new ResponseEntity<>(schDto, HttpStatus.CREATED);


    }
    @DeleteMapping
    public ResponseEntity<String> deleteSchoolById(@RequestParam long id){

        schoolService.deleteSchoolById(id);
        return new ResponseEntity<>("School deleted successfully", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SchoolDto> updateSchoolById(@RequestParam long id,
                                                      @RequestBody SchoolDto schoolDto){

        SchoolDto dtos = schoolService.updateSchoolById(id, schoolDto);
        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<SchoolDto>> getAllSchool(
            @RequestParam(name="pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name="pageSize", defaultValue = "3", required = false) int pageSize,
            @RequestParam(name="sortBy", defaultValue = "name", required = false) String sortBy

    ){
        List<SchoolDto> allSchool = schoolService.getAllSchool(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(allSchool, HttpStatus.OK);
    }
}
