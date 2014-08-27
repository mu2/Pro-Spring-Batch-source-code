package com.apress.springbatch.statement.reader;

import com.apress.springbatch.statement.domain.Transaction;
import com.apress.springbatch.statement.domain.TransactionType;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.apress.springbatch.statement.dao.TickerDao;
import com.apress.springbatch.statement.domain.Customer;
import com.apress.springbatch.statement.domain.Statement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class CustomerStatementReader implements ItemReader<Statement> {

    private ItemReader<Customer> customerReader;
    private TickerDao tickerDao;
    private Random generator = new Random();

    public Statement read() throws Exception, UnexpectedInputException,
            ParseException {
        
        Customer customer = customerReader.read();
        
        if(customer == null) {
            return null;
        } else {
            Statement statement = new Statement();
            
            statement.setCustomer(customer);
            statement.setSecurityTotal(new BigDecimal(generator.nextInt()));
            statement.setStocks(Arrays.asList(new Transaction(generator.nextLong(),generator.nextLong(),
                    "" + generator.nextLong() ,"tckr1", generator.nextLong(), generator.nextLong(), new Date(),
                    new BigDecimal(generator.nextInt()), TransactionType.STOCK),

                    new Transaction(generator.nextLong(),generator.nextLong(),
                        "" + generator.nextLong() ,"tckr2", generator.nextLong(), generator.nextLong(), new Date(),
                        new BigDecimal(generator.nextInt()), TransactionType.STOCK),

                    new Transaction(generator.nextLong(),generator.nextLong(),
                        "" + generator.nextLong() ,"tckr3", generator.nextLong(), generator.nextLong(), new Date(),
                        new BigDecimal(generator.nextInt()), TransactionType.STOCK)


            ));
//            statement.setSecurityTotal(tickerDao.getTotalValueForCustomer(customer.getId()));
//            statement.setStocks(tickerDao.getStocksForCustomer(customer.getId()));
            
            return statement;
        }
    }

    public void setCustomerReader(ItemReader<Customer> customerReader) {
        this.customerReader = customerReader;
    }

    public void setTickerDao(TickerDao tickerDao) {
        this.tickerDao = tickerDao;
    }
}
