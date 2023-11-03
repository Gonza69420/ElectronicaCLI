package electronicacli.CLI
import electronicacli.api.ApiResponse



fun scipt() {
    println("Hello Admin!")

    val api = ApiResponse()

    while (true) {
        println("Log In")

        print("Username:")
        val username = readLine()

        print("Password:")
        val password = readLine()

        val login = api.login(username!!, password!!)

        if (login == "Login successful") {
            break
        } else {
            println(login)
        }
    }

    //clear

    while (true) {
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
        println("12. Exit")

        print("Option:")
        val option = readLine()

        when (option) {
            "1" -> {
                val addMachine = api.addMachine()
                println(addMachine)
            }
            "2" -> {
                print("Machine id:")
                val id = readLine()

                val deleteMachine = api.deleteMachine(id!!)
                println(deleteMachine)
            }
            "3" -> {
                print("Machine id:")
                val id = readLine()

                val income = api.getIncomeInMachine(id!!)
                println(income)
            }
            "4" -> {
                val machines = api.getAllMachines()
                println(machines)
            }
            "5" -> {
                print("Username:")
                val username = readLine()

                print("Password:")
                val password = readLine()

                print("Name:")
                val name = readLine()

                val addMaintenanceStaff = api.addMaintenanceStaff(username!!, password!!, name!!)
                println(addMaintenanceStaff)
            }
            "7" -> {
                print("Machine id:")
                val id = readLine()

                val machine = api.getMachineById(id!!)
                println(machine)
            }
            "8" -> {
                val totalIncome = api.getTotalIncome()
                println(totalIncome)
            }
            "9" -> {
                print("Product id:")
                val id = readLine()

                print("New price:")
                val price = readLine()

                val adjustPrice = api.adjustPrice(id!!, price!!)
                println(adjustPrice)
            }
            "10" -> {
                print("Product name:")
                val name = readLine()

                print("Product price:")
                val price = readLine()

                val addProduct = api.addProduct(name!!, price!!)
                println(addProduct)
            }
            "11" -> {
                print("Product id:")
                val id = readLine()

                val deleteProduct = api.deleteProduct(id!!)
                println(deleteProduct)
            }
            "12" -> {
                break
            }
            else -> {
                println("Invalid option")
            }
        }
    }





}