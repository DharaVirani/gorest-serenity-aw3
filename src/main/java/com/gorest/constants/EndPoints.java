package com.gorest.constants;


public class EndPoints {

    /**
     * This is Endpoints of gorest api
     */
    public static final String GET_ALL_USERS = "/users";
    public static final String GET_SINGLE_USER_BY_ID = "/users/{userId}";
    public static final String UPDATE_USER_BY_ID = "/users/{userId}";
    public static final String DELETE_USER_BY_ID = "/users/{userId}";

    /**
     * This is Endpoints of Authentication api
     */
    public static final String LOGIN = "/api";
    public static final String LOGOUT = "/close_session";


}
