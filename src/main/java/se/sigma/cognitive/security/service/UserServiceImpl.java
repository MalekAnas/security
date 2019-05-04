package se.sigma.cognitive.security.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.sigma.cognitive.security.entity.UserEntity;
import se.sigma.cognitive.security.repo.UserRepository;
import se.sigma.cognitive.security.sharedDto.UserDto;
import se.sigma.cognitive.security.sharedDto.Utils;

import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService {



    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;






    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.findUserByEmail(user.getEmail())!= null){
            throw new RuntimeException("record is already exist");
        }

        UserEntity userEntity= new UserEntity();
        BeanUtils.copyProperties(user, userEntity);


        String publicUserID = utils.generateUserId(30);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(publicUserID);

        UserEntity storedUserDts = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDts, userEntity);

        return returnValue;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       UserEntity userEntity =  userRepository.findUserByEmail(email);
       if (userEntity==null){
           throw new UsernameNotFoundException(email);
       }
       return  new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }


    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity==null){
            throw new UsernameNotFoundException(email);
        }


        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserDto getUserByUserId(String id) {


            UserDto returnValue = new UserDto();
            UserEntity userEntity = userRepository.findByUserId(id);
            if (userEntity == null){
                throw new UsernameNotFoundException(id);
            }
            BeanUtils.copyProperties(userEntity,returnValue );

            return returnValue;
        }
    }



