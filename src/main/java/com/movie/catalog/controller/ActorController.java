package com.movie.catalog.controller;

import com.movie.catalog.model.Actor;
import com.movie.catalog.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping("/movies")
    public ResponseEntity<List<Actor>> getAllActors
            (@RequestParam(required = false) String name) {
        try {
            List<Actor> actors;

            if (name == null)
                actors = actorRepository.findAll();
            else
                actors = actorRepository.findByName(name);
            if (actors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(actors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") long id) {
        Actor actor = actorRepository.findById(id);

        if (actor != null) {
            return new ResponseEntity<>(actor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<String> createActor(@RequestBody Actor actor) {
        try {
            actorRepository.save(actor);
            return new ResponseEntity<>("Actor was created successfully.",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateActor(@PathVariable("id") long id,
                                              @RequestBody Actor actor) {
        Actor _actor = actorRepository.findById(id);

        if (_actor != null) {
            _actor.setId(id);
            _actor.setName(actor.getName());
            _actor.setSurName(actor.getSurName());
            actorRepository.update(_actor);
            return new ResponseEntity<>("Actor was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Actor with id=" + id,
                    HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/actor/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable("id") long id) {
        try {
            int result = actorRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Actor with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Actor was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete actor.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}