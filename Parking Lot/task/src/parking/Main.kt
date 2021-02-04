package parking

fun main() {
    var spots = mutableListOf<String>()

    while (true) {
        val command = readLine()!!
        if ("exit" in command) {
            break
        }

        if (spots.isEmpty() && "create" !in command) {
            println("Sorry, a parking lot has not been created.")
            continue
        }

        if ("status" in command) {
            if (spots.none { it != "free" }) {
                println("Parking lot is empty.")
            }
            spots.filter { it != "free" }.forEach { s -> println("${spots.indexOf(s) + 1} $s") }
            continue
        }
        if ("reg_by_color" in command) {
            if (spots.none { it != "free" }) {
                println("Parking lot is empty.")
            }
            val carColor = command.split(" ").last().toLowerCase()
            val carOfColorList = spots
                .filter { carColor in it.toLowerCase() }

            if (carOfColorList.isEmpty()) {
                println("No cars with color ${command.split(" ").last()} were found.")
            } else {
                println(carOfColorList.joinToString { it.split(" ").first() })
            }

            continue
        }
        if ("spot_by_color" in command) {
            if (spots.none { it != "free" }) {
                println("Parking lot is empty.")
            }
            val carColor = command.split(" ").last().toLowerCase()
            val carOfColorList = spots
                .filter { carColor in it.toLowerCase() }

            if (carOfColorList.isEmpty()) {
                println("No cars with color ${command.split(" ").last()} were found.")
            } else {
                println(carOfColorList.joinToString { (spots.indexOf(it) + 1).toString() })
            }
            continue
        }
        if ("spot_by_reg" in command) {
            if (spots.none { it != "free" }) {
                println("Parking lot is empty.")
            }
            val carReg = command.split(" ").last().toLowerCase()
            val carOfRegList = spots
                .filter { carReg == it.split(" ").first().toLowerCase() }

            if (carOfRegList.isEmpty()) {
                println("No cars with registration number ${command.split(" ").last()} were found.")
            } else {
                println(carOfRegList.joinToString { (spots.indexOf(it) + 1).toString() })
            }
            continue
        }

        if ("create" in command) {
            val listSize = command.split(" ").last().toInt()
            spots = List(listSize) { "free" } as MutableList<String>
            println("Created a parking lot with $listSize spots.")
            continue
        }

        if ("park" in command) {
            if (spots.none { it == "free" }) {
                println("Sorry, the parking lot is full.")
                continue
            }
            for (i in 0 until spots.size) {
                if (spots[i] == "free") {
                    spots[i] = command.replace("park", "").trim()
                    println("${command.split(" ").last()} car parked in spot ${i + 1}.")
                    break
                }
            }
            continue
        }
        if ("leave" in command) {
            val spotNumber = command.split(" ").last().toInt()
            if (spots[spotNumber - 1] == "free") {
                println("There is no car in spot $spotNumber.")
                continue
            }
            spots[spotNumber - 1] = "free"
            println("Spot $spotNumber is free.")
        }
    }

}

