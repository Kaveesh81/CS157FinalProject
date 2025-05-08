package com.mlatsjsu.controller;

import com.mlatsjsu.model.Membership;
import com.mlatsjsu.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects/{projectId}/memberships")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @GetMapping
    public ResponseEntity<List<Membership>> getAllMemberships(@PathVariable Long projectId) {
        return ResponseEntity.ok(membershipService.findByProjectId(projectId));
    }

    @GetMapping("/{membershipId}")
    public ResponseEntity<Membership> getMembershipById(
            @PathVariable Long projectId,
            @PathVariable Long membershipId) {
        return membershipService.findById(membershipId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Membership> createMembership(
            @PathVariable Long projectId,
            @RequestBody Membership membership) {
        membership.setProjectId(projectId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(membershipService.create(membership));
    }

    @PutMapping("/{membershipId}")
    public ResponseEntity<Membership> updateMembership(
            @PathVariable Long projectId,
            @PathVariable Long membershipId,
            @RequestBody Membership membership) {
        membership.setProjectId(projectId);
        return ResponseEntity.ok(membershipService.update(membershipId, membership));
    }

    @DeleteMapping("/{membershipId}")
    public ResponseEntity<Void> deleteMembership(
            @PathVariable Long projectId,
            @PathVariable Long membershipId) {
        membershipService.delete(membershipId);
        return ResponseEntity.noContent().build();
    }
}