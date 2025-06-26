package com.roomify.roomifybackend;

import com.roomify.roomifybackend.persistence.entity.*;
import com.roomify.roomifybackend.persistence.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class RoomifybackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomifybackendApplication.class, args);
	}

	@Bean
	CommandLineRunner init(
			RoleRepository roleRepository,
			UserRepository userRepository,
			RoomRepository roomRepository,
			AmenityRepository amenityRepository,
			RoomTypeRepository roomTypeRepository,
			BookingRepository bookingRepository,
			PaymentRepository paymentRepository
	) {
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
					.roleEnum(RoleEnum.ADMINISTRADOR)
					.build();
			RoleEntity roleReceptionist = RoleEntity.builder()
					.roleEnum(RoleEnum.RECEPCIONISTA)
					.build();
			RoleEntity roleCustomer= RoleEntity.builder()
					.roleEnum(RoleEnum.CLIENTE)
					.build();

			roleRepository.saveAll(List.of(roleAdmin, roleReceptionist, roleCustomer));

			/*create users*/
			UserEntity userJohn = UserEntity.builder()
					.username("John")
					.lastName("Angel")
					.email("jairoanngelll@gmail.com")
					.phoneNumber("3156783542")
					.birthday(LocalDate.of(2003, 07, 26))
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.role(roleAdmin)
					.build();
			UserEntity userAngel = UserEntity.builder()
					.username("Angel")
					.lastName("Perez")
					.email("angel@gmail.com")
					.phoneNumber("322567532")
					.birthday(LocalDate.of(2003, 07, 26))
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.role(roleReceptionist)
					.build();
			UserEntity userPedro = UserEntity.builder()
					.username("Pedro")
					.lastName("Lopez")
					.email("pedro@gmail.com")
					.phoneNumber("3236452398")
					.birthday(LocalDate.of(2003, 07, 26))
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.role(roleCustomer)
					.build();

			UserEntity userMaria = UserEntity.builder()
					.username("Maria")
					.lastName("Gomez")
					.email("maria@gmail.com")
					.phoneNumber("3104567890")
					.birthday(LocalDate.of(1998, 3, 15))
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.role(roleCustomer)
					.build();

			UserEntity userCarlos = UserEntity.builder()
					.username("Carlos")
					.lastName("Ramirez")
					.email("carlos@gmail.com")
					.phoneNumber("3112345678")
					.birthday(LocalDate.of(1995, 11, 5))
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.role(roleCustomer)
					.build();

			UserEntity userLaura = UserEntity.builder()
					.username("Laura")
					.lastName("Martinez")
					.email("laura@gmail.com")
					.phoneNumber("3123456789")
					.birthday(LocalDate.of(2000, 7, 9))
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.role(roleCustomer)
					.build();

			UserEntity userAndres = UserEntity.builder()
					.username("Andres")
					.lastName("Torres")
					.email("andres@gmail.com")
					.phoneNumber("3139876543")
					.birthday(LocalDate.of(1993, 12, 2))
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.role(roleCustomer)
					.build();

			UserEntity userCamila = UserEntity.builder()
					.username("Camila")
					.lastName("Diaz")
					.email("camila@gmail.com")
					.phoneNumber("3148765432")
					.birthday(LocalDate.of(1999, 5, 20))
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.role(roleCustomer)
					.build();

			UserEntity userEmily = UserEntity.builder()
					.username("Emily")
					.lastName("Padilla")
					.email("emily@gmail.com")
					.phoneNumber("31385463324")
					.birthday(LocalDate.of(2005, 9, 24))
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.role(roleCustomer)
					.build();

			UserEntity userSmith = UserEntity.builder()
					.username("Smith")
					.lastName("Useche")
					.email("johannuseche2020@gmail.com")
					.phoneNumber("31385463324")
					.birthday(LocalDate.of(2001, 10, 24))
					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.role(roleCustomer)
					.build();

			userRepository.saveAll(List.of(userJohn, userAngel, userPedro, userMaria, userCarlos, userLaura, userAndres, userCamila, userEmily, userSmith));

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


			// Habitaciones

			RoomTypeEntity roomType1 = RoomTypeEntity.builder()
					.name("Habitacion estandar sencilla")
					.description("Confort y funcionalidad en un espacio acogedor. Equipada con una cama individual, baño privado, aire acondicionado, Wi-Fi y TV por cable. Ideal para viajeros que buscan una estancia cómoda a buen precio.")
					.beds(1)
					.bathRooms(1)
					.meters(30)
					.images(List.of("https://images.pexels.com/photos/164595/pexels-photo-164595.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600"))
					.quantity_available(10)
					.capacity(2)
					.price(new BigDecimal("55999"))
					.amenities(Set.of(wifi, piscina, gimnasio, servicioHabitacion))
					.tags(List.of("Decoración moderna", "Popular"))
					.build();

			RoomTypeEntity roomType2 = RoomTypeEntity.builder()
					.name("Habitacion estandar doble")
					.description("Cómoda y funcional, perfecta para dos personas. Dispone de una cama doble o dos camas individuales, baño privado, aire acondicionado, Wi-Fi y TV por cable. Ideal para parejas o compañeros de viaje.")
					.beds(2)
					.bathRooms(1)
					.meters(29)
					.images(List.of("https://images.unsplash.com/photo-1568495248636-6432b97bd949?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600"))
					.quantity_available(5)
					.capacity(4)
					.price(new BigDecimal("169999"))
					.amenities(Set.of( piscina, gimnasio))
					.tags(List.of("Elección del mes", "¡Reserva ya!"))
					.build();

			RoomTypeEntity roomType3 = RoomTypeEntity.builder()
					.name("Habitacion familiar")
					.description("Espaciosa y cómoda, ideal para toda la familia. Cuenta con cama doble, camas individuales, baño privado, aire acondicionado, Wi-Fi, TV por cable y minibar. Perfecta para descansar y disfrutar juntos.")
					.beds(4)
					.bathRooms(2)
					.meters(35)
					.images(List.of("https://images.unsplash.com/photo-1549638441-b787d2e11f14?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600"))
					.quantity_available(10)
					.capacity(8)
					.price(new BigDecimal("345999"))
					.amenities(Set.of(wifi, piscina, gimnasio, estacionamiento, aireAcondicionado))
					.tags(List.of("Recomendada", "Popular"))
					.build();

			RoomTypeEntity roomType4 = RoomTypeEntity.builder()
					.name("Habitacion suite")
					.description("Este exclusivo penthouse ofrece una experiencia de vida inigualable con amplios espacios, acabados de alta gama y vistas panorámicas impresionantes de la ciudad. Cuenta con una distribución moderna que incluye una sala de estar elegante con ventanales de piso a techo, una cocina gourmet totalmente equipada, y habitaciones espaciosas con baño privado. Además, dispone de terrazas privadas ideales para el entretenimiento, jacuzzi, gimnasio y acceso a amenidades premium del edificio. Perfecto para quienes buscan confort, privacidad y lujo en un solo lugar.")
					.beds(2)
					.bathRooms(1)
					.meters(35)
					.images(List.of("https://images.unsplash.com/photo-1600077625345-f401f4ba2fde?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600"))
					.quantity_available(10)
					.capacity(4)
					.price(new BigDecimal("155999"))
					.amenities(Set.of(wifi, piscina, gimnasio))
					.tags(List.of("Hot sale", "Popular"))
					.build();


			roomTypeRepository.saveAll(List.of(roomType1, roomType2, roomType3, roomType4));

			// RESERVAS.



			BookingEntity booking1 = BookingEntity.builder()
					.clientId(userPedro)
					.reservationNumber("RES-KCJ8A")
					.name(userPedro.getUsername())
					.lastName(userPedro.getLastName())
					.email(userPedro.getEmail())
					.phoneNumber(userPedro.getPhoneNumber())
					.roomType(roomType1)
					.bookingDate(LocalDateTime.now())
					.status(BookingStatus.CONFIRMADA)
					.checkInDate(LocalDate.of(2025, 06, 20))
					.checkOutDate(LocalDate.of(2025, 06, 24))
					.totalPrice(BigDecimal.valueOf(467992))
					.numberOfRoom(2)
					.build();

			BookingEntity booking2 = BookingEntity.builder()
					.clientId(userMaria)
					.reservationNumber("RES-M4TXP")
					.name(userMaria.getUsername())
					.lastName(userMaria.getLastName())
					.email(userMaria.getEmail())
					.phoneNumber(userMaria.getPhoneNumber())
					.roomType(roomType2)
					.bookingDate(LocalDateTime.now())
					.status(BookingStatus.CONFIRMADA)
					.checkInDate(LocalDate.of(2025, 06, 18))
					.checkOutDate(LocalDate.of(2025, 06, 22))
					.totalPrice(BigDecimal.valueOf(243996))
					.numberOfRoom(1)
					.build();

			BookingEntity booking3 = BookingEntity.builder()
					.clientId(userCarlos)
					.reservationNumber("RES-WQ7L9")
					.name(userCarlos.getUsername())
					.lastName(userCarlos.getLastName())
					.email(userCarlos.getEmail())
					.phoneNumber(userCarlos.getPhoneNumber())
					.roomType(roomType1)
					.bookingDate(LocalDateTime.now())
					.status(BookingStatus.CONFIRMADA)
					.checkInDate(LocalDate.of(2025, 07, 05))
					.checkOutDate(LocalDate.of(2025, 07, 07))
					.totalPrice(BigDecimal.valueOf(355994))
					.numberOfRoom(3)
					.build();

			BookingEntity booking4 = BookingEntity.builder()
					.clientId(userLaura)
					.reservationNumber("RES-Y1BQM")
					.name(userLaura.getUsername())
					.lastName(userLaura.getLastName())
					.email(userLaura.getEmail())
					.phoneNumber(userLaura.getPhoneNumber())
					.roomType(roomType3)
					.bookingDate(LocalDateTime.now())
					.status(BookingStatus.CONFIRMADA)
					.checkInDate(LocalDate.of(2025, 6, 25))
					.checkOutDate(LocalDate.of(2025, 6, 28))
					.totalPrice(BigDecimal.valueOf(1057997))
					.numberOfRoom(1)
					.build();

			BookingEntity booking5 = BookingEntity.builder()
					.clientId(userAndres)
					.reservationNumber("RES-A56XA")
					.name(userAndres.getUsername())
					.lastName(userAndres.getLastName())
					.email(userAndres.getEmail())
					.phoneNumber(userAndres.getPhoneNumber())
					.roomType(roomType4)
					.bookingDate(LocalDateTime.now())
					.status(BookingStatus.CONFIRMADA)
					.checkInDate(LocalDate.of(2025, 7, 10))
					.checkOutDate(LocalDate.of(2025, 7, 13))
					.totalPrice(BigDecimal.valueOf(955997))
					.numberOfRoom(2)
					.build();

			BookingEntity booking6 = BookingEntity.builder()
					.clientId(userCamila)
					.reservationNumber("RES-8ZKJ3")
					.name(userCamila.getUsername())
					.lastName(userCamila.getLastName())
					.email(userCamila.getEmail())
					.phoneNumber(userCamila.getPhoneNumber())
					.roomType(roomType2)
					.bookingDate(LocalDateTime.now())
					.status(BookingStatus.CONFIRMADA)
					.checkInDate(LocalDate.of(2025, 7, 2))
					.checkOutDate(LocalDate.of(2025, 7, 6))
					.totalPrice(BigDecimal.valueOf(643996))
					.numberOfRoom(1)
					.build();

			bookingRepository.saveAll(List.of(booking1, booking2, booking3, booking4, booking5, booking6));

			//Payments
			String preferenceId1 = "2509326300-" + UUID.randomUUID();
			String preferenceId2 = "2509326300-" + UUID.randomUUID();
			String preferenceId3 = "2509326300-" + UUID.randomUUID();
			String preferenceId4 = "2509326300-" + UUID.randomUUID();
			String preferenceId5 = "2509326300-" + UUID.randomUUID();
			String preferenceId6 = "2509326300-" + UUID.randomUUID();

			PaymentEntity payment1 = PaymentEntity.builder()
					.booking(booking1)
					.preference_id(preferenceId1) // Puedes inventar este valor
					.status(PaymentStatus.APPROVED)
					.amount(booking1.getTotalPrice())
					.created_at(LocalDateTime.now())
					.build();

			PaymentEntity payment2 = PaymentEntity.builder()
					.booking(booking2)
					.preference_id(preferenceId2)
					.status(PaymentStatus.APPROVED)
					.amount(booking2.getTotalPrice())
					.created_at(LocalDateTime.now())
					.build();

			PaymentEntity payment3 = PaymentEntity.builder()
					.booking(booking3)
					.preference_id(preferenceId3)
					.status(PaymentStatus.APPROVED)
					.amount(booking3.getTotalPrice())
					.created_at(LocalDateTime.now())
					.build();

			PaymentEntity payment4 = PaymentEntity.builder()
					.booking(booking4)
					.preference_id(preferenceId4) // Puedes inventar este valor
					.status(PaymentStatus.APPROVED)
					.amount(booking4.getTotalPrice())
					.created_at(LocalDateTime.now())
					.build();

			PaymentEntity payment5 = PaymentEntity.builder()
					.booking(booking5)
					.preference_id(preferenceId5)
					.status(PaymentStatus.APPROVED)
					.amount(booking5.getTotalPrice())
					.created_at(LocalDateTime.now())
					.build();

			PaymentEntity payment6 = PaymentEntity.builder()
					.booking(booking6)
					.preference_id(preferenceId6)
					.status(PaymentStatus.APPROVED)
					.amount(booking6.getTotalPrice())
					.created_at(LocalDateTime.now())
					.build();

			paymentRepository.saveAll(List.of(payment1, payment2, payment3, payment4, payment5, payment6));

			//Rooms

			RoomEntity room101 = RoomEntity.builder()
					.room_number("101")
					.roomType(roomType1)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 10))
					.notes("Habitación cerca al lobby")
					.build();

			RoomEntity room102 = RoomEntity.builder()
					.room_number("102")
					.roomType(roomType1)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 11))
					.notes("Vista al jardín")
					.build();

			RoomEntity room103 = RoomEntity.builder()
					.room_number("103")
					.roomType(roomType1)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 12))
					.notes("Cerca al ascensor")
					.build();

			RoomEntity room104 = RoomEntity.builder()
					.room_number("104")
					.roomType(roomType1)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 13))
					.notes("Decoración moderna")
					.build();

			RoomEntity room105 = RoomEntity.builder()
					.room_number("105")
					.roomType(roomType1)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 14))
					.notes("Cuenta con escritorio")
					.build();

			RoomEntity room106 = RoomEntity.builder()
					.room_number("106")
					.roomType(roomType1)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 15))
					.notes("Cerca a la salida de emergencia")
					.build();

			RoomEntity room107 = RoomEntity.builder()
					.room_number("107")
					.roomType(roomType1)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 16))
					.notes("Ideal para ejecutivos")
					.build();

			RoomEntity room108 = RoomEntity.builder()
					.room_number("108")
					.roomType(roomType1)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 17))
					.notes("Con arte local decorativo")
					.build();

			RoomEntity room109 = RoomEntity.builder()
					.room_number("109")
					.roomType(roomType1)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 18))
					.notes("Tiene terraza pequeña")
					.build();

			RoomEntity room110 = RoomEntity.builder()
					.room_number("110")
					.roomType(roomType1)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 19))
					.notes("Cerca al área de lavandería")
					.build();

