package com.sweetcat.app_feedback.infrastructure.dao;

import com.sweetcat.app_feedback.domain.feedback.entity.AppFeedback;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppFeedbackMapper {

    void insertOne(AppFeedback feedback);

}