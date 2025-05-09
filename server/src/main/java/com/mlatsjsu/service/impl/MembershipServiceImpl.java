package com.mlatsjsu.service.impl;

import com.mlatsjsu.model.Membership;
import com.mlatsjsu.repository.MembershipRepository;
import com.mlatsjsu.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Override
    public List<Membership> findByProjectId(Long projectId) {
        return membershipRepository.findByProjectId(projectId);
    }

    @Override
    public Optional<Membership> findById(Long userId, Long projectId) {
        return membershipRepository.findById(userId, projectId);
    }

    @Override
    @Transactional
    public Membership create(Membership membership) {
        return membershipRepository.save(membership);
    }

    @Override
    @Transactional
    public Membership update(Long userId, Long projectId, Membership membership) {
        membership.setUserId(userId);
        membership.setProjectId(projectId);
        membershipRepository.update(membership);
        return membershipRepository.findById(userId, projectId).orElseThrow();
    }

    @Override
    @Transactional
    public void delete(Long userId, Long projectId) {
        membershipRepository.deleteById(userId, projectId);
    }
}