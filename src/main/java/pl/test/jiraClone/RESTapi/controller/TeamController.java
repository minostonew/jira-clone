package pl.test.jiraClone.RESTapi.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.test.jiraClone.RESTapi.dto.TeamDto;
import pl.test.jiraClone.RESTapi.service.TeamService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/team")
@AllArgsConstructor
@Slf4j
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto teamDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.save(teamDto));
    }

    @GetMapping
    public ResponseEntity<List<TeamDto>> getAllTeams()
    {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.getAllTeams());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeam(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.getTeam(id));
    }

    @RequestMapping(value = "{teamId}/add-user/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> addUserToTeam(@PathVariable Long teamId, @PathVariable Long userId)
    {
        teamService.addUserToTeam(teamId, userId);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> editTeam(@RequestBody TeamDto teamDto)
    {
        teamService.editTeam(teamDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTeam(@PathVariable Long teamId)
    {
        teamService.deleteTeam(teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "{teamId}/delete-user/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> deleteUserFromTeam(@PathVariable Long teamId, @PathVariable Long userId)
    {
        teamService.deleteUserFromTeam(teamId, userId);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}

