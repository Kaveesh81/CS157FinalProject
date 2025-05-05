package lib.service;

import java.sql.SQLException;
import java.util.List;
import lib.dao.MemberDAO;
import lib.model.Member;

/**
 * Service class for Member-related business logic
 */
public class MemberService {
    private MemberDAO memberDAO;
    
    public MemberService() {
        this.memberDAO = new MemberDAO();
    }
    
    /**
     * Get all members
     * 
     * @return List of all members
     * @throws ServiceException if an error occurs
     */
    public List<Member> getAllMembers() throws ServiceException {
        try {
            return memberDAO.getAllMembers();
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving members", e);
        }
    }
    
    /**
     * Get a member by ID
     * 
     * @param memberId the ID of the member to retrieve
     * @return the Member object, or null if not found
     * @throws ServiceException if an error occurs
     */
    public Member getMemberById(int memberId) throws ServiceException {
        try {
            return memberDAO.getMemberById(memberId);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving member with ID: " + memberId, e);
        }
    }
    
    /**
     * Authenticate a member by email and password
     * 
     * @param email the member's email
     * @param password the member's password
     * @return the authenticated Member object, or null if authentication fails
     * @throws ServiceException if an error occurs
     */
    public Member authenticateMember(String email, String password) throws ServiceException {
        try {
            Member member = memberDAO.getMemberByEmail(email);
            if (member != null && member.getPassword().equals(password)) {
                // In a real application, you would use proper password hashing
                return member;
            }
            return null;
        } catch (SQLException e) {
            throw new ServiceException("Error authenticating member", e);
        }
    }
    
    /**
     * Register a new member
     * 
     * @param member the Member object to register
     * @return the ID of the newly registered member
     * @throws ServiceException if an error occurs
     */
    public int registerMember(Member member) throws ServiceException {
        try {
            // Check if email already exists
            Member existingMember = memberDAO.getMemberByEmail(member.getEmail());
            if (existingMember != null) {
                throw new ServiceException("Email already registered");
            }
            
            // In a real application, you would hash the password here
            
            return memberDAO.addMember(member);
        } catch (SQLException e) {
            throw new ServiceException("Error registering member", e);
        }
    }
    
    /**
     * Update a member's information
     * 
     * @param member the Member object with updated information
     * @return true if the update was successful, false otherwise
     * @throws ServiceException if an error occurs
     */
    public boolean updateMember(Member member) throws ServiceException {
        try {
            return memberDAO.updateMember(member);
        } catch (SQLException e) {
            throw new ServiceException("Error updating member", e);
        }
    }
    
    /**
     * Delete a member
     * 
     * @param memberId the ID of the member to delete
     * @return true if the deletion was successful, false otherwise
     * @throws ServiceException if an error occurs
     */
    public boolean deleteMember(int memberId) throws ServiceException {
        try {
            return memberDAO.deleteMember(memberId);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting member", e);
        }
    }
    
    /**
     * Check if a member is eligible to be a project member
     * 
     * @param memberId the ID of the member to check
     * @return true if the member is eligible, false otherwise
     * @throws ServiceException if an error occurs
     */
    public boolean isEligibleForProjectMembership(int memberId) throws ServiceException {
        try {
            return memberDAO.isEligibleForProjectMembership(memberId);
        } catch (SQLException e) {
            throw new ServiceException("Error checking member eligibility", e);
        }
    }
    
    /**
     * Get members by role
     * 
     * @param role the role to filter by
     * @return List of members with the specified role
     * @throws ServiceException if an error occurs
     */
    public List<Member> getMembersByRole(String role) throws ServiceException {
        try {
            return memberDAO.getMembersByRole(role);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving members by role", e);
        }
    }
}
