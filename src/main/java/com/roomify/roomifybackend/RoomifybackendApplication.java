package com.roomify.roomifybackend;

import com.roomify.roomifybackend.persistence.entity.*;
import com.roomify.roomifybackend.persistence.repository.AmenityRepository;
import com.roomify.roomifybackend.persistence.repository.RoomRepository;
import com.roomify.roomifybackend.persistence.repository.RoomTypeRepository;
import com.roomify.roomifybackend.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class RoomifybackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomifybackendApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, RoomRepository roomRepository, AmenityRepository amenityRepository, RoomTypeRepository roomTypeRepository) {
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
					.lastName("Angel")
					.email("john@gmail.com")
					.birthday(LocalDate.of(2003, 07, 26))
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();
			UserEntity userAngel = UserEntity.builder()
					.username("Angel")
					.lastName("Perez")
					.email("angel@gmail.com")
					.birthday(LocalDate.of(2003, 07, 26))
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleReceptionist))
					.build();
			UserEntity userPedro = UserEntity.builder()
					.username("Pedro")
					.lastName("Lopez")
					.email("pedro@gmail.com")
					.birthday(LocalDate.of(2003, 07, 26))
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleCustomer))
					.build();

			userRepository.saveAll(List.of(userJohn, userAngel, userPedro));

			// Amenidades
			AmenityEntity wifi = AmenityEntity.builder()
					.name("Wifi")
					.image("https://cdn-icons-png.flaticon.com/128/10322/10322514.png")
					.description("Conexión a Internet de alta velocidad gratuita.")
					.build();

			AmenityEntity piscina = AmenityEntity.builder()
					.name("Piscina")
					.image("https://cdn-icons-png.flaticon.com/128/15323/15323598.png")
					.description("Piscina climatizada disponible las 24 horas.")
					.build();

			AmenityEntity gimnasio = AmenityEntity.builder()
					.name("Gimnasio")
					.image("https://cdn-icons-png.flaticon.com/128/9312/9312789.png")
					.description("Gimnasio completamente equipado con acceso gratuito para huéspedes.")
					.build();

			AmenityEntity estacionamiento = AmenityEntity.builder()
					.name("Estacionamiento")
					.image("https://cdn-icons-png.flaticon.com/128/7612/7612847.png")
					.description("Estacionamiento privado gratuito para huéspedes.")
					.build();

			AmenityEntity aireAcondicionado = AmenityEntity.builder()
					.name("Aire acondicionado")
					.image("https://cdn-icons-png.flaticon.com/128/10322/10322514.png")
					.description("Sistema de aire acondicionado en todas las habitaciones.")
					.build();

			AmenityEntity servicioHabitacion = AmenityEntity.builder()
					.name("Servicio a la habitación")
					.image("https://cdn-icons-png.flaticon.com/128/4880/4880405.png")
					.description("Atención personalizada y servicio de comida a la habitación.")
					.build();

			amenityRepository.saveAll(List.of(wifi, piscina, gimnasio, estacionamiento, aireAcondicionado, servicioHabitacion));

			// Tipos de habitacion.

//			RoomTypeEntity individual = RoomTypeEntity.builder()
//					.name("Individual")
//					.description("Habitación para una persona con comodidades básicas.")
//					.build();
//
//			RoomTypeEntity doble = RoomTypeEntity.builder()
//					.name("Doble")
//					.description("Habitación con capacidad para dos personas, con una cama matrimonial o dos individuales.")
//					.build();
//
//			RoomTypeEntity suite = RoomTypeEntity.builder()
//					.name("Suite")
//					.description("Habitación de lujo con áreas adicionales como sala de estar y jacuzzi.")
//					.build();
//
//			RoomTypeEntity familiar = RoomTypeEntity.builder()
//					.name("Familiar")
//					.description("Habitación espaciosa para familias con múltiples camas y servicios adicionales.")
//					.build();
//
//			RoomTypeEntity penthouse = RoomTypeEntity.builder()
//					.name("Penthouse")
//					.description("La mejor habitación del hotel con vistas panorámicas y servicios premium.")
//					.build();
//
//			roomTypeRepository.saveAll(List.of(individual, doble, suite, familiar, penthouse ));


			// Habitaciones

			RoomTypeEntity room1 = RoomTypeEntity.builder()
					.name("Habitacion estandar sencilla")
					.description("Confort y funcionalidad en un espacio acogedor. Equipada con una cama individual, baño privado, aire acondicionado, Wi-Fi y TV por cable. Ideal para viajeros que buscan una estancia cómoda a buen precio.")
					.beds(1)
					.bathRooms(1)
					.meters(30)
					.images(List.of("https://images.pexels.com/photos/164595/pexels-photo-164595.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600"))
					.quantity_available(20L)
					.capacity(2)
					.price(new BigDecimal("55.999"))
					.amenities(Set.of(wifi, piscina, gimnasio, servicioHabitacion))
					.build();

			RoomTypeEntity room2 = RoomTypeEntity.builder()
					.name("Habitacion estandar doble")
					.description("Cómoda y funcional, perfecta para dos personas. Dispone de una cama doble o dos camas individuales, baño privado, aire acondicionado, Wi-Fi y TV por cable. Ideal para parejas o compañeros de viaje.")
					.beds(2)
					.bathRooms(1)
					.meters(29)
					.images(List.of("https://images.unsplash.com/photo-1568495248636-6432b97bd949?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600"))
					.quantity_available(20L)
					.capacity(4)
					.price(new BigDecimal("169.999"))
					.amenities(Set.of( piscina, gimnasio))
					.build();

			RoomTypeEntity room3 = RoomTypeEntity.builder()
					.name("Habitacion familiar")
					.description("Espaciosa y cómoda, ideal para toda la familia. Cuenta con cama doble, camas individuales, baño privado, aire acondicionado, Wi-Fi, TV por cable y minibar. Perfecta para descansar y disfrutar juntos.")
					.beds(4)
					.bathRooms(2)
					.meters(35)
					.images(List.of("https://images.unsplash.com/photo-1549638441-b787d2e11f14?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600"))
					.quantity_available(10L)
					.capacity(8)
					.price(new BigDecimal("345.999"))
					.amenities(Set.of(wifi, piscina, gimnasio, estacionamiento, aireAcondicionado))
					.build();

			RoomTypeEntity room4 = RoomTypeEntity.builder()
					.name("Habitacion suite")
					.description("Este exclusivo penthouse ofrece una experiencia de vida inigualable con amplios espacios, acabados de alta gama y vistas panorámicas impresionantes de la ciudad. Cuenta con una distribución moderna que incluye una sala de estar elegante con ventanales de piso a techo, una cocina gourmet totalmente equipada, y habitaciones espaciosas con baño privado. Además, dispone de terrazas privadas ideales para el entretenimiento, jacuzzi, gimnasio y acceso a amenidades premium del edificio. Perfecto para quienes buscan confort, privacidad y lujo en un solo lugar.")
					.beds(2)
					.bathRooms(1)
					.meters(35)
					.images(List.of("https://images.unsplash.com/photo-1600077625345-f401f4ba2fde?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600"))
					.quantity_available(20L)
					.capacity(4)
					.price(new BigDecimal("155.999"))
					.amenities(Set.of(wifi, piscina, gimnasio))
					.build();


			roomTypeRepository.saveAll(List.of(room1, room2, room3, room4));
		};
	}

}
