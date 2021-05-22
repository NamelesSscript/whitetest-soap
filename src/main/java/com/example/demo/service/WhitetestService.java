package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Service;

import de.tekup.soap.models.whitetest.Exam;
import de.tekup.soap.models.whitetest.ObjectFactory;
import de.tekup.soap.models.whitetest.Student;
import de.tekup.soap.models.whitetest.StudentRequest;
import de.tekup.soap.models.whitetest.WhiteTestResponse;

@Service
public class WhitetestService {


	public WhiteTestResponse checkWhiteTestStatus(StudentRequest studentRequest) throws DatatypeConfigurationException {
        Boolean testStudent=false;
        Boolean testExam=false;
        int id=0;
        int code=0;
        List<Student> students = new Student().getAllStudents() ;
        List<Exam> exams = new Exam().getAllExams() ;
 
        
        for (int i=0;i<students.size();i++) {
            if(students.get(i).getId()==studentRequest.getStudentId()) {
            	testStudent=true;
                id=i;
                break;
            }
        }
      
        for (int i=0;i<exams.size();i++) {
            if(exams.get(i).getCode().equalsIgnoreCase(studentRequest.getExamCode())) {
                testExam=true;
                code=i;
                break;
            }
        }
 
        WhiteTestResponse whiteTestResponse = new ObjectFactory().createWhiteTestResponse();
        List<String> mismatchs =whiteTestResponse.getCriteriaMismatch();
        if(studentRequest.getStudentId()==0 || studentRequest.getStudentId()<0 || !(testStudent)) {
            mismatchs.add("Student Id is not found !");
        }
        if(studentRequest.getExamCode().isEmpty()) {
            mismatchs.add("Exam code is empty! , please enter a valid code");
        }
        if(!(testExam))
        {
            mismatchs.add("Exam code is wrong ! , please enter a valid code");
        }
        if(mismatchs.isEmpty()) {
            Student student = new Student();
            student.setId(studentRequest.getStudentId());
            student.setName(students.get(id).getName());
            student.setAddress((students.get(id).getAddress()));
            Exam exam = new Exam();
            exam.setCode(studentRequest.getExamCode());
            exam.setName(exams.get(code).getName());
            whiteTestResponse.setStudent(student);
            whiteTestResponse.setExam(exam);
            LocalDateTime localDate = LocalDateTime.now().plusDays(3);
            XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
            whiteTestResponse.setDate(xmlGregorianCalendar);
            whiteTestResponse.setIsValid(true);

        }else {
            whiteTestResponse.setIsValid(false);
        }
        return whiteTestResponse;
    }
}