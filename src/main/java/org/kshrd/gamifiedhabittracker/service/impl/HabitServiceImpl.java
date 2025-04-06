package org.kshrd.gamifiedhabittracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.HabitEntity;
import org.kshrd.gamifiedhabittracker.repository.HabitRepository;
import org.kshrd.gamifiedhabittracker.service.HabitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    @Override
    public List<HabitEntity> getAllHabitsByUserId(String userId) {
//        List<HabitEntity> habits = habitRepository.findAllByUserId();

        return List.of();
    }
}
