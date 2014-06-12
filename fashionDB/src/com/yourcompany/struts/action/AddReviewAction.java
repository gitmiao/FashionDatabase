/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.yourcompany.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.yourcompany.struts.form.AddReviewForm;

import dao.UserReviewDAO;

/**
 * MyEclipse Struts Creation date: 06-22-2013
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/addReview" name="addReviewForm" input="addReview.jsp"
 *                scope="request" validate="true"
 */
public class AddReviewAction extends Action {
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AddReviewForm addReviewForm = (AddReviewForm) form;
		final UserReviewDAO dao = new UserReviewDAO();
		final Long userId = dao.findUserIdByName(addReviewForm.getUserName());
		final ActionErrors msg = new ActionErrors();
		if (userId == null) {
			msg.add("addReviewResult",
					new ActionError("errors.invalidUserName"));
			saveErrors(request, msg);
		} else {
			try {
				dao.addReview(userId, addReviewForm.getProductId(),
						addReviewForm.getRate());
				msg.add("addReviewResult", new ActionError(
						"addReviewSuccessful"));
				saveErrors(request, msg);
			} catch (Exception e) {
				e.printStackTrace();
				msg.add("addReviewResult", new ActionError(
						"errors.addReviewFailed"));
				saveErrors(request, msg);
			}
		}
		dao.closeConnection();
		return mapping.findForward("addReviewResult");
	}
}