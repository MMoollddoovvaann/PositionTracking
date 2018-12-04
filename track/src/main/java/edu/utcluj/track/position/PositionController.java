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

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Position save(@RequestBody Position position) {
        if (position.getId() != null) {
            throw new IllegalArgumentException("Invalid 'id' value. It should be empty");
        }

        return positionService.savePosition(position);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public boolean delete(@PathVariable("id") long id){
        if(positionService.deletePosition(id)){
            return true;
        }
        else {
            throw new IllegalArgumentException("Invalid position! This position doesn't exists in the DataBase.");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{terminalId}/{newLatitude}/{newLongitude}")
    public Position update(@PathVariable("terminalId") String terminalId, @PathVariable("newLatitude") String newLatitude,
                           @PathVariable("newLongitude") String newLongitude)throws IllegalArgumentException{
        return positionService.updatePosition(terminalId, newLatitude, newLongitude);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/read/{terminalId}")
    public Position read (@PathVariable("terminalId") String terminalId)throws IllegalArgumentException{
        return positionService.readPosition(terminalId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/readAll")
    public List<Position> readAllPositions (){
        return positionService.getAllPositions();
    }
}
