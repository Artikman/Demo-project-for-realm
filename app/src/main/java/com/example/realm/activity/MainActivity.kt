package com.example.realm.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.realm.databinding.ActivityMainBinding
import com.example.realm.model.Employee
import com.example.realm.service.EmployeeService
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var employeeService: EmployeeService
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            employeeService = EmployeeService()
            realm = Realm.getDefaultInstance()
            val employeeModel = Employee(
                _ID = employeeService.getNextPrimaryKey(realm),
                sex = "Male",
                firstName = "FirstName",
                lastName = "LastName"
            )
            employeeService.addOrUpdateEmployee(realm, employeeModel)
            binding.tvEmployeeData.text = employeeService.getEmployees(realm).toString()
        } catch (e: Exception) {
            Log.d(TAG, "Exception in creating realm object:", e)
        }
    }

    companion object {
        const val TAG: String = "MainActivity"
    }
}