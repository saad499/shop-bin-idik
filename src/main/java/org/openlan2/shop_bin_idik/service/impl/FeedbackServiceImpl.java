package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.AddFeedbackRequest;
import org.openlan2.shop_bin_idik.dto.FeedbackDto;
import org.openlan2.shop_bin_idik.entities.Feedback;
import org.openlan2.shop_bin_idik.repository.FeedbackRepository;
import org.openlan2.shop_bin_idik.service.FeedbackService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Override
    public FeedbackDto addFeedback(AddFeedbackRequest request) {
        if (request.getRating() < 1 || request.getRating() > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }

        Feedback feedback = Feedback.builder()
            .rating(request.getRating())
            .message(request.getMessage())
            .build();

        Feedback savedFeedback = feedbackRepository.save(feedback);
        return mapToDto(savedFeedback);
    }

    @Override
    public List<FeedbackDto> getAllFeedback() {
        List<Feedback> feedbacks = feedbackRepository.findAllByOrderByIdDesc();
        return feedbacks.stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public Page<FeedbackDto> getAllFeedback(Pageable pageable) {
        Page<Feedback> feedbacks = feedbackRepository.findAllByOrderByIdDesc(pageable);
        return feedbacks.map(this::mapToDto);
    }

    @Override
    public FeedbackDto getFeedbackById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Feedback not found"));
        return mapToDto(feedback);
    }

    @Override
    public Double getAverageRating() {
        Double average = feedbackRepository.getAverageRating();
        return average != null ? average : 0.0;
    }

    @Override
    public List<FeedbackDto> getFeedbackByRating(Integer rating) {
        List<Feedback> feedbacks = feedbackRepository.findByRating(rating);
        return feedbacks.stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    private FeedbackDto mapToDto(Feedback feedback) {
        return FeedbackDto.builder()
            .id(feedback.getId())
            .rating(feedback.getRating())
            .message(feedback.getMessage())
            .build();
    }
}
