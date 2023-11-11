package electronicacli.cli
import electronicacli.api.ApiResponse

fun scipt() {
    println("Hello Admin!")

    val api = ApiResponse()

    while (true) {
        //post("/createAdmin", "")
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
        println("What do you want to do?")
        println("1. Refill machine")
        println("2. Working in machine")
        println("3. Machine is ready to use")
        println("4. Get machine by id")
        println("5. Get all machines")
        println("6. Get all products")
        println("7. Get product by id")
        println("8. Exit")

        print("Option: ")
        val option = readLine()

        println("")

        when (option) {
            "1" -> {
                print("Machine id: ")
                val id = readLine()

                print("Product id: ")
                val productId = readLine()

                print("New quantity: ")
                val newQuantity = readLine()

                if (verifyInt(id!!) || verifyInt(productId!!) || verifyInt(newQuantity!!)) {
                    println("Invalid option")
                    continue
                }

                if (!areYouSure()) {
                    continue
                }

                try{
                    val idInt = id!!.toInt()
                    val prodocutIdInt = productId!!.toInt()
                    val newQuantityInt = newQuantity!!.toInt()
                    val refillMachine = api.refillMachine(idInt, newQuantityInt, prodocutIdInt)

                    println("Success!")

                    println(refillMachine)

                } catch (e: Exception) {
                    println("Id, product id and new quantity must be numbers")

                    continue
                }
            }
            "2" -> {
                println("Working in what machine? (id)")
                print("Id: ")

                val id = readLine()

                if (verifyInt(id!!)) {
                    println("Invalid option")
                    continue
                }

                if (!areYouSure()) {
                    continue
                }

                try{
                    val idInt = id.toInt()
                    val workingInMachine = api.workingInMachine(idInt)

                    println("Success!")

                    println(workingInMachine)

                } catch (e: Exception) {
                    println("Id must be a number")

                    continue
                }

            }
            "3" -> {
                println("What machine is ready to use? (id)")
                print("Id: ")

                val id = readLine()

                if (verifyInt(id!!)) {
                    println("Invalid option")
                    continue
                }

                if (!areYouSure()) {
                    continue
                }

                try{
                    val idInt = id.toInt()
                    val machineIsReady = api.machineIsReady(idInt)

                    println("Success!")

                    println(machineIsReady)

                } catch (e: Exception) {
                    println("Id must be a number")

                    continue
                }
            }
            "4" -> {
                print("Machine id: ")
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
            "5" -> {
                try {
                    val machines = api.getAllMachines()
                    println(machines)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            "6" -> {
                try {
                    val products = api.getAllProducts()
                    println(products)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
            "7" -> {
                print("Product id: ")
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
            "8" -> {
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
    print("Y/N: ")
    val confirm = readLine()

    return if (confirm == "Y" || confirm == "y") {
        true
    } else {
        println("Canceled")
        false
    }
}

private fun verifyInt (inputString : String) : Boolean {
    if (inputString.isBlank()) {
        return false
    }

    return try {
        val intValue = inputString.toInt()
        intValue <= 0
    } catch (e: NumberFormatException) {
        true
    }
}