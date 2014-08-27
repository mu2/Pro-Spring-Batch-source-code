package com.apress.springbatch.statement.reader;

import com.apress.springbatch.statement.domain.*;

import java.math.BigDecimal;
import java.util.*;

public class CustomerBuilder {

    private List<Customer> customers;
    private int curIndex;

    private String [] firstNames = {"Michael", "Warren", "Ann", "Terrence", "Erica", "Laura", "Warren", "Steve", "Larry", "Barack"};
    private String middleInitial = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String [] lastNames = {"Minella", "Gates", "Darrow", "Donnelly", "Benes", "Jobs", "Buffett", "Ellison", "Obama"};
    private String [] streets = {"4th Street", "Wall Street", "Fifth Avenue", "Mt. Lee Drive", "Jeopardy Lane", "Infinite Loop Drive", "Farnam Street", "Isabella Ave", "S. Greenwood Ave"};
    private String [] cities = {"Chicago", "New York", "Hollywood", "Aurora", "Omaha", "Atherton"};
    private String [] states = {"IL", "NY", "CA", "NE"};

    private Random generator = new Random();

    public CustomerBuilder() {
        curIndex = 0;
        
        customers = new ArrayList<Customer>();
        
        for(int i = 0; i < 100; i++) {
            customers.add(buildCustomer());
        }
    }
    
    private Customer buildCustomer() {
        Customer customer = new Customer();

        customer.setFirstName(firstNames[generator.nextInt(firstNames.length - 1)]);
        //customer.setMiddleInitial(String.valueOf(middleInitial.charAt(generator.nextInt(middleInitial.length() - 1))));
        customer.setLastName(lastNames[generator.nextInt(lastNames.length - 1)]);
        customer.setAddress(new Address(generator.nextInt(9999) + " " + streets[generator.nextInt(streets.length - 1)],
                cities[generator.nextInt(cities.length - 1)],states[generator.nextInt(states.length - 1)],
                String.valueOf(generator.nextInt(99999))));

        customer.setAccount(new Account(generator.nextLong(), "" + generator.nextLong(),
                customer, new BigDecimal(generator.nextLong()),
                PricingTier.convert(generator.nextInt(4)),
                Arrays.asList(new Transaction(generator.nextLong(), generator.nextLong(),
                                "" + generator.nextLong(), "tckr1", generator.nextLong(), generator.nextLong(), new Date(),
                                new BigDecimal(generator.nextInt()), TransactionType.STOCK),

                        new Transaction(generator.nextLong(), generator.nextLong(),
                                "" + generator.nextLong(), "tckr2", generator.nextLong(), generator.nextLong(), new Date(),
                                new BigDecimal(generator.nextInt()), TransactionType.STOCK),

                        new Transaction(generator.nextLong(), generator.nextLong(),
                                "" + generator.nextLong(), "tckr3", generator.nextLong(), generator.nextLong(), new Date(),
                                new BigDecimal(generator.nextInt()), TransactionType.STOCK)


                )

        ));
        //customer.setCity(cities[generator.nextInt(cities.length - 1)]);
       // customer.setState(states[generator.nextInt(states.length - 1)]);
        //customer.setZip(String.valueOf(generator.nextInt(99999)));
        
        return customer;
    }

    public Customer getCustomer() {
        Customer cust = null;
        
        if(curIndex < customers.size()) {
            cust = customers.get(curIndex);
            curIndex++;
        }
        
        return cust;
    }

}
