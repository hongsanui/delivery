package com.week5.delivery.service;

import com.week5.delivery.domain.User;
import com.week5.delivery.domain.UserRoleEnum;
import com.week5.delivery.dto.UserDto;
import com.week5.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    private static final String OWNER_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
    //회원가입 signUp
    //음식점 추가하는 메소드
    @Transactional
    public User signUp(UserDto userDto) {

        User user = User.builder()
                .userId(userDto.getUserId())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .build();
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void registerUser(UserDto userDto) {
        // 회원 ID 중복 확인
        String userid = userDto.getUserId();
        Optional<User> found = userRepository.findByUserId(userid);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(userDto.getPassword());
        String username = userDto.getUsername();

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (userDto.isOwner()) {
            if (!userDto.getOwnerToken().equals(OWNER_TOKEN)) {
                throw new IllegalArgumentException("음식점 등록 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.OWNER;
        }

        User user = new User(userid, password, username, role);
        userRepository.save(user);
    }

    public List<User> userList(){
        return userRepository.findAll();
    }

}
