package workout.calendar.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workout.calendar.domain.dto.FormLoginUserDto;
import workout.calendar.domain.entity.User;
import workout.calendar.repository.UserRepository;
import workout.calendar.service.UserService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;


    @Transactional
    @Override
    public Long register(FormLoginUserDto formLoginUserDto) {
        validateDuplicateMember(formLoginUserDto.getUsername());
        formLoginUserDto.setPassword(encoder.encode(formLoginUserDto.getPassword()));
        User user = new User();
        user.setUser(formLoginUserDto);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateMember(String username) {
        User findUsers = userRepository.findByUsername(username);
        System.out.println("findUsers = " + findUsers);
        if (findUsers == null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
