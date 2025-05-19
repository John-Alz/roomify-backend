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
//			RoleEntity roleAdmin = RoleEntity.builder()
//					.roleEnum(RoleEnum.ADMIN)
////					.permissionsList(Set.of(createPermission,readPermission,updatePermission,deletePermission))
//					.build();
//			RoleEntity roleReceptionist = RoleEntity.builder()
//					.roleEnum(RoleEnum.RECEPTIONIST)
////					.permissionsList(Set.of(createPermission,readPermission,updatePermission))
//					.build();
//			RoleEntity roleCustomer= RoleEntity.builder()
//					.roleEnum(RoleEnum.CUSTOMER)
////					.permissionsList(Set.of(readPermission))
//					.build();
//			/*create users*/
//			UserEntity userJohn = UserEntity.builder()
//					.username("John")
//					.email("john@gmail.com")
//					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
//					.isEnabled(true)
//					.accountNoExpired(true)
//					.accountNoLocked(true)
//					.credentialNoExpired(true)
//					.roles(Set.of(roleAdmin))
//					.build();
//			UserEntity userAngel = UserEntity.builder()
//					.username("Angel")
//					.email("angel@gmail.com")
//					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
//					.isEnabled(true)
//					.accountNoExpired(true)
//					.accountNoLocked(true)
//					.credentialNoExpired(true)
//					.roles(Set.of(roleReceptionist))
//					.build();
//			UserEntity userPedro = UserEntity.builder()
//					.username("Pedro")
//					.email("pedro@gmail.com")
//					.password("$2a$10$ijSt4zfkR7Gth3hY8huOl.eO.LARyYhiAfRpwtC5pWFord.65oOCa")
//					.isEnabled(true)
//					.accountNoExpired(true)
//					.accountNoLocked(true)
//					.credentialNoExpired(true)
//					.roles(Set.of(roleCustomer))
//					.build();
//
//			userRepository.saveAll(List.of(userJohn, userAngel, userPedro));
//
//			// Amenidades
//			AmenityEntity wifi = AmenityEntity.builder()
//					.name("wifi")
//					.image("https://cdn-icons-png.flaticon.com/128/10322/10322514.png")
//					.description("Conexión a Internet de alta velocidad gratuita.")
//					.build();
//
//			AmenityEntity piscina = AmenityEntity.builder()
//					.name("Piscina")
//					.image("https://cdn-icons-png.flaticon.com/128/10322/10322514.png")
//					.description("Piscina climatizada disponible las 24 horas.")
//					.build();
//
//			AmenityEntity gimnasio = AmenityEntity.builder()
//					.name("Gimnasio")
//					.image("https://cdn-icons-png.flaticon.com/128/10322/10322514.png")
//					.description("Gimnasio completamente equipado con acceso gratuito para huéspedes.")
//					.build();
//
//			AmenityEntity estacionamiento = AmenityEntity.builder()
//					.name("Estacionamiento")
//					.image("https://cdn-icons-png.flaticon.com/128/10322/10322514.png")
//					.description("Estacionamiento privado gratuito para huéspedes.")
//					.build();
//
//			AmenityEntity aireAcondicionado = AmenityEntity.builder()
//					.name("Aire acondicionado")
//					.image("https://cdn-icons-png.flaticon.com/128/10322/10322514.png")
//					.description("Sistema de aire acondicionado en todas las habitaciones.")
//					.build();
//
//			AmenityEntity servicioHabitacion = AmenityEntity.builder()
//					.name("Servicio a la habitación")
//					.image("https://cdn-icons-png.flaticon.com/128/10322/10322514.png")
//					.description("Atención personalizada y servicio de comida a la habitación.")
//					.build();
//
//			AmenityEntity prueba = AmenityEntity.builder()
//					.name("Servicio de prueba")
//					.image("https://cdn-icons-png.flaticon.com/128/10322/10322514.png")
//					.description("Atención personalizada y servicio de comida a la habitación.")
//					.build();
//
//			amenityRepository.saveAll(List.of(wifi, piscina, gimnasio, estacionamiento, aireAcondicionado, servicioHabitacion, prueba));
//
//			// Tipos de habitacion.
//
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
//
//
//			// Habitaciones
//
//			RoomEntity room1 = RoomEntity.builder()
//					.room_rumber(101)
//					.room_name("Penthouse de Lujo con Vista Panorámica")
//					.room_description("Este exclusivo penthouse ofrece una experiencia de vida inigualable con amplios espacios, acabados de alta gama y vistas panorámicas impresionantes de la ciudad. Cuenta con una distribución moderna que incluye una sala de estar elegante con ventanales de piso a techo, una cocina gourmet totalmente equipada, y habitaciones espaciosas con baño privado. Además, dispone de terrazas privadas ideales para el entretenimiento, jacuzzi, gimnasio y acceso a amenidades premium del edificio. Perfecto para quienes buscan confort, privacidad y lujo en un solo lugar.")
//					.roomsCount(2)
//					.bathRoomsCount(1)
//					.room_images(List.of("https://images.pexels.com/photos/164595/pexels-photo-164595.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600"))
//					.room_availability(RoomStatus.AVAILABLE)
//					.room_capacity(4)
//					.room_price(new BigDecimal("155.999"))
//					.room_type(doble)
//					.amenities(Set.of(wifi, piscina, gimnasio))
//					.build();
//
//			RoomEntity room2 = RoomEntity.builder()
//					.room_rumber(102)
//					.room_name("Habitación Doble con Balcón y Vista al Jardín")
//					.room_description("Nuestra habitación doble es ideal para parejas o amigos que desean compartir una estadía cómoda. Dispone de una cama matrimonial o dos camas individuales, según la preferencia del huésped. La habitación cuenta con un balcón privado con vista al jardín, baño privado con artículos de aseo gratuitos, televisión por cable y minibar. Además, los huéspedes pueden disfrutar de un desayuno buffet variado cada mañana. WiFi gratuito, aire acondicionado y servicio de limpieza diaria incluidos.")
//					.roomsCount(1)
//					.bathRoomsCount(1)
//					.room_images(List.of("https://images.pexels.com/photos/1743231/pexels-photo-1743231.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/7061671/pexels-photo-7061671.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600"))
//					.room_availability(RoomStatus.AVAILABLE)
//					.room_capacity(2)
//					.room_price(new BigDecimal("199.999"))
//					.room_type(penthouse)
//					.amenities(Set.of(wifi, piscina, gimnasio, estacionamiento, aireAcondicionado, servicioHabitacion))
//					.build();
//
//			RoomEntity room3 = RoomEntity.builder()
//					.room_rumber(103)
//					.room_name("Suite Ejecutiva con Hidromasaje y Spa")
//					.room_description("Para quienes buscan una experiencia de lujo, nuestra suite ofrece un espacio amplio y sofisticado con una sala de estar independiente y una bañera de hidromasaje privada. Decorada con un estilo elegante y moderno, la habitación está equipada con una cama king-size, un sofá cómodo, minibar y cafetera. Los huéspedes pueden disfrutar del servicio a la habitación las 24 horas, WiFi de alta velocidad, TV de pantalla plana con contenido premium y acceso al spa del hotel sin costo adicional.")
//					.roomsCount(2)
//					.bathRoomsCount(2)
//					.room_images(List.of("https://images.pexels.com/photos/3201758/pexels-photo-3201758.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/14917460/pexels-photo-14917460.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600"))
//					.room_availability(RoomStatus.AVAILABLE)
//					.room_capacity(4)
//					.room_price(new BigDecimal("69.999"))
//					.room_type(suite)
//					.amenities(Set.of(wifi, piscina, gimnasio, estacionamiento, servicioHabitacion))
//					.build();
//
//			RoomEntity room4 = RoomEntity.builder()
//					.room_rumber(104)
//					.room_name("Suite Familiar Espaciosa con Sala de Estar")
//					.room_description("Pensada para familias o grupos, esta espaciosa habitación cuenta con dos camas dobles y un sofá cama adicional, proporcionando suficiente espacio para todos. Con una decoración cálida y acogedora, la habitación incluye una sala de estar, televisión por cable, aire acondicionado y un baño privado con ducha y artículos de aseo gratuitos. Además, los huéspedes pueden disfrutar de estacionamiento gratuito, piscina climatizada y desayuno buffet incluido en su reserva.")
//					.roomsCount(4)
//					.bathRoomsCount(2)
//					.room_images(List.of("https://images.pexels.com/photos/3688261/pexels-photo-3688261.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/6186810/pexels-photo-6186810.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600"))
//					.room_availability(RoomStatus.AVAILABLE)
//					.room_capacity(8)
//					.room_price(new BigDecimal("269.999"))
//					.room_type(familiar)
//					.amenities(Set.of(wifi, piscina, estacionamiento, servicioHabitacion))
//					.build();
//
//			RoomEntity room5 = RoomEntity.builder()
//					.room_rumber(105)
//					.room_name("Penthouse Panorámico con Terraza y Jacuzzi")
//					.room_description("Ubicado en el último piso del hotel, el penthouse es el epítome del lujo y la exclusividad. Ofrece vistas panorámicas inigualables, una terraza privada con jacuzzi, un elegante salón con sofás de cuero, minibar y servicio de mayordomo personalizado. La habitación cuenta con tres dormitorios con camas king-size, dos baños de mármol con duchas de efecto lluvia y bañera de hidromasaje. Además, los huéspedes tienen acceso exclusivo a la piscina privada y al spa de lujo del hotel.")
//					.roomsCount(3)
//					.bathRoomsCount(2)
//					.room_images(List.of("https://images.pexels.com/photos/6899433/pexels-photo-6899433.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/6580228/pexels-photo-6580228.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600"))
//					.room_availability(RoomStatus.AVAILABLE)
//					.room_capacity(6)
//					.room_price(new BigDecimal("299.999"))
//					.room_type(penthouse)
//					.amenities(Set.of(wifi, piscina, estacionamiento, aireAcondicionado, gimnasio, servicioHabitacion, prueba))
//					.build();
//
//			RoomEntity room6 = RoomEntity.builder()
//					.room_rumber(106)
//					.room_name("Habitación Individual Económica y Funcional")
//					.room_description("Nuestra habitación individual económica es perfecta para quienes buscan una opción accesible sin sacrificar la comodidad. Con un diseño práctico y acogedor, la habitación cuenta con una cama individual, escritorio de trabajo, aire acondicionado y WiFi de alta velocidad. El baño privado incluye artículos de aseo gratuitos y agua caliente. Ideal para viajeros de negocios o estudiantes que necesitan un espacio tranquilo y funcional durante su estancia.")
//					.roomsCount(1)
//					.bathRoomsCount(1)
//					.room_images(List.of("https://images.pexels.com/photos/7746093/pexels-photo-7746093.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/3201735/pexels-photo-3201735.jpeg?auto=compress&cs=tinysrgb&w=600", "https://images.pexels.com/photos/271624/pexels-photo-271624.jpeg?auto=compress&cs=tinysrgb&w=600"))
//					.room_availability(RoomStatus.AVAILABLE)
//					.room_capacity(1)
//					.room_price(new BigDecimal("89.999"))
//					.room_type(individual)
//					.amenities(Set.of(wifi, aireAcondicionado))
//					.build();
//
//
//			roomRepository.saveAll(List.of(room1, room2, room3, room4, room5, room6));
		};
	}

}
