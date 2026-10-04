package com.PollApp.service;

import com.PollApp.entity.OptionVote;
import com.PollApp.entity.Poll;
import com.PollApp.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PollService {

    private final PollRepository pollRepository;

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(Long pollId) {
        return pollRepository.findById(pollId);
    }

    public void vote(Long pollId, int optionIndex) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found"));
        List<OptionVote> options = poll.getOptions();
        if(optionIndex < 0 || optionIndex >= options.size()) {
            throw new IllegalArgumentException("Invalid option index");
        }
        OptionVote selectedOption = options.get(optionIndex);
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);
        pollRepository.save(poll);
    }
}
