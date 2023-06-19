package com.example.nrs.algorithm;


import com.example.nrs.entity.Rating;
import com.example.nrs.repository.NoteRepo;
import com.example.nrs.repository.RatingRepo;
import com.example.nrs.repository.UserRepo;

import java.util.*;


public class CosineSimilarity {
    //Data structure to store user-item ratings
    private Map<Integer, Map<Integer, Float>> userRatings = new HashMap<>();

    private final UserRepo userRepo;
    private final NoteRepo noteRepo;
    private final RatingRepo ratingRepo;
    private  Rating rating;

    public CosineSimilarity(UserRepo userRepo, NoteRepo noteRepo, RatingRepo ratingRepo) {
        this.userRepo = userRepo;
        this.noteRepo = noteRepo;
        this.ratingRepo = ratingRepo;
        userRatings = new HashMap<>();
    }


    //Add user-item ratings to the system
    public void addUserRating() {
        List<Rating> ratings = ratingRepo.findAll();
        if (!ratings.isEmpty()) {
            for (Rating eachUserRating : ratings
            ) {
                if (userRatings.containsKey(eachUserRating.getUser().getId())) {
                    continue;
                }
                List<Rating> ratings1 = ratingRepo.findAllByUser(eachUserRating.getUser());
                Map<Integer, Float> localRatings = new HashMap<>();
                for (Rating eachRating : ratings1
                ) {
                    localRatings.put(eachRating.getNote().getId(), eachRating.getStars());
                }
                userRatings.put(eachUserRating.getUser().getId(), localRatings);

            }
        }
    }


    //calculate similarity between two users
    private double calculateSimilarity(Integer user1, Integer user2) {

        Set<Integer> user1Notes = userRatings.get(user1).keySet();
        Set<Integer> user2Notes = userRatings.get(user2).keySet();
        float dotProdut = 0;
        double user1Norm = 0;
        double user2Norm = 0;
        for (Integer note : user1Notes
        ) {
            if (user2Notes.contains(note)) {
                dotProdut = dotProdut + userRatings.get(user1).get(note) * userRatings.get(user2).get(note);
            }
            user1Norm = user1Norm + Math.pow(userRatings.get(user1).get(note), 2);

        }
        for (Integer note : user2Notes) {
            user2Norm = user2Norm + Math.pow(userRatings.get(user2).get(note), 2);
        }
        if (user1Norm == 0 || user2Norm == 0) {
            return 0.0;//return 0 if one of the user has no ratings
        }
        return dotProdut / (Math.sqrt(user1Norm) * Math.sqrt(user2Norm));

    }

    //Recommend top-k notes for the given user
    public List<Integer> recommendNotes(Integer targetUser, int numRecommendation) {
        addUserRating();
        if (!userRatings.containsKey(targetUser)) {
            System.out.println("User " + targetUser + " not found");
            return Collections.emptyList();
        }

        //Calculate similariity between the target user and other users
        Map<Integer, Double> similarityMap = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, Float>> entry : userRatings.entrySet()) {
            Integer userId = entry.getKey();
            if (!userId.equals(targetUser)) {
                double similarity = calculateSimilarity(targetUser, userId);
                similarityMap.put(userId, similarity);
            }
        }

        //sort users by similarity in descending order
        List<Map.Entry<Integer, Double>> sortedSimilarities = new ArrayList<>(similarityMap.entrySet());
        sortedSimilarities.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        //Generate note recommendations based on similar users
        Set<Integer> targetUserNotes = userRatings.get(targetUser).keySet();
        List<Integer> recommendations = new ArrayList<>();

        for (Map.Entry<Integer, Double> entry : sortedSimilarities) {
            Integer similarUser = entry.getKey();
            Map<Integer, Float> similarUserRatings = userRatings.get(similarUser);

            for (Integer note : similarUserRatings.keySet()
            ) {
                if (!targetUserNotes.contains(note) && !recommendations.contains(note)) {
                    recommendations.add(note);
                    if (recommendations.size() >= numRecommendation) {
                        return recommendations;
                    }
                }
            }

        }
        return recommendations;
    }
}