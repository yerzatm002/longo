package kz.meirambekuly.backendlongo.services;

import kz.meirambekuly.backendlongo.web.dto.ResultDto;
import kz.meirambekuly.backendlongo.web.dto.userDtos.UserCreatorDto;
import kz.meirambekuly.backendlongo.web.dto.userDtos.UserDetailsDto;
import kz.meirambekuly.backendlongo.web.dto.userDtos.UserLoginDto;

import java.util.concurrent.ExecutionException;

public interface UserService {

    ResultDto<?> register(UserCreatorDto dto) throws ExecutionException, InterruptedException;
    ResultDto<?> login(UserLoginDto dto);
    ResultDto<?> getUserInfo();
    ResultDto<?> updateUser(UserDetailsDto dto);
    ResultDto<?> activateUser(String phoneNumber, String code);

}
