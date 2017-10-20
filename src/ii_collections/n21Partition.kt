package ii_collections

fun example8() {
    val numbers = listOf(1, 3, -4, 2, -11)

    // The details (how multi-assignment works) will be explained later in the 'Conventions' task
    val (positive, negative) = numbers.partition { it > 0 }

    positive == listOf(1, 3, 2)
    negative == listOf(-4, -11)
}

fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> {
    // Return customers who have more undelivered orders than delivered
    val customersWithMoreUndelivered = HashSet<Customer>()
    for (customer in this.customers){
        val (delivered, undelivered) = customer.orders.partition { it.isDelivered }
        if (delivered.count() < undelivered.count()) customersWithMoreUndelivered.add(customer)
    }
    return customersWithMoreUndelivered
    // return this.customers.filter { it.orders.partition { it.isDelivered }.let { it.first.count() < it.second.count() } }.toSet()

}
