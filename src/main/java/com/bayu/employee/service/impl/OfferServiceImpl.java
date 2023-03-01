package com.bayu.employee.service.impl;

import com.bayu.employee.repository.OfferRepository;
import com.bayu.employee.service.OfferService;
import com.bayu.employee.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final UserService userService;

    public OfferServiceImpl(OfferRepository offerRepository, UserService userService) {
        this.offerRepository = offerRepository;
        this.userService = userService;
    }
}
