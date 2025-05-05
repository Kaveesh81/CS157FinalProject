package web.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.model.Member;
import lib.service.MemberService;
import lib.service.ServiceException;

/**
 * Servlet for handling Member-related HTTP requests
 */
@WebServlet("/members/*")
public class MemberServlet extends HttpServlet {
    private MemberService memberService;
    
    @Override
    public void init() throws ServletException {
        memberService = new MemberService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // List all members
                List<Member> members = memberService.getAllMembers();
                request.setAttribute("members", members);
                request.getRequestDispatcher("/WEB-INF/views/members/list.jsp").forward(request, response);
            } else {
                // Get member by ID
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length > 1) {
                    int memberId = Integer.parseInt(pathParts[1]);
                    Member member = memberService.getMemberById(memberId);
                    
                    if (member != null) {
                        request.setAttribute("member", member);
                        request.getRequestDispatcher("/WEB-INF/views/members/view.jsp").forward(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Member not found");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid member ID");
                }
            }
        } catch (ServiceException e) {
            throw new ServletException("Error processing member request", e);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid member ID format");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if ("create".equals(action)) {
                // Create new member
                Member member = new Member();
                member.setName(request.getParameter("name"));
                member.setEmail(request.getParameter("email"));
                member.setPassword(request.getParameter("password"));
                member.setLinkedIn(request.getParameter("linkedin"));
                member.setGradDate(request.getParameter("gradDate"));
                member.setRole(request.getParameter("role"));
                member.setStartDate(request.getParameter("startDate"));
                
                int memberId = memberService.registerMember(member);
                response.sendRedirect(request.getContextPath() + "/members/" + memberId);
            } else if ("update".equals(action)) {
                // Update existing member
                Member member = new Member();
                member.setMemberId(Integer.parseInt(request.getParameter("memberId")));
                member.setName(request.getParameter("name"));
                member.setEmail(request.getParameter("email"));
                member.setLinkedIn(request.getParameter("linkedin"));
                member.setGradDate(request.getParameter("gradDate"));
                member.setRole(request.getParameter("role"));
                member.setStartDate(request.getParameter("startDate"));
                
                boolean updated = memberService.updateMember(member);
                if (updated) {
                    response.sendRedirect(request.getContextPath() + "/members/" + member.getMemberId());
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update member");
                }
            } else if ("delete".equals(action)) {
                // Delete member
                int memberId = Integer.parseInt(request.getParameter("memberId"));
                boolean deleted = memberService.deleteMember(memberId);
                if (deleted) {
                    response.sendRedirect(request.getContextPath() + "/members");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete member");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } catch (ServiceException e) {
            throw new ServletException("Error processing member request", e);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid member ID format");
        }
    }
}
