package workout.calendar.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workout.calendar.domain.dto.user.UserModifyFormDto;
import workout.calendar.domain.dto.user.UserResisterFormDto;
import workout.calendar.domain.dto.user.UserRoleFormDto;
import workout.calendar.domain.entity.User;
import workout.calendar.repository.UserRepository;
import workout.calendar.service.UserService;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Transactional
    @Override
    public Long createUser(UserResisterFormDto userResisterFormDto) {
        if (userRepository.findByUsername(userResisterFormDto.getUsername()) != null) {
            return null;
        } else {
            User user = new User();
            userResisterFormDto.setPassword(encoder.encode(userResisterFormDto.getPassword()));
            user.setUser(userResisterFormDto);
            userRepository.save(user);
            return user.getId();
        }
    }

    @Override
    public Page<UserRoleFormDto> getUsers(String cat, String info, Pageable pageable) {
        if (cat.equals("username")) {
            return userRepository.findRoleByUsernameContains(info, pageable)
                    .map(UserRoleFormDto::new);
        } else {
            return userRepository.findRoleByNicknameContains(info, pageable)
                    .map(UserRoleFormDto::new);
        }
    }

    @Override
    public UserRoleFormDto getUserRoleForm(Long id) {
        UserRoleFormDto userRoleFormDto = new UserRoleFormDto();
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return userRoleFormDto;
        } else {
            return user.map(UserRoleFormDto::new).get();
        }
    }

    @Override
    public UserModifyFormDto getUserModifyForm(Long id) {
        UserModifyFormDto userModifyFormDto = new UserModifyFormDto();
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return userModifyFormDto;
        } else {
            return user.map(UserModifyFormDto::new).get();
        }
    }

    @Override
    @Transactional
    public Long modifyRole(UserRoleFormDto userRoleFormDto) {
        User user = userRepository.findById(userRoleFormDto.getId()).orElseThrow(IllegalArgumentException::new);
        user.changeRole(userRoleFormDto.getRole());
        return user.getId();
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Long modifyUser(UserModifyFormDto userModifyFormDto) {
        User user = userRepository.findById(userModifyFormDto.getId()).orElseThrow(IllegalArgumentException::new);
        if (user.getProvider() == null){
            userModifyFormDto.setPassword(encoder.encode(userModifyFormDto.getPassword()));
            user.changeInfo(userModifyFormDto);
        } else {
            user.changeProviderInfo(userModifyFormDto);
        }
        return user.getId();
    }

}
