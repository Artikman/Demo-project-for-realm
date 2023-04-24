package com.example.realm.service

import com.example.realm.model.Employee
import io.realm.Realm
import io.realm.RealmResults

interface EmployeeInterface {
    fun addOrUpdateEmployee(realm: Realm, employee: Employee): Boolean
    fun getEmployee(realm: Realm, employeeId: Long): Employee
    fun deleteEmployee(realm: Realm, employee: Employee): Boolean
    fun removeLastEmployee(realm: Realm)
    fun getEmployees(realm: Realm): RealmResults<Employee>
}