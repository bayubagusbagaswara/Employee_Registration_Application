package com.bayu.employee.repository;

import com.bayu.employee.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, String> {
}
