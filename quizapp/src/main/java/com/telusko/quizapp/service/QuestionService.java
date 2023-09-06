package com.telusko.quizapp.service;

import com.telusko.quizapp.dao.QuestionDao;
import com.telusko.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestion() {

        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }


    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {

        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);


        }

    }

    public ResponseEntity<String> addQuestion(@RequestBody Question question) {

        questionDao.save(question);
        return new ResponseEntity<>("sucess", HttpStatus.ACCEPTED);

    }

//    public String deleteByid(int id ) {
//        return questionDao.deleteById(id);
//
//    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            questionDao.deleteById(id);
            return new ResponseEntity<>("deleted", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("ID not Found ", HttpStatus.NO_CONTENT);


        }
    }

    public void updateQuestion(Question question, int id) {
        question.setId(id);
        questionDao.save(question);
    }


//    public Question updateQuestion(int id, Question question) {
//       Question existing=questionDao.findById(id);
////               orElse(()->new EntityNotFoundException("product not fund"));
//       existing.setCategory(updateQuestion.getCategory());
//       return  questionDao.save(existing);
//
//
//    }


}
