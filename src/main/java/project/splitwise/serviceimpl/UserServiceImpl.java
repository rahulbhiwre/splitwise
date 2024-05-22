package project.splitwise.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.splitwise.dto.UserDTO;
import project.splitwise.entity.UserEntity;
import project.splitwise.entity.UserTotalAmountEntity;
import project.splitwise.repository.IAccountRepository;
import project.splitwise.repository.IUserRepository;
import project.splitwise.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private IUserRepository iUserRepository;

	@Autowired
	private IAccountRepository iAccountRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO addUser(UserDTO user) {
		log.info("saving new user ");
		log.info(user.toString());
		UserEntity newUser = this.modelMapper.map(user, UserEntity.class);
		log.info("newUser..");
		log.info(newUser.toString());
		UserDTO userr = this.modelMapper.map(this.iUserRepository.save(newUser), UserDTO.class);
		createNewAccount(newUser);
		return userr;
	}

	private void createNewAccount(UserEntity user) {
		UserTotalAmountEntity userAccount = new UserTotalAmountEntity();
		userAccount.setAmount(0.0);
		userAccount.setUserId(user);
		iAccountRepository.save(userAccount);
	}

	@Override
	public List<UserDTO> getUsers() {
		List<UserEntity> users = (List<UserEntity>) iUserRepository.findAll();
		List<UserDTO> userDTOs = users.stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
		return userDTOs;
	}

	@Override
	public UserDTO getUser(Long userId) {
		UserDTO user = modelMapper.map(iUserRepository.findById(userId), UserDTO.class);
		log.info("user details ", user.toString());
		return user;
	}

	public Double getTotalExpense(Long userid) {
		Optional<UserEntity> user = iUserRepository.findById(userid);
		return iAccountRepository.getByUserId(user.get()).getAmount();
	}

}
