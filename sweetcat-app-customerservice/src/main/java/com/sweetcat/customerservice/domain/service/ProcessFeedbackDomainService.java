package com.sweetcat.customerservice.domain.service;

import com.sweetcat.customerservice.domain.feedback.entity.Feedback;
import com.sweetcat.customerservice.domain.feedback.entity.Informer;
import com.sweetcat.customerservice.domain.staff.entity.CustomerServiceStaff;
import org.springframework.stereotype.Service;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-13:28
 * @version: 1.0
 */
@Service
public class ProcessFeedbackDomainService {
    public void processFeedback(CustomerServiceStaff staff, Feedback feedback) {
        Informer informer = new Informer(staff.getStaffId());
        feedback.setInformer(informer);
    }
}
