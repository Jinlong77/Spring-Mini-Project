package org.kshrd.gamifiedhabittracker.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.kshrd.gamifiedhabittracker.enumeration.EventType;
import org.kshrd.gamifiedhabittracker.model.AppUserEntity;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class UserEvent {

    private AppUserEntity user;
    private EventType eventType;
}