//			RoomEntity room111 = RoomEntity.builder()
//					.room_number("111")
//					.roomType(roomType1)
//					.status(RoomStatus.DISPONIBLE)
//					.lastMaintenance(LocalDate.of(2025, 6, 20))
//					.notes("Espaciosa y luminosa")
//					.build();
//
//			RoomEntity room112 = RoomEntity.builder()
//					.room_number("112")
//					.roomType(roomType1)
//					.status(RoomStatus.DISPONIBLE)
//					.lastMaintenance(LocalDate.of(2025, 6, 21))
//					.notes("Ideal para largas estancias")
//					.build();
//
//			RoomEntity room113 = RoomEntity.builder()
//					.room_number("113")
//					.roomType(roomType1)
//					.status(RoomStatus.DISPONIBLE)
//					.lastMaintenance(LocalDate.of(2025, 6, 22))
//					.notes("Decoración clásica")
//					.build();
//
//			RoomEntity room114 = RoomEntity.builder()
//					.room_number("114")
//					.roomType(roomType1)
//					.status(RoomStatus.DISPONIBLE)
//					.lastMaintenance(LocalDate.of(2025, 6, 23))
//					.notes("Con minibar")
//					.build();
//
//			RoomEntity room115 = RoomEntity.builder()
//					.room_number("115")
//					.roomType(roomType1)
//					.status(RoomStatus.DISPONIBLE)
//					.lastMaintenance(LocalDate.of(2025, 6, 24))
//					.notes("Vista a la ciudad")
//					.build();
//
//			RoomEntity room116 = RoomEntity.builder()
//					.room_number("116")
//					.roomType(roomType1)
//					.status(RoomStatus.DISPONIBLE)
//					.lastMaintenance(LocalDate.of(2025, 6, 25))
//					.notes("Incluye sofá cama")
//					.build();
//
//			RoomEntity room117 = RoomEntity.builder()
//					.room_number("117")
//					.roomType(roomType1)
//					.status(RoomStatus.DISPONIBLE)
//					.lastMaintenance(LocalDate.of(2025, 6, 26))
//					.notes("Habitación esquinera")
//					.build();
//
//			RoomEntity room118 = RoomEntity.builder()
//					.room_number("118")
//					.roomType(roomType1)
//					.status(RoomStatus.DISPONIBLE)
//					.lastMaintenance(LocalDate.of(2025, 6, 27))
//					.notes("Piso antialérgico")
//					.build();
//
//			RoomEntity room119 = RoomEntity.builder()
//					.room_number("119")
//					.roomType(roomType1)
//					.status(RoomStatus.DISPONIBLE)
//					.lastMaintenance(LocalDate.of(2025, 6, 28))
//					.notes("Diseño renovado")
//					.build();
//
//			RoomEntity room120 = RoomEntity.builder()
//					.room_number("120")
//					.roomType(roomType1)
//					.status(RoomStatus.DISPONIBLE)
//					.lastMaintenance(LocalDate.of(2025, 6, 29))
//					.notes("Cerca a recepción")
//					.build();

			RoomEntity room201 = RoomEntity.builder()
					.room_number("201")
					.roomType(roomType2)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 10))
					.notes("Vista a la piscina")
					.build();

			RoomEntity room202 = RoomEntity.builder()
					.room_number("202")
					.roomType(roomType2)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 8))
					.notes("Revisión de aire acondicionado reciente")
					.build();

			RoomEntity room203 = RoomEntity.builder()
					.room_number("203")
					.roomType(roomType2)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 6))
					.notes("Cerca al ascensor")
					.build();

			RoomEntity room204 = RoomEntity.builder()
					.room_number("204")
					.roomType(roomType2)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 4))
					.notes("Sillas recién cambiadas")
					.build();

			RoomEntity room205 = RoomEntity.builder()
					.room_number("205")
					.roomType(roomType2)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 2))
					.notes("Alfombra nueva instalada")
					.build();

			RoomEntity room301 = RoomEntity.builder()
					.room_number("301")
					.roomType(roomType3)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 1))
					.notes("Vista al jardín")
					.build();

			RoomEntity room302 = RoomEntity.builder()
					.room_number("302")
					.roomType(roomType3)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 3))
					.notes("Baño remodelado recientemente")
					.build();

			RoomEntity room303 = RoomEntity.builder()
					.room_number("303")
					.roomType(roomType3)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 5))
					.notes("Cerca de la salida de emergencia")
					.build();

			RoomEntity room304 = RoomEntity.builder()
					.room_number("304")
					.roomType(roomType3)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 7))
					.notes("Colchón ortopédico nuevo")
					.build();

			RoomEntity room305 = RoomEntity.builder()
					.room_number("305")
					.roomType(roomType3)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 9))
					.notes("Decoración temática minimalista")
					.build();

			RoomEntity room306 = RoomEntity.builder()
					.room_number("306")
					.roomType(roomType3)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 11))
					.notes("Pintura de pared actualizada")
					.build();

			RoomEntity room307 = RoomEntity.builder()
					.room_number("307")
					.roomType(roomType3)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 13))
					.notes("Cambio de lámparas LED")
					.build();

			RoomEntity room308 = RoomEntity.builder()
					.room_number("308")
					.roomType(roomType3)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 14))
					.notes("Control de plagas realizado")
					.build();

			RoomEntity room309 = RoomEntity.builder()
					.room_number("309")
					.roomType(roomType3)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 15))
					.notes("Revisión eléctrica completa")
					.build();

			RoomEntity room310 = RoomEntity.builder()
					.room_number("310")
					.roomType(roomType3)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 16))
					.notes("Nuevo juego de sábanas y cobijas")
					.build();

			RoomEntity room401 = RoomEntity.builder()
					.room_number("401")
					.roomType(roomType4)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 1))
					.notes("Habitación premium con balcón")
					.build();

			RoomEntity room402 = RoomEntity.builder()
					.room_number("402")
					.roomType(roomType4)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 2))
					.notes("Incluye escritorio ejecutivo")
					.build();

			RoomEntity room403 = RoomEntity.builder()
					.room_number("403")
					.roomType(roomType4)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 3))
					.notes("Vista al mar")
					.build();

			RoomEntity room404 = RoomEntity.builder()
					.room_number("404")
					.roomType(roomType4)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 4))
					.notes("Revisión de aire acondicionado")
					.build();

			RoomEntity room405 = RoomEntity.builder()
					.room_number("405")
					.roomType(roomType4)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 5))
					.notes("Baño con tina de hidromasaje")
					.build();

			RoomEntity room406 = RoomEntity.builder()
					.room_number("406")
					.roomType(roomType4)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 6))
					.notes("Recién pintada y decorada")
					.build();

			RoomEntity room407 = RoomEntity.builder()
					.room_number("407")
					.roomType(roomType4)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 7))
					.notes("Iluminación LED inteligente")
					.build();

			RoomEntity room408 = RoomEntity.builder()
					.room_number("408")
					.roomType(roomType4)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 8))
					.notes("TV de 55 pulgadas con Netflix")
					.build();

			RoomEntity room409 = RoomEntity.builder()
					.room_number("409")
					.roomType(roomType4)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 9))
					.notes("Habitación insonorizada")
					.build();

			RoomEntity room410 = RoomEntity.builder()
					.room_number("410")
					.roomType(roomType4)
					.status(RoomStatus.DISPONIBLE)
					.lastMaintenance(LocalDate.of(2025, 6, 10))
					.notes("Mini bar incluido")
					.build();


// Guardar todas
			roomRepository.saveAll(List.of(
					room101, room102, room103, room104, room105,
					room106, room107, room108, room109, room110,
//					room111, room112, room113, room114, room115,
//					room116, room117, room118, room119, room120,
					room201, room202, room203, room204, room205,
					room301, room302, room303, room304, room305,
					room306, room307, room308, room309, room310,
					room401, room402, room403, room404, room405,
					room406, room407, room408, room409, room410
			));


		};
	}

}
