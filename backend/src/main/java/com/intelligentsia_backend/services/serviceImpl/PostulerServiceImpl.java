package com.intelligentsia_backend.services.serviceImpl;

import com.intelligentsia_backend.entity.JobOffer;
import com.intelligentsia_backend.entity.Postuler;
import com.intelligentsia_backend.entity.Society;
import com.intelligentsia_backend.entity.Student;
import com.intelligentsia_backend.model.PostulerModel;
import com.intelligentsia_backend.repository.PostulerRepository;
import com.intelligentsia_backend.services.service.JobOfferService;
import com.intelligentsia_backend.services.service.PostulerService;
import com.intelligentsia_backend.services.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostulerServiceImpl implements PostulerService {
    @Autowired
    private PostulerRepository postulerRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private JobOfferService jobOfferService;
    @Override
    public Postuler save(PostulerModel postulerModel) {

        Postuler postuler=new Postuler();
        postuler.setStudent(studentService.findById(postulerModel.getStudentId()));
        postuler.setJobOffer(jobOfferService.findById(postulerModel.getJobOfferId()));
        postuler.setDate(new Date());
        postuler.setAccept(false);
        postuler.setComment(postulerModel.getComment());
        return postulerRepository.save(postuler);
    }

    @Override
    public Postuler recupPostuler(String emailStudent) {
        Student student=studentService.findByEmail(emailStudent);
        return postulerRepository.findByStudent_id(student.getId());
    }

    @Override
    public Iterable<Postuler> recupAllPostulerSociety(Long id ) {
        return postulerRepository.findAllBySociety_id(id);
    }
}
