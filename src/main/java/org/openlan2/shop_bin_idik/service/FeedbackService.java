package org.openlan2.shop_bin_idik.service;

import org.openlan2.shop_bin_idik.dto.AddFeedbackRequest;
import org.openlan2.shop_bin_idik.dto.FeedbackDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeedbackService {
    FeedbackDto addFeedback(AddFeedbackRequest request);
    List<FeedbackDto> getAllFeedback();
    Page<FeedbackDto> getAllFeedback(Pageable pageable);
    FeedbackDto getFeedbackById(Long id);
    Double getAverageRating();
    List<FeedbackDto> getFeedbackByRating(Integer rating);
}
