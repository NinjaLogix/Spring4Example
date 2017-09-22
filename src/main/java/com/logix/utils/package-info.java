@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(
                name="Customer.fetchByName",
                query="SELECT e.* FROM customer e WHERE e.name like '%:custName%'"),
        @org.hibernate.annotations.NamedQuery(
                name="customer.findAll",
                query="from Customer c"
        )
})

package com.logix.utils;