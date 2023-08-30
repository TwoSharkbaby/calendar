package workout.calendar.security.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Service;
import workout.calendar.domain.auth.provider.GoogleUserInfo;
import workout.calendar.domain.auth.provider.KakaoUserInfo;
import workout.calendar.domain.auth.provider.NaverUserInfo;
import workout.calendar.domain.auth.provider.OAuth2UserInfo;
import workout.calendar.domain.entity.User;
import workout.calendar.repository.UserRepository;

import java.util.Map;

@Service
@Getter
public abstract class AbstractOAuth2UserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public User register(OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
        String password = encoder.encode("TwoSharkBaby");
        user.setProviderUser(oAuth2UserInfo, password);

        User findUser = userRepository.findByUsername(user.getUsername());

        if (findUser == null){
            userRepository.save(user);
            return user;
        } else {
            return findUser;
        }
    }

    public OAuth2UserInfo providerUser(ClientRegistration clientRegistration, org.springframework.security.oauth2.core.user.OAuth2User oAuth2User) {

        String registrationId = clientRegistration.getRegistrationId();
        if (registrationId.equals("google")) {
            return new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (registrationId.equals("naver")) {
            return new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        } else if (registrationId.equals("kakao")) {
            return new KakaoUserInfo((Map)oAuth2User.getAttributes());
        }
        return null;
    }
}
