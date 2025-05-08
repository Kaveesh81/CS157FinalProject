package com.mlatsjsu.service;

import com.mlatsjsu.model.Membership;
import java.util.List;
import java.util.Optional;

public interface MembershipService {
    List<Membership> findByProjectId(Long projectId);

    Optional<Membership> findById(Long membershipId);

    Membership create(Membership membership);

    Membership update(Long membershipId, Membership membership);

    void delete(Long membershipId);
}