package com.example.surveysserver;

import com.example.surveysserver.data.Survey;
import com.example.surveysserver.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/surveys")
public class SurveyController {

    @Autowired
    SurveyRepository surveyRepository;

    @GetMapping("/surveys")
    public List<Survey> getAll(){
        return surveyRepository.getAll();
    }



//    @GetMapping(/{rate})
//    public List<Survey> getSurveyBySurname(@PathVariable String surname){
//        return surveyRepository.getSurveysBySurname(surname);
//    }

    @GetMapping("/surveys/{rate}")
    public List<Survey> getSurveysByRate(@PathVariable("rate") Integer rate){
        return surveyRepository.getSurveysByRate(rate);
    }

    @PostMapping("/surveys")
    public int add(@RequestBody List<Survey> surveys){
            return surveyRepository.save(surveys);
    }

    @PutMapping("/surveys/{id}")
    public int update(@PathVariable("id") int id,@RequestBody Survey surveyToUpdate){
        try{
            Survey survey = surveyRepository.getSurveysById(id);
        }catch(EmptyResultDataAccessException e){
            return -1;
        }

        return surveyRepository.update(surveyToUpdate);


    }


}
