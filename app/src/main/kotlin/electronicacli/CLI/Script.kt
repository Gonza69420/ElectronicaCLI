package electronicacli.CLI
import electronicacli.api.ApiResponse
import electronicacli.utils.post


fun scipt() {
    println("Hello Admin!")

    val api = ApiResponse()

    while (true) {
        println("Log In")

        print("Username: ")
        val username = readLine()

        print("Password: ")
        val password = readLine()

        if (username != null && password != null) {
            if (username.length < 3 || password.length < 3) {
                println("Invalid username or password")
                continue
            }
        }
        try {
            val login = api.login(username!!, password!!)

            if (login == "Login successful") {
                break
            } else {
                println(login)
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }

    //clear

    while (true) {
        println("")
        println("")
        println("What do you want to do?")
        println("1. Add machine")
        println("2. Delete machine")
        println("3. Get income in machine")
        println("4. Get all machines")
        println("5. Add maintenance staff")
        println("6. Delete maintenance staff")
        println("7. Get Machine by id")
        println("8. Get total income")
        println("9. Adjust price of product")
        println("10. Add product")
        println("11. Delete product")
        println("12. Get a product by id")
        println("13. Get all products")
        println("14. Get maintenance staff by id")
        println("15. Get all maintenance staff")
        println("16. Exit")

        print("Option: ")

        val option = readlnOrNull()

        when (option) {
            "1" -> {
                if (!areYouSure()) {
                    continue
                }

                try {
                    val addMachine = api.addMachine()
                    println(addMachine)
                } catch (e: Exception) {
                    println(e.message)
                }

            }
            "2" -> {
                print("Machine id:")
                val id = readlnOrNull()

                if (id != null) {
                    if (verifyInt(id)) {
                        println("Invalid id")
                        continue
                    }
                }

                if (!areYouSure()) {
                    continue
                }

                val idInt = id!!.toInt()
                try {
                    val deleteMachine = api.deleteMachine(idInt)
                    println(deleteMachine)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            "3" -> {
                print("Machine id:")
                val id = readlnOrNull()

                if (id != null) {
                    if (verifyInt(id)) {
                        println("Invalid id")
                        continue
                    }
                }

                if (!areYouSure()) {
                    continue
                }

                val idInt = id!!.toInt()

                try {
                    val income = api.getIncomeInMachine(idInt)
                    println(income)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            "4" -> {
                try {
                    val machines = api.getAllMachines()
                    println(machines)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            "5" -> {
                print("Username:")
                val username = readlnOrNull()

                print("Password:")
                val password = readlnOrNull()

                print("Name:")
                val name = readlnOrNull()

                if (username != null && password != null && name != null) {
                    if (username.length > 3 || password.length > 3 || name.length > 3) {
                        println("Invalid username or password")
                        continue
                    }
                }

                try {
                    val addMaintenanceStaff = api.addMaintenanceStaff(username!!, password!!, name!!)
                    println(addMaintenanceStaff)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            "6" -> {
                print("Maintenance staff id:")
                val id = readlnOrNull()

                if (id != null) {
                    if (verifyInt(id)) {
                        println("Invalid id")
                        continue
                    }
                }

                if (!areYouSure()) {
                    continue
                }

                val idInt = id!!.toInt()

                try {
                    val deleteMaintenanceStaff = api.deleteMaintenanceStaff(idInt)
                    println(deleteMaintenanceStaff)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            "7" -> {
                print("Machine id:")
                val id = readlnOrNull()

                if (id != null) {
                    if (verifyInt(id)) {
                        println("Invalid id")
                        continue
                    }
                }

                val idInt = id!!.toInt()

                try {
                    val machine = api.getMachineById(idInt)
                    println(machine)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            "8" -> {
                val totalIncome = api.getTotalIncome()
                println(totalIncome)
            }
            "9" -> {
                print("Product id:")
                val id = readlnOrNull()

                print("New price:")
                val price = readlnOrNull()

                if (id != null && price != null) {
                    if (verifyInt(id) || verifyInt(price)) {
                        println("Invalid id or price")
                        continue
                    }
                }

                if (!areYouSure()) {
                    continue
                }

                val idInt = id!!.toInt()
                val priceInt = price!!.toInt()

                try {
                    val adjustPrice = api.adjustPrice(idInt, priceInt)
                    println(adjustPrice)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            "10" -> {
                print("Product name:")
                val name = readLine()

                print("Product price:")
                val price = readLine()

                if (name != null && price != null) {
                    if (name.length > 3 || verifyInt(price)) {
                        println("Invalid name or price")
                        continue
                    }
                }

                if (!areYouSure()) {
                    continue
                }

                val priceInt = price!!.toInt()

                try {
                    val addProduct = api.addProduct(name!!, priceInt)
                    println(addProduct)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            "11" -> {
                print("Product id:")
                val id = readLine()

                if (id != null) {
                    if (verifyInt(id)) {
                        println("Invalid id")
                        continue
                    }
                }

                if (!areYouSure()) {
                    continue
                }

                val idInt = id!!.toInt()

                try {
                    val deleteProduct = api.deleteProduct(idInt)
                    println(deleteProduct)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            "12" -> {
                print("Product id:")
                val id = readLine()

                if (id != null) {
                    if (verifyInt(id)) {
                        println("Invalid id")
                        continue
                    }
                }

                val idInt = id!!.toInt()

                try {
                    val product = api.getProduct(idInt)
                    println(product)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            "13" -> {
                val products = api.getAllProducts()
                println(products)
            }
            "14" -> {
                print("Maintenance staff id:")
                val id = readLine()

                if (id != null) {
                    if (verifyInt(id)) {
                        println("Invalid id")
                        continue
                    }
                }

                val idInt = id!!.toInt()

                try {
                    val maintenanceStaff = api.getMaintenanceStaff(idInt)
                    println(maintenanceStaff)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            "15" -> {
                try {
                    val maintenanceStaff = api.getAllMaintenanceStaff()
                    println(maintenanceStaff)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            "16" -> {
                println("Bye!")
                break
            }
            else -> {
                println("Invalid option")
            }
        }
    }
}



private fun areYouSure () : Boolean {
    println("Are you sure?")
    print("Y/N:")
    val confirm = readLine()

    return if (confirm == "Y") {
        true
    } else {
        false
    }
}

private fun verifyInt (int : String) : Boolean {
    if (int == "") {
        return false
    }

    return try {
        int.toInt() >= 0
    } catch (e: Exception) {
        false
    }
}
