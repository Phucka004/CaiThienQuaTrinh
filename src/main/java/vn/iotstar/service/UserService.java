package vn.iotstar.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import vn.iotstar.entity.UserInfo;
import vn.iotstar.repository.UserInfoRepository;

public record UserService(UserInfoRepository repository, PasswordEncoder passwordEncoder) {
	public String addUser(UserInfo userInfo)
	{
		userInfo.setPassword(passwordEncoder.encode(UserInfo.getPassword()));
		repository.save(userInfo);
		return "Thêm user thành công!";
	}
}
