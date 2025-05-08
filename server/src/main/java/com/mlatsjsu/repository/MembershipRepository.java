package com.mlatsjsu.repository;

import com.mlatsjsu.model.Membership;
import java.util.List;
import java.util.Optional;

public interface MembershipRepository {
    List<Membership> findByProjectId(Long projectId);

    Optional<Membership> findById(Long membershipId);

    Membership save(Membership membership);

    int update(Membership membership);

    int deleteById(Long membershipId);
}