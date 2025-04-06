

![Custom Badge](https://img.shields.io/badge/Status-Active-brightgreen)
![Static Badge](https://img.shields.io/badge/Controller-habitlog-blue)
.
demo: https://youtu.be/H4so23Pz8AA

i am using this direct
```
spring:
  application:
    name:
      gamified-habit-tracker

  datasource:
    url: jdbc:postgresql://localhost:5432/spring_mini_project
    username: 'nyfong'
    password: '*******'


jwt:
  secret: ${JWT_SECRET}
  expiration: ${JWT_EXPIRATION}

```
UserController បានបង្កើតដើម្បីជំនួយគ្រប់គ្រងការងារ habitlog

```java

public class UserController {
    private final AppUserService appUserService;
    @GetMapping
    @Operation(summary = "Get User Profile by id but static")
    public ResponseEntity<Response<?>> getUserProfile() {
        UUID userUUID = UUID.fromString("f1a2b3c4-5d6e-7f89-a0b1-2345c678d901");
        AppUserEntity userEntity = appUserService.getAppuserByID(userUUID);
        Response<AppUserEntity> response = new Response<>(
                "Habit log created successfully!",
                "OK",
                userEntity,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, OK);
    }
}
```

<div style="color:white; background-color:green; padding:3px 8px; border-radius:4px; font-family:monospace">
    ប្រើសម្រាប់ update exp របស់ user
</div>

<div style="color:white; background-color:purple; margin:10px 0; padding:3px 8px; border-radius:4px; font-family:monospace">
    service/implementation/HabitLogServiceImp.java
</div>

```java
ក្នុងការ post habitlog មាន៣ចូលរួម:
1. @Override
   @Transactional
   public HabitLogEntity createNewHabitLogService(HabitLogRequest habitLogRequest)
   
2. private void updateUserXp(UUID userId, int xpToAdd)
   
3. private void checkForLevelUp(AppUserEntity user, int newXp)
     
ក្នុងការ get habitlog by id មានចូលរួម:
1. private List<HabitLogEntity> getHabitLogByIdService(UUID habitId, Integer page, Integer size)
```

<div style="color:white; background-color:green; padding:3px 8px; border-radius:4px; font-family:monospace">
    HabitController ក្នុង package
</div>

```java
package org.kshrd.gamifiedhabittracker.controller;
```

<div style="color:white; background-color:green; padding:3px 8px; border-radius:4px; font-family:monospace">
    ក្នុង HabitController
</div>

```java
@GetMapping("/{habit-id}")
public ResponseEntity<?> getAllHabitById(@PathVariable("habit-id") UUID habitId) {
    UUID userUUID = UUID.fromString("f1a2b3c4-5d6e-7f89-a0b1-2345c678d901");
    HabitEntity habitEntity = habitService.getHabitsByHabitId(habitId);
    Response<HabitEntity> response = new Response<>(
            "Habit log created successfully!",
            "OK",
            habitEntity,
            LocalDateTime.now()
    );
    return new ResponseEntity<>(response, HttpStatus.OK);
}
```

វាជួយនៅពេលដែលយើងធ្វើការប្រើ endpoint POST habit-log ព្រោះវាត្រូវមាន habitId ចាំបាច់ក្នុងការ map

<div style="color:white; background-color:palevioletred; padding:3px 8px; border-radius:4px; font-family:monospace">
    POST
</div>

```json
{
  "status": "COMPLETED",
  "habitId": "d6913c64-7dc5-4bd1-ab1a-1037fb25e4f9"
}
```

បើ <span style="color:green">GET</span> យើងប្រើ pagination

<div style="border:1px solid #ccc; padding:10px; border-radius:5px; background-color:#f9f9f9">
  <p style="font-weight:bold">Note</p>

  <div style="color:white; background-color:red; padding:3px 8px; border-radius:4px; font-family:monospace; margin:5px 0">
    ញុមអត់ទាន់ validate ទេ
  </div>

  <div style="color:white; background-color:orange; padding:3px 8px; border-radius:4px; font-family:monospace; margin:5px 0">
    ញុមដូរ filed ខ្លះ តែមិនច្រើនទេ
  </div>

  <div style="color:white; background-color:green; padding:3px 8px; border-radius:4px; font-family:monospace; margin:5px 0">
    សូមអរគុណ
  </div>
</div>

![Custom Badge](http://34.87.39.167:9085/api/v1/files/preview-file/f407c063-9d51-4b4e-a44d-a9848416c23e.png)
```
