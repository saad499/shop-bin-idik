package org.openlan2.shop_bin_idik.controller;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.AddFeedbackRequest;
import org.openlan2.shop_bin_idik.dto.FeedbackDto;
import org.openlan2.shop_bin_idik.service.FeedbackService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/add")
    public ResponseEntity<FeedbackDto> addFeedback(@RequestBody AddFeedbackRequest request) {
        return ResponseEntity.ok(feedbackService.addFeedback(request));
    }

    @GetMapping("")
    public ResponseEntity<List<FeedbackDto>> getAllFeedback() {
        return ResponseEntity.ok(feedbackService.getAllFeedback());
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<FeedbackDto>> getAllFeedbackPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(feedbackService.getAllFeedback(pageable));
    }

    @GetMapping("/one")
    public ResponseEntity<FeedbackDto> getFeedbackById(@RequestParam Long id) {
        return ResponseEntity.ok(feedbackService.getFeedbackById(id));
    }

    @GetMapping("/average-rating")
    public ResponseEntity<Double> getAverageRating() {
        return ResponseEntity.ok(feedbackService.getAverageRating());
    }

    @GetMapping("/by-rating")
    public ResponseEntity<List<FeedbackDto>> getFeedbackByRating(@RequestParam Integer rating) {
        return ResponseEntity.ok(feedbackService.getFeedbackByRating(rating));
    }
}
