package org.kshrd.gamifiedhabittracker.service.serviceImpl;
import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.exception.ResourceNotFoundException;
import org.kshrd.gamifiedhabittracker.model.dto.request.AppUserRequest;
import org.kshrd.gamifiedhabittracker.model.dto.response.AppUserDTO;
import org.kshrd.gamifiedhabittracker.repository.AppUserRepository;
import org.kshrd.gamifiedhabittracker.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    @Override
    public AppUserDTO getAppUser() {
        AppUserDTO appUser = appUserRepository.findAppUser("john@example.com");
        ModelMapper modelMapper = new ModelMapper();
        System.out.println(appUser);
        AppUserDTO appUserDTO = modelMapper.map(appUser, AppUserDTO.class);
        if (appUser == null)
        {
            throw new ResourceNotFoundException("User is not Found");
        }
        return appUserDTO;
    }

    @Override
    public AppUserDTO updateAppUser(AppUserRequest appUserRequest) {
        AppUserDTO  appUserDTO = appUserRepository.updateAppUser("mike@example.com" , appUserRequest);
        return appUserDTO;
    }

    @Override
    public void deleteAppUser(String email) {
      appUserRepository.deleteAppUser(email);
    }
}