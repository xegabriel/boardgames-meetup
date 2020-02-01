package app.boardgames.bgcore.controller;

import app.boardgames.bgcore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/core")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/votePlayer/{eventTitle}/{voteType}/{votedUser}", method = RequestMethod.PUT)
    public ResponseEntity<?> confirmAttendance(HttpServletRequest request, @PathVariable String eventTitle, @PathVariable String voteType, @PathVariable String votedUser) {
        return ResponseEntity.ok(userService.votePlayer(request.getHeader("email"), eventTitle, voteType, votedUser));
    }
}
