package com.example.realm.service

import com.example.realm.model.Employee
import io.realm.Realm
import io.realm.RealmResults

class EmployeeService : EmployeeInterface {

    override fun addOrUpdateEmployee(realm: Realm, employee: Employee): Boolean {
        return try {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(employee)
            realm.commitTransaction()
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun getEmployee(realm: Realm, employeeId: Long): Employee =
        realm.where(Employee::class.java).equalTo("_ID", employeeId).findFirst()!!

    override fun deleteEmployee(realm: Realm, employee: Employee): Boolean {
        return try {
            realm.beginTransaction()
            employee.deleteFromRealm()
            realm.commitTransaction()
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun removeLastEmployee(realm: Realm) {
        realm.beginTransaction()
        getLastEmployee(realm).deleteFromRealm()
        realm.commitTransaction()
    }

    override fun getEmployees(realm: Realm): RealmResults<Employee> =
        realm.where(Employee::class.java).findAll()

    fun getNextPrimaryKey(realm: Realm): Long {
        val number: Number? = realm.where(Employee::class.java).max("_ID")
        var nextKey: Long = 1
        if (number != null) {
            nextKey = number.toLong() + 1
        }
        return nextKey
    }

    fun getLastEmployee(realm: Realm): Employee =
        realm.where(Employee::class.java).findAll().last()!!
}