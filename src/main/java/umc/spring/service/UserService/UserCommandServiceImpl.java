package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.PreferCategoryHandler;
import umc.spring.converter.UserConverter;
import umc.spring.domain.Address;
import umc.spring.domain.PreferCategory;
import umc.spring.domain.Users;
import umc.spring.domain.mapping.Prefer;
import umc.spring.repository.AddressRepository.AddressRepository;
import umc.spring.repository.PreferCategoryRepository.PreferCategoryRepository;
import umc.spring.repository.UsersRepository.UsersRepository;
import umc.spring.web.dto.users.UserRequestDTO;

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
    @Override
    @Transactional
    public Users joinUser(UserRequestDTO.JoinDto request){
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
}
