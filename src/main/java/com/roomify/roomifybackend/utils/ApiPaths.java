package com.roomify.roomifybackend.utils;

public class ApiPaths {

    private ApiPaths() {}

    public static final String ROOMS = "/api/v1/rooms";
    public static final String ROOM_BY_ID = "/api/v1/rooms/{roomId}";

    public static final String ROOMS_TYPES = "/api/v1/rooms/types";
    public static final String ROOM_TYPE_BY_ID = "/api/v1/rooms/types/{roomId}";

    public static final String AMENITIES = "/api/v1/rooms/amenities";
    public static final String AMENITY_BY_ID = "/api/v1/rooms/amenities/{roomId}";

}
