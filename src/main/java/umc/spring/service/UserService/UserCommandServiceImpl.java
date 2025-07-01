package umc.spring.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.PreferCategoryHandler;
import umc.spring.config.security.jwt.JwtTokenProvider;
import umc.spring.converter.UserConverter;
import umc.spring.domain.Address;
import umc.spring.domain.PreferCategory;
import umc.spring.domain.Users;
import umc.spring.domain.mapping.Prefer;
import umc.spring.repository.AddressRepository.AddressRepository;
import umc.spring.repository.PreferCategoryRepository.PreferCategoryRepository;
import umc.spring.repository.UsersRepository.UsersRepository;
import umc.spring.web.dto.users.UserRequestDTO;
import umc.spring.web.dto.users.UserResponseDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCommandServiceImpl implements UserCommandService {
    private final UsersRepository usersRepository;
    private final PreferCategoryRepository preferCategoryRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public Users joinUser(UserRequestDTO.JoinDto request){
        //이미 가입된 이메일인지 확인
        if (usersRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new MemberHandler(ErrorStatus.MEMBER_ALREADY_EXIST);
        }
        Users newUser = UserConverter.toUsers(request);
        newUser.encodePassword(passwordEncoder.encode(newUser.getPassword()));
        //주소 연결
        Address address = UserConverter.toAddress(request);
        addressRepository.save(address);
        newUser.setAddress(address);
        //선호카테고리 연결 =>양방향
        List<PreferCategory> preferCategoryList = request.getPreferCategory().stream()
                .map(categoryId -> preferCategoryRepository.findById(categoryId)
                        .orElseThrow(() -> new PreferCategoryHandler(ErrorStatus.Prefer_CATEGORY_NOT_FOUND)))
                .collect(Collectors.toList());
        //선호카테고리 연결
        List<Prefer> preferList = UserConverter.toPreferList(preferCategoryList);
        preferList.forEach(prefer -> {prefer.setUsers(newUser);});
        newUser.getPrefers().addAll(preferList);
        Users savedUser = usersRepository.save(newUser);
        return savedUser;
    }

    public UserResponseDTO.LoginResultDTO loginUser(UserRequestDTO.LoginRequestDTO request){
        Users users = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        if(!passwordEncoder.matches(request.getPassword(), users.getPassword())) {
            throw new MemberHandler(ErrorStatus.INVALID_PASSWORD);
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                users.getEmail(), null,
                Collections.singleton(() -> users.getRole().name()));
        String accessToken = jwtTokenProvider.generateToken(authentication);
        return UserConverter.toLoginResultDTO(
                users.getUserId(),
                accessToken
        );
    }

    public UserResponseDTO.UserResultDTO getUserInfo(HttpServletRequest request){
        Authentication authentication = jwtTokenProvider.extractAuthentication(request);
        String email = authentication.getName();
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return UserConverter.toUserResultDTO(user);
    }
}
