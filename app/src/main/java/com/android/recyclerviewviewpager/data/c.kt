package com.android.recyclerviewviewpager.data

object c {

    //val BASE_URL = "0.0.0.0:8080"
    val BASE_URL = "192.168.1.54:23567"

    //UserAPI
    val REGISTER_USER = "register" // регистрация пользолвателя
    val LOGIN_USER = "user/login/"   // залогинить пользователя
    val SAVE_F_TOKEN = "user/savetoken/" // сохранить Firabase token пользователя
    val REGISTER_WORKER = "service/worker" // регистрация работника

    val TABLE = "/asd" // получить расписание
    val GET_ALL_DEVICE_IN_SERVICE = "service/device/get" // добавление устройства


    // LessonAPI
    val GET_LESSON_LIST = "data/lessons/list"  // получить список доступных разделов
    val GET_LESSON = "data/lessons/"  // получить упражнения выбранного раздела

    //Test connection
    val GET_PODUCTS = "products"
    //    val BASE_URL = "URL"
//
//
//    //UserAPI
//    val REGISTER_USER = "user/register/" // регистрация пользолвателя
//    val LOGIN_USER = "user/login/"   // залогинить пользователя
    val LOGIN_USER_BY_PHONE = "user/loginByPhone/"   // залогинить пользователя c использованием телефона и FCM token
//    val SAVE_F_TOKEN = "user/savetoken/" // сохранить Firabase token пользователя
//
//    // LessonAPI
//    val GET_LESSON_LIST = "data/lessons/list"  // получить список доступных разделов
//    val GET_LESSON = "data/lessons/"  // получить упражнения выбранного раздела
//
//    //Test connection
//    val GET_PODUCTS = "products/"

}