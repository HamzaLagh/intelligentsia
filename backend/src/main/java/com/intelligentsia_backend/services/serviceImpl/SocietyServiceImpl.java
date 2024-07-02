package com.intelligentsia_backend.services.serviceImpl;

import com.intelligentsia_backend.entity.Role;
import com.intelligentsia_backend.entity.Society;
import com.intelligentsia_backend.model.SocietyModel;
import com.intelligentsia_backend.repository.RoleRepository;
import com.intelligentsia_backend.repository.SocietyRepository;
import com.intelligentsia_backend.services.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SocietyServiceImpl implements SocietyService {
    @Autowired
    private SocietyRepository societyRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Society Registration(SocietyModel societyModel) {
        Role role= new Role();
        role.setName("SOCIETY");
        role=roleRepository.save(role);
        Society society=new Society();
        society.setName(societyModel.getName());
        society.setSiren(societyModel.getSiren());
        society.setTel(societyModel.getTel());
        society.setAddress(societyModel.getAddress());
        society.setSiret(societyModel.getSiret());
        society.getRole().add(role);
        society.setSpecialization(societyModel.getSpecialization());
        society.setEmail(societyModel.getEmail());
        society.setPassword(passwordEncoder.encode(societyModel.getPassword()));
        return societyRepository.save(society);
    }

    @Override
    public Society getByEmail(String email) {
        return societyRepository.findByEmail(email);
    }

    @Override
    public void enableSociety(Society society) {
        society.setEnabled(true);
    }


}
