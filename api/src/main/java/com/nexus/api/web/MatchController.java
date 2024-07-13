package main.java.com.nexus.api.web;

import com.nexus.api.business.*;
import com.nexus.api.data.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/match")

public class MatchController {


    private final AccountService accountService;
    private final LikeService likeService;
    private final MatchService matchService;

    @Autowired
    public MatchController(AccountService accountService, LikeService likeService, MatchService matchService){
        this.accountService = accountService;
        this.likeService = likeService;
        this.matchService = matchService;
    }


    @PostMapping("/create-like")
    public ResponseEntity<Void> createLike(@RequestBody Like like) {
        try {
            System.out.println("Received like request: " + like);

            likeService.createLike(like);

            // Return a success response with no content
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/fetch-likes/{accountId}")
    public ResponseEntity<List<Like>> fetchLikes(@PathVariable Long accountId) {
        try {
            // Fetch likes for the current account
            Account currentAccount = accountService.findById(accountId);
            List<Like> receivedLikes = likeService.getLikesByReceiverId(currentAccount.getAccountId());

            // Return the list of received likes
            return new ResponseEntity<>(receivedLikes, HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions and return an error response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-match")
    public ResponseEntity<Void> createMatch(@RequestBody Like like) {
        try {
            matchService.createMatch(like);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle exceptions and return an error response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fetch-matches/{accountId}")
    public ResponseEntity<List<Match>> fetchMatches(@PathVariable Long accountId) {
        try {
            // Fetch matches for the current account
            List<Match> matches = matchService.getMatchesByAccountId(accountId);

            // Return the list of received likes
            return new ResponseEntity<>(matches, HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions and return an error response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}