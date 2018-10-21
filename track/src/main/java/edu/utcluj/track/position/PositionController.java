package edu.utcluj.track.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * @author radu.miron
 * @since 18.10.2016
 */
@RestController
@RequestMapping(value = "/position")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @RequestMapping(method = RequestMethod.POST)
    public Position save(@RequestBody Position position) {
        if (position.getId() != null) {
            throw new IllegalArgumentException("Invalid 'id' value. It should be empty");
        }

        return positionService.savePosition(position);
    }
}
