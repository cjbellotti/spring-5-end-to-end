package springfive.cms.domain.models;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class News {

    String id;

    String title;

    String content;

    User author;

    Set<User> mandatoryReviewera = new HashSet<>();

    Set<Category> categories = new HashSet<>();

    Set<Tag> tags = new HashSet<>();

    Set<Review> reviewers = new HashSet<>();

    public Review review(String userId, String status) {
        final Review review = new Review(userId, status);
        this.reviewers.add(review);
        return review;
    }

    public Boolean revised() {
        return this.mandatoryReviewera.stream().allMatch(reviewer -> this.reviewers.stream().anyMatch(review -> reviewer.id.equals(review.userId) && "approved".equals((review.status))));
    }

}
