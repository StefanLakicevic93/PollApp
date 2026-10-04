package com.PollApp.controller;

import com.PollApp.entity.Poll;
import com.PollApp.entity.Vote;
import com.PollApp.service.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poll")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PollController {

        private final PollService pollService;

        @PostMapping
        public Poll createPoll(@RequestBody Poll poll) {
                return pollService.createPoll(poll);
        }

        @GetMapping
        public List<Poll> getAllPolls() {
                return pollService.getAllPolls();
        }

        @GetMapping("/{pollId}")
        public ResponseEntity<Poll> getPollById(@PathVariable Long pollId) {
                return pollService.getPollById(pollId)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping("/vote")
        public void  vote(@RequestBody Vote vote) {
                pollService.vote(vote.getPollId(), vote.getOptionIndex());
        }

}
