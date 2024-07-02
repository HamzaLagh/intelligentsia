package com.intelligentsia_backend.services.service;

import com.intelligentsia_backend.entity.Postuler;
import com.intelligentsia_backend.model.PostulerModel;

public interface PostulerService {
    Postuler save(PostulerModel postulerModel);

    Postuler recupPostuler(String emailStudent);

    Iterable<Postuler> recupAllPostulerSociety(Long id);
}
