package org.kshrd.gamifiedhabittracker.service;
import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.dto.HabitEntity;
import org.kshrd.gamifiedhabittracker.repository.HabitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class HabitServiceImpl implements HabitService{

    private final HabitRepository habitRepository;

    @Override
    public List<HabitEntity> getAllHabit(Integer page, Integer size) {

        return habitRepository.getAllHabit(page, size);
    }

    @Override
    public HabitEntity getHabitById(UUID id) {

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Habit ID cannot be null ??");
        }
         HabitEntity habit = habitRepository.getHabitById(id);

        if(habit == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Habit Id not been found ??");
        }
        return  habit;
    }

}
