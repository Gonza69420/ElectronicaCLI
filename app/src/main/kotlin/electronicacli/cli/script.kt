package electronicacli.cli
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
        println("1. Refill machine")
        println("2. Working in machine")
        println("3. Machine is ready to use")
        println("4. Exit")

        print("Option:")
        val option = readLine()

        when (option) {
            "1" -> {
                println("Machine id:")
                val id = readLine()

                println("Product id:")
                val productId = readLine()

                println("New quantity:")
                val newQuantity = readLine()

                if (id == "" || productId == "" || newQuantity == "") {
                    println("Invalid option")
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

                val id = readLine()

                if (id == "") {
                    println("Invalid option")
                    continue
                }

                try{
                    val idInt = id!!.toInt()
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

                val id = readLine()

                if (id == "") {
                    println("Invalid option")
                    continue
                }

                try{
                    val idInt = id!!.toInt()
                    val machineIsReady = api.machineIsReady(idInt)

                    println("Success!")

                    println(machineIsReady)

                } catch (e: Exception) {
                    println("Id must be a number")

                    continue
                }
            }
            "4" -> {
                println("Bye!")
                break
            }
            else -> {
                println("Invalid option")
            }
        }
    }





}