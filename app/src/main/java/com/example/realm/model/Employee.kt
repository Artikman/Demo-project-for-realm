package com.example.realm.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Employee(
    @PrimaryKey
    open var _ID: Long = 0,
    open var sex: String = "",
    open var firstName: String = "",
    open var lastName: String = ""
) : RealmObject()