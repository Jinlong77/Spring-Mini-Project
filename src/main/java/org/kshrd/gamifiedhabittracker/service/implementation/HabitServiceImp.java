package org.kshrd.gamifiedhabittracker.service.implementation;


import org.kshrd.gamifiedhabittracker.model.dto.HabitEntity;
import org.kshrd.gamifiedhabittracker.repository.HabitRepository;
import org.kshrd.gamifiedhabittracker.service.HabitLogService;
import org.kshrd.gamifiedhabittracker.service.HabitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HabitServiceImp implements HabitService {

    private final HabitRepository habitRepository;

    public HabitServiceImp(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @Override
    public List<HabitEntity> getHabitsByUserId(UUID userId) {
        return habitRepository.getHabitsByUserIdRepo(userId);
    }
}