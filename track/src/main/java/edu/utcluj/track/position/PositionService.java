package edu.utcluj.track.position;

import javafx.print.PaperSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author radu.miron
 * @since 18.10.2016
 */
@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Position savePosition(Position position) {
        position.setCreateTime(new Date(System.currentTimeMillis()));
        return positionRepository.save(position);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deletePosition(long idPosition){

        if (positionRepository.exists(idPosition)) {
            //if the position required to be deleted exists in the DataBase, than it is deleted and a 'true confirmation is returned'
            positionRepository.delete(positionRepository.getOne(idPosition));
            return true;
        }
        else{
            //the given position doesn't exists in the current DataBase than the method returns false  because the
            //operation cannot be done
            return false;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Position updatePosition(String terminalId, String newLatitude, String newLongitude){
        ArrayList<Position> positionList = (ArrayList<Position>) positionRepository.findAll();
        for (Position positionInList : positionList){
            if(terminalId.equals(positionInList.getTerminalId())){
                positionInList.setLongitude(newLongitude);
                positionInList.setLatitude(newLatitude);
                return positionRepository.save(positionInList);
            }
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Position readPosition(String terminalId){
        //returns null if no position with this id was founds
        ArrayList<Position> positionList = (ArrayList<Position>) positionRepository.findAll();
        for (Position positionInList : positionList){
            if(terminalId.equals(positionInList.getTerminalId())){
                return positionRepository.getOne(positionInList.getId());
            }
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Position> getAllPositions(){
        return positionRepository.findAll();
    }
}
