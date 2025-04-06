package com.roomify.roomifybackend;

import com.roomify.roomifybackend.persistence.entity.RoleEntity;
import com.roomify.roomifybackend.persistence.entity.RoleEnum;
import com.roomify.roomifybackend.persistence.entity.UserEntity;
import com.roomify.roomifybackend.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class RoomifybackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomifybackendApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
//			PermissionEntity createPermission = PermissionEntity.builder()
//					.name("CREATE")
//					.build();
//			PermissionEntity readPermission = PermissionEntity.builder()
//					.name("READ")
//					.build();
//			PermissionEntity updatePermission = PermissionEntity.builder()
//					.name("UPDATE")
//					.build();
//			PermissionEntity deletePermission = PermissionEntity.builder()
//					.name("DELETE")
//					.build();

			/*create roles*/
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
//					.permissionsList(Set.of(createPermission,readPermission,updatePermission,deletePermission))
					.build();
			RoleEntity roleReceptionist = RoleEntity.builder()
					.roleEnum(RoleEnum.RECEPTIONIST)
//					.permissionsList(Set.of(createPermission,readPermission,updatePermission))
					.build();
			RoleEntity roleCustomer= RoleEntity.builder()
					.roleEnum(RoleEnum.CUSTOMER)
//					.permissionsList(Set.of(readPermission))
					.build();
			/*create users*/
			UserEntity userJohn = UserEntity.builder()
					.username("John")
					.email("john@gmail.com")
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();
			UserEntity userAngel = UserEntity.builder()
					.username("Angel")
					.email("angel@gmail.com")
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleReceptionist))
					.build();
			UserEntity userPedro = UserEntity.builder()
					.username("Pedro")
					.email("pedro@gmail.com")
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleCustomer))
					.build();

			userRepository.saveAll(List.of(userJohn, userAngel, userPedro));
		};
	}

}
