package com.intelligentsia_backend.services.service;

import com.intelligentsia_backend.entity.Society;
import com.intelligentsia_backend.model.SocietyModel;

public interface SocietyService {
    Society Registration(SocietyModel societyModel);
    Society getByEmail(String email);
    void enableSociety(Society society);
}
